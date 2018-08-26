package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@Component
@ConfigurationProperties(prefix = "goods")
@Data
public class Goods {
    private Long id;
    private Double price;
}
