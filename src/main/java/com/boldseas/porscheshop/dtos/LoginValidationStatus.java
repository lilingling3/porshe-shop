package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.constant.LoginValidationResultMessage;

/**
 * @author Chen Jingxuan
 * @version 2018/5/14
 */
public enum LoginValidationStatus {
    /**
     * 登录成功
     */
    SUCCESS(LoginValidationResultMessage.SUCCESS),
    /**
     * 未登录或登录已过期
     */
    FAILED_OR_EXPIRED(LoginValidationResultMessage.FAILED_OR_EXPIRED),
    /**
     * 登录失败次数过多
     */
    VALIDATION_FAIL_TOO_MANY_TIMES(LoginValidationResultMessage.VALIDATION_FAIL_TOO_MANY_TIMES),
    /**
     * 参数错误
     */
    PARAMS_ERROR(LoginValidationResultMessage.PARAMS_ERROR);

    LoginValidationStatus(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage(){
        return this.message;
    }
}
