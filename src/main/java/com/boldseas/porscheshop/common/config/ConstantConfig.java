package com.boldseas.porscheshop.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chen Jingxuan
 * @version 2018/5/14
 */
@Component
@ConfigurationProperties(prefix = "constant")
@Data
public class ConstantConfig {
    private long verificationCodeSendPhoneMinutes;
    private int verificationCodeSendPhoneMaxTimes;
    private long verificationCodeSendIpMinutes;
    private int verificationCodeSendIpMaxTimes;
    private long phoneLoginFailMinutes;
    private int phoneLoginFailMaxTimes;
    private long ipLoginFailMinutes;
    private int ipLoginFailMaxTimes;
    private long loginExpireTime;
    private long ipValidateTokenFailMinutes;
    private int ipValidateTokenFailMaxTimes;

    public int getIntLoginExpireTime() {
        return (int) loginExpireTime;
    }
}
