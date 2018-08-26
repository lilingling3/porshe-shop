package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@Component
@ConfigurationProperties(prefix = "timer")
@Data
public class TimerConfig {
    private Boolean run;
    private String sendPaySuccessForDealer;
}
