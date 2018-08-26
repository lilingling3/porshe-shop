package com.boldseas.porscheshop.interceptor;

import com.boldseas.porscheshop.common.config.ConstantConfig;
import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.dtos.UserDTO;
import com.boldseas.porscheshop.user.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chen Jingxuan
 * @version 2018/6/7
 */
@Component
public class UserSourceInterceptor implements HandlerInterceptor {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ConstantConfig constantConfig;

    private final String HOME_PAGE_URI = "/home";
    private final String LOGIN_PAGE_URI = "/login";

    /**
     * 记录/更新用户来源渠道（source）
     *
     * 代码逻辑：
     * 前提是用户为已登录状态。
     * 当用户访问主页（home）和登录页（login）时，更新当前的用户的来源渠道为url中带的source，
     * 当用户访问其他页面时，当且仅当url中source不为空时，更新当前用户的来源渠道。
     * 用户source保存在redis中。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前用户是否登录
        if(!userProvider.isLogin()){
            return true;
        }

        //取当前url中的source
        String source = StringUtils.isEmpty(request.getParameter(CommonConstants.SOURCE)) ? CommonConstants.EMPTY_STRING : request.getParameter(CommonConstants.SOURCE);

        //用户source存在 跳转链接添加source
        addUserSourceForURL(request,response,source);

        //判断是否应该更新当前用户的source
        //当用户访问的路径为主页（home）或登录页（login）或访问url中source不为空时，更新当前用户source
        if (!updateUserSourceCondition(request)) {
            return true;
        }

        //更新当前用户source，当前用户source保存在redis缓存中
        UserDTO currentUserDTO = userProvider.getCurrentUser();
        Long userId = currentUserDTO.getId();
        redisService.save(PrefixConstant.USER_SOURCE, userId.toString(), source, constantConfig.getLoginExpireTime());
        return true;
    }

    private void addUserSourceForURL(HttpServletRequest request,HttpServletResponse response,String urlSource)throws Exception{
        String userSource = userProvider.getUserSource();
        if(StringUtils.isEmpty(urlSource)&& !StringUtils.isEmpty(userSource)){
            String url = request.getRequestURI();
            if(StringUtils.isEmpty(request.getQueryString())){
                response.sendRedirect(String.format("%s?"+CommonConstants.SOURCE+"=%s",request.getRequestURI(),userSource));
            }else if(!request.getQueryString().contains(CommonConstants.SOURCE)){
                response.sendRedirect(String.format("%s?%s&"+CommonConstants.SOURCE+"=%s",request.getRequestURI(), request.getQueryString(),userSource));
            }
        }
    }

    private boolean updateUserSourceCondition(HttpServletRequest request) {
        return request.getRequestURI().contains(HOME_PAGE_URI) ||
                request.getRequestURI().contains(LOGIN_PAGE_URI) ||
                !StringUtils.isEmpty(request.getParameter(CommonConstants.SOURCE));
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
