package com.boldseas.porscheshop;

import com.boldseas.porscheshop.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yefei
 */
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableCaching
@EnableAsync(proxyTargetClass = true)
@EnableFeignClients("com.boldseas")
public class PorscheShopApplication extends WebMvcConfigurerAdapter {

    @Autowired
    private IpLimitInterceptor ipLimitInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private GlobalInterceptor globalInterceptor;
    @Autowired
    private WeixinEnvironmentInterceptor weixinEnvironmentInterceptor;
    @Autowired
    private UserSourceInterceptor userSourceInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(PorscheShopApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipLimitInterceptor);
        registry.addInterceptor(weixinEnvironmentInterceptor).excludePathPatterns("/ums/**");
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login", "/home/wechat-interceptor", "/v1/api/user/login",
                "/ums/**", "/v1/api/user/send_verification_code", "/swagger-resources/**", "/v2/**","/account/download/**");
        registry.addInterceptor(userSourceInterceptor).excludePathPatterns("/**/api/**");
        registry.addInterceptor(globalInterceptor).excludePathPatterns("/**/api/**");

        super.addInterceptors(registry);
    }
}
