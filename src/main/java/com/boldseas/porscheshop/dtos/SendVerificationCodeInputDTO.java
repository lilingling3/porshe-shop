package com.boldseas.porscheshop.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Chen Jingxuan
 * @version 2018/5/16
 */
@Data
public class SendVerificationCodeInputDTO {
    @NotBlank
    private String phone;
    private String ip;
}
