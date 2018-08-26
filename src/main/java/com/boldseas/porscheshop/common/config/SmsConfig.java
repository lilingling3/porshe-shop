package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@ConfigurationProperties(prefix = "sms")
@Component
@Data
public class SmsConfig {

    private String accessToken;

    private String paySuccessToken;

    private String offLinePaymentToken;
}
