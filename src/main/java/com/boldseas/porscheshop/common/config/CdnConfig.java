package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wushan
 * @version 16/8/20
 */
@Component
@ConfigurationProperties(prefix = "cdn")
@Data
public class CdnConfig {

    private Path path;

    @Data
    public static class Path {

        private String image;
        private String style;
        private String scripts;
    }

}

