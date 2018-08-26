package com.boldseas.porscheshop.pay.dto;

import com.boldseas.porscheshop.common.constant.CommonConstants;
import com.boldseas.porscheshop.pay.utils.UmsPayUtils;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author huisheng.jin
 * @version 2018/5/21.
 */
@Data
public class BasePayInput {
    /**
     * 消息来源
     */
    private String msgSrc;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 消息来源编号
     */
    private String msgSrcId;
    /**
     * 商户号
     */
    private String mid;
    /**
     * 终端号
     */
    private String tid;
    /**
     * 业务类型
     */
    private String instMid;
    /**
     * 账单总金额
     */
    private Integer totalAmount;
    /**
     * 支付结果通知地址
     */
    private String notifyUrl;
    /**
     * 网页跳转地址
     */
    private String returnUrl;
    /**
     * 签名
     */
    private String sign;

    /**
     * 报文请求时间，格式yyyy-MM-dd HH:mm:ss
     */
    private String requestTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public void createSign(String privateKey) {
        this.sign = UmsPayUtils.generateSign(this, privateKey);
    }

    public void updateReturnUrl(String shopOrderSn,String source) {
        this.returnUrl = this.returnUrl + shopOrderSn +"?"+ CommonConstants.SOURCE+"="+source;
    }
}
