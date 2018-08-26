package com.boldseas.porscheshop.common.utils;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
public class CookieUtil {
    private static final String DEFAULT_PATH = "/";
    /**
     * 获取cookie
     *
     * @param request HttpServletRequest
     * @param name    Cookie名称
     * @return Cookie值，若不存在则返回 ""
     */
    public static String getCookie(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        Assert.notNull(request, "HttpServletRequest is null.");
        Assert.hasText(name, "CookieName is empty.");

        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            name = URLEncoder.encode(name, "UTF-8");
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
            }

        }
        return CommonConstants.EMPTY_STRING;
    }

    /**
     * 添加cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param value    Cookie值
     * @param domain   域
     * @param maxAge   有效期
     * @param path     path
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain) throws UnsupportedEncodingException {
        Assert.notNull(request, "HttpServletRequest is null.");
        Assert.notNull(response, "HttpServletResponse is null.");
        Assert.hasText(name, "CookieName is empty.");
        Assert.hasText(value, "CookieValue is empty.");

        name = URLEncoder.encode(name, "UTF-8");
        value = URLEncoder.encode(value, "UTF-8");
        Cookie cookie = new Cookie(name, value);
        if (Objects.nonNull(maxAge)) {
            cookie.setMaxAge(maxAge);
        }
        if (StringUtils.isEmpty(path)) {
            path = DEFAULT_PATH;
        }
        cookie.setPath(path);
        if (!StringUtils.isEmpty(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);
    }

    /**
     * 移除cookie
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param name     Cookie名称
     * @param path     路径
     * @param domain   域
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain) throws UnsupportedEncodingException {
        Assert.notNull(request, "HttpServletRequest is null.");
        Assert.notNull(response, "HttpServletResponse is null.");
        Assert.hasText(name, "CookieName is empty.");

        name = URLEncoder.encode(name, "UTF-8");
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        if (StringUtils.isEmpty(path)) {
            path = DEFAULT_PATH;
        }
        cookie.setPath(path);
        if (!StringUtils.isEmpty(domain)) {
            cookie.setDomain(domain);
        }
        response.addCookie(cookie);

    }
}
