package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author huisheng.jin
 * @version 2018/6/5.
 */
@Data
public class SearchWapPayDto {
    public static final String BILLS_QUERY_TYPE = "query";
    /**
     * 消息来源
     */
    private String msgSrc;
    /**
     * 消息类型
     */
    private String msgType;
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
     * 签名
     */
    private String sign;

    /**
     * 报文请求时间，格式yyyy-MM-dd HH:mm:ss
     */
    private String requestTimestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    /**
     * 商户订单号
     */
    private String merOrderId;

    public void updateMsgType() {
        this.msgType = BILLS_QUERY_TYPE;
    }

    public void updateSign(String sign) {
        this.sign = sign;
    }
}
