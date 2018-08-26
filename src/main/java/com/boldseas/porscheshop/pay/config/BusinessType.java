package com.boldseas.porscheshop.pay.config;

import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/5/21.
 */
@Data
public class BusinessType {
    /**
     * 业务类型
     */
    private String type;
    /**
     * 机构商户号(支付类型）
     */
    private String instMid;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 支付url
     */
    private String payUrl;
    /**
     * 支付结果通知地址
     */
    private String notifyUrl;
    /**
     * 网页跳转地址
     */
    private String returnUrl;

}
