package com.boldseas.porscheshop.dtos;

import com.boldseas.base.dto.CaptchaGenerateOutput;
import com.boldseas.base.dto.RestApiResult;
import com.boldseas.porscheshop.common.constant.SendVerificationCodeResultMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author feng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class RestResult <T> {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "成功或失败信息")
    private String message;
    @ApiModelProperty(value = "返回对象,没有返回时为空对象")
    private T data;

    public RestResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public RestResult(Boolean success,T data){
        this.success=success;
        this.data=data;
    }

    public static <T> RestResult<T> success(T data) {
        RestResult<T> restResult = new RestResult<>();
        restResult.success = true;
        restResult.setMessage("SUCCESS");
        restResult.setData(data);
        return restResult;
    }

    public static <T> RestResult<T> success() {
        RestResult<T> restResult = new RestResult<>();
        restResult.success = true;
        restResult.setMessage("SUCCESS");
        return restResult;
    }

    public static <T> RestResult<T> failure(String message) {
        RestResult<T> restResult = new RestResult<>();
        restResult.success = false;
        restResult.setMessage(message);
        return restResult;
    }

    public static <T> RestResult<T> failure(String message, T data) {
        RestResult<T> restResult = failure(message);
        restResult.setData(data);
        return restResult;
    }

    public static RestResult<CaptchaGenerateOutput> from(RestApiResult<CaptchaGenerateOutput> captchaGenerateResult) {
        RestResult<CaptchaGenerateOutput> restResult = new RestResult<>();
        restResult.success = Objects.equals(captchaGenerateResult.getCode(), 0) && Objects.nonNull(captchaGenerateResult.getData().getResult().getContent());
        restResult.setData(captchaGenerateResult.getData());
        restResult.setMessage(restResult.success ? SendVerificationCodeResultMessage.SEND_SUCCESS : SendVerificationCodeResultMessage.SEND_FAIL);
        return restResult;
    }

}
