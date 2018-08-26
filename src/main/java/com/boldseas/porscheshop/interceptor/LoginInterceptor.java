package com.boldseas.porscheshop.interceptor;

import com.boldseas.porscheshop.common.config.ConstantConfig;
import com.boldseas.porscheshop.common.config.CookieConfig;
import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.utils.CookieUtil;
import com.boldseas.porscheshop.dtos.UserLoginValidationResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private ConstantConfig constantConfig;
    @Autowired
    private CookieConfig cookieConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserLoginValidationResultDTO userLoginValidationResult = userProvider.loginValidation(request);
        String source = request.getParameter("source");
        if (!userLoginValidationResult.isSuccess()) {
            response.sendRedirect(String.format("/login?source=%s", StringUtils.isEmpty(source) ? CommonConstants.EMPTY_STRING : source));
            return true;
        }
        userProvider.setCurrentUser(userLoginValidationResult.getToken().getCurrentUserDTO());
        CookieUtil.addCookie(request, response, CommonConstants.AUTH_TOKEN_COOKIE_NAME, userLoginValidationResult.getToken().getAuthToken(), constantConfig.getIntLoginExpireTime(), null, cookieConfig.getDomain());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
