package com.boldseas.porscheshop.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 用户登录结果
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResultDTO {
    /**
     * 登录返回的状态
     */
    @JsonIgnore
    private UserLoginStatus status;
    /**
     * 登录返回的文字提示
     */
    @JsonIgnore
    private String message;
    /**
     * 登录成功后生成的token
     */
    private String authToken;

    /**
     * 登录是否成功
     * @return
     */
    public boolean isSuccess() {
        return Objects.equals(this.status, UserLoginStatus.LOGIN_SUCCESS);
    }

    public UserLoginResultDTO(UserLoginStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public UserLoginResultDTO(UserLoginStatus userLoginStatus){
        this.status = userLoginStatus;
        this.message = userLoginStatus.getMessage();
    }
    public UserLoginResultDTO(String authToken, UserLoginStatus userLoginStatus){
        this.authToken = authToken;
        this.status = userLoginStatus;
        this.message = userLoginStatus.getMessage();
    }
}
