package com.boldseas.porscheshop.pay.bean;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 商户信息
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "merchant")
@Data
public class Merchant extends BaseEntity<Long> {

    @Column(length = 20)
    private String dealerCode;
    /**
     * 保时捷中心名称
     */
    @Column(length = 100)
    private String centerName;
    /**
     * 营业执照名称
     */
    @Column(length = 100)
    private String businessLicenseName;
    /**
     * H5商户编号(包括支付宝，云闪付等)
     */
    @Column(length = 20)
    private String H5No;
    /**
     * H5终端编号
     */
    @Column(length = 20)
    private String h5TerminalNo;
    /**
     * 网关商户编号 --- 对应文档中的无卡商户编号
     */
    @Column(length = 20)
    private String gateWayNo;
    /**
     * 网关终端编号 --- 对应文档中的无卡终端编号
     */
    @Column(length = 20)
    private String gateWayTerminalNo;
    /**
     * 二维码商户编号 --- 对应C扫B商户编号
     */
    @Column(length = 20)
    private String qrCodeNo;
    /**
     * 二维码终端编号 --- 对应C扫B终端编号
     */
    @Column(length = 20)
    private String qrCodeTerminalNo;
    /**
     * 微信公众号商户编号
     */
    @Column(length = 20)
    private String wxPublicNo;
    /**
     * 微信公众号终端编号
     */
    @Column(length = 20)
    private String wxPublicTerminalNo;
}
