package com.boldseas.porscheshop.common.utils;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
public class RequestUtils {
    private static final String UNKNOWN = "unknown";
    private static final String WEI_XIN_USER_AGENT = "micromessenger";
    public static final String USER_AGENT_KEY = "user-agent";

    /**
     * 获取请求的ip地址
     *
     * @param request HttpServletRequest
     * @return IP地址
     */
    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 是否是微信环境
     *
     * @param request
     * @return
     */
    public static boolean isWeixinEnv(HttpServletRequest request) {

        String userAgent = request.getHeader(USER_AGENT_KEY);
        if (StringUtils.isEmpty(userAgent)) {
            return false;
        }
        return userAgent.toLowerCase().contains(WEI_XIN_USER_AGENT);
    }
}
