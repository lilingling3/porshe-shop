package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.constant.UserLoginResultMessage;

/**
 * 用户登录返回的状态
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
public enum UserLoginStatus {

    /**
     * 登录成功
     */
    LOGIN_SUCCESS(UserLoginResultMessage.LOGIN_SUCCESS_MESSAGE),
    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR(UserLoginResultMessage.VERIFICATION_CODE_ERROR_MESSAGE),
    /**
     * 参数不完整
     */
    PARAMS_ERROR(UserLoginResultMessage.PARAMS_ERROR_MESSAGE),
    /**
     * 登录失败次数过多，已被锁定
     */
    LOGIN_FAIL_TOO_MANY_TIMES(UserLoginResultMessage.LOGIN_FAIL_TOO_MANY_TIMES);


    UserLoginStatus(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage(){
        return this.message;
    }
}
