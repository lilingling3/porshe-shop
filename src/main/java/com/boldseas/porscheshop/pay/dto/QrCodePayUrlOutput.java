package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Data
public class QrCodePayUrlOutput {
    /**
     * 平台错误码
     */
    private String errCode;

    /**
     * 平台错误信息
     */
    private String errMsg;
    /**
     * 消息ID，原样返回
     */
    private String msgId;
    /**
     * 消息类型，原样返回
     */
    private String msgType;
    /**
     * 消息来源，原样返回
     */
    private String msgSrc;
    /**
     * 报文应答时间，格式yyyy-MM-dd HH:mm:ss
     */
    private String responseTimeStamp;
    /**
     * 请求系统预留字段
     */
    private String srcReserve;
    /**
     * 商户号，原样返回
     */
    private String mid;
    /**
     * 终端号，原样返回
     */
    private String tid;
    /**
     * 业务类型，原样返回
     */
    private String instMid;
    /**
     * 账单号，原样返回
     */
    private String billNo;
    /**
     * 账单日期，原样返回
     */
    private String billDate;
    /**
     * 账单二维码
     */
    private String billQRCode;
    /**
     * 系统ID，原样返回
     */
    private String systemId;
    /**
     * 签名算法
     */
    private String signType;
    /**
     * 签名
     */
    private String sign;

    public boolean isSuccess() {
        return "SUCCESS".equals(this.errCode);
    }

    public Long getId() {
        return null;
    }
}
