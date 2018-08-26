package com.boldseas.porscheshop.pay.dto;

/**
 * @author huisheng.jin
 * @version 2018/5/21.
 */
public enum PayTypeEnum {
    /**
     * 扫码支付
     */
    qrCode,
    /**
     * h5支付(支付宝)
     */
    h5AliPay,
    /**
     * 微信公众号支付
     */
    wxPublic,
    /**
     * 云闪付
     */
    h5QuickPass,
    /**
     * 网关支付
     */
    gateWay
}
