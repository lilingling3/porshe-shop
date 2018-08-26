package com.boldseas.porscheshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Chen Jingxuan
 * @version 2018/5/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVerificationCodeResultDTO {
    private String captcha;

    @Data
    public class SmsResponseModel {
        private Integer code;
        private String status;
        private String content;
    }
}
