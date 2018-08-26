package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@Component
@ConfigurationProperties(prefix = "cookie")
@Data
public class CookieConfig {
    private String domain;
}
