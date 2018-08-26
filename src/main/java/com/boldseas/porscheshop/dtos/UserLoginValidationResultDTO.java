package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.user.beans.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author Chen Jingxuan
 * @version 2018/5/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginValidationResultDTO {
    private Token token;
    private LoginValidationStatus status;
    private String message;

    public boolean isSuccess() {
        return Objects.nonNull(token);
    }

    public UserLoginValidationResultDTO(LoginValidationStatus status){
        this.status = status;
        this.message = status.getMessage();
    }

    public UserLoginValidationResultDTO(LoginValidationStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public UserLoginValidationResultDTO(Token token, LoginValidationStatus status) {
        this.token = token;
        this.status = status;
        this.message = status.getMessage();
    }
}
