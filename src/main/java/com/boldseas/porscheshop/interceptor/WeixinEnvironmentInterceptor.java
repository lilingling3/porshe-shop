package com.boldseas.porscheshop.interceptor;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.utils.RequestUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@Component
public class WeixinEnvironmentInterceptor implements HandlerInterceptor {

    private static final String INTERCEPTOR_WECHAT_PAGE_URL = "/home/wechat-interceptor";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!RequestUtils.isWeixinEnv(request)) {
            return true;
        }
        if (isInterceptWechatPageUrl(request)) {
            return true;
        }
        String source = StringUtils.isEmpty(request.getParameter("source")) ? CommonConstants.EMPTY_STRING : request.getParameter("source");

        response.sendRedirect(INTERCEPTOR_WECHAT_PAGE_URL+"?source="+source);
        return false;
    }

    private boolean isInterceptWechatPageUrl(HttpServletRequest request) {
        return request.getRequestURL().toString().contains(INTERCEPTOR_WECHAT_PAGE_URL);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
