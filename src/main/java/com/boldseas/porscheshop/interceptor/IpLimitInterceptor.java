package com.boldseas.porscheshop.interceptor;

import com.boldseas.porscheshop.interceptor.limit.*;
import com.boldseas.porscheshop.common.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Component
public class IpLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private final LimitService limitService;

    @Autowired
    public IpLimitInterceptor(LimitService limitService) {
        this.limitService = limitService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        IpLimit ipLimit = method.getMethodAnnotation(IpLimit.class);
        if (ipLimit == null) {
            return true;
        }
        Limit limit = new Limit();
        limit.setIp(RequestUtils.getUserIp(request));
        limit.setPath(request.getServletPath());
        limit.setSeconds(ipLimit.seconds());
        if (!limitService.limit(limit, ipLimit.times())) {
            response.sendError(408, "request time out limit");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
