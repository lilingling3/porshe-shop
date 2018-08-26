package com.boldseas.porscheshop.pay.config;

import com.boldseas.porscheshop.pay.dto.PayTypeEnum;
import com.boldseas.porscheshop.pay.utils.UmsPayUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 银联接口账户相关配置
 *
 * @author huisheng.jin
 * @version 2018/5/11.
 */

@Component
@ConfigurationProperties(prefix = "umsPay")
@Data
public class PayConfig {
    /**
     * 消息来源编号
     */
    private String msgSrcId;
    /**
     * 消息来源
     */
    private String msgSrc;
    /**
     * 终端号
     */
    private String tid;
    /**
     * 加密私钥
     */
    private String privateKey;
    /**
     * 业务类型
     */
    private List<BusinessType> businessTypes = new ArrayList<>();

    public String getMid() {
        return "";
    }

    public String generateMerchantOrderId() {
        return UmsPayUtils.generateMerchantOrderId(msgSrcId);
    }

    public BusinessType getBusinessType(PayTypeEnum payType) {
        return businessTypes.stream()
                .filter(item -> item.getType().equals(payType.toString()))
                .findFirst()
                .get();
    }

}
