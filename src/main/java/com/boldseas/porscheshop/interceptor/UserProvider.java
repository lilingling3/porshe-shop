package com.boldseas.porscheshop.interceptor;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.common.utils.CookieUtil;
import com.boldseas.porscheshop.common.utils.RequestUtils;
import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.user.RedisService;
import com.boldseas.porscheshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProvider {
    private CurrentUserDTO currentUser;
    private final UserService userService;
    private final RedisService redisService;


    @Autowired
    public UserProvider(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    /**
     * 设置当前登录用户
     */
    public void setCurrentUser(CurrentUserDTO currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public UserDTO getCurrentUser() {
      return userService.findById(currentUser.getUserId());
//          return userService.findById(23l);
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return Objects.nonNull(this.currentUser);
    }


    /**
     * 验证当前用户是否登录
     *
     * @param request
     * @return
     */
    public UserLoginValidationResultDTO loginValidation(HttpServletRequest request) throws UnsupportedEncodingException {
        String authToken = CookieUtil.getCookie(request, CommonConstants.AUTH_TOKEN_COOKIE_NAME);
        if (StringUtils.isEmpty(authToken)) {
            return new UserLoginValidationResultDTO(LoginValidationStatus.FAILED_OR_EXPIRED);
        }

        UserLoginValidationInputDTO userLoginValidationInput = new UserLoginValidationInputDTO();
        userLoginValidationInput.setAuthToken(authToken);
        userLoginValidationInput.setIp(RequestUtils.getUserIp(request));

        return userService.loginValidation(userLoginValidationInput);
    }

    public String getUserSource() {
        if (Objects.nonNull(this.currentUser)) {
            return redisService.get(PrefixConstant.USER_SOURCE, currentUser.getUserId().toString());
        }
        return CommonConstants.EMPTY_STRING;
    }
}
