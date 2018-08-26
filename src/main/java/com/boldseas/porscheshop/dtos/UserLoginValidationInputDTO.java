package com.boldseas.porscheshop.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Chen Jingxuan
 * @version 2018/5/14
 */
@Data
public class UserLoginValidationInputDTO {
    @NotBlank(message = "auth_token为空")
    private String authToken;
    private String ip;
}
