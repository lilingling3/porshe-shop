package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@Data
@Component
@ConfigurationProperties(prefix = "email")
public class EmailConfig {
    private String paySuccessToken;
    private String refundToken;
    private String testEmail;
    private String excelPassword;
    private String dailyReportToken;
    private String dailyReportAddresses;
}
