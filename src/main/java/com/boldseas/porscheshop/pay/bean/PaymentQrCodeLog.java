package com.boldseas.porscheshop.pay.bean;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * 扫码支付记录
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment_qr_code_log")
@Data
public class PaymentQrCodeLog extends BaseEntity<Long> {

    /**
     * 商城订单号
     */
    @Column(name = "order_sn", length = 50)
    private String orderSn;
    /**
     * 商品价格
     */
    @Column
    private Double price;
    /**
     * 商户号
     */
    @Column(name = "merchant_id", length = 50)
    private String mid;
    /**
     * 终端号
     */
    @Column(name = "terminal_id", length = 50)
    private String tid;
    /**
     * 机构商户号
     */
    @Column(name = "institution_merchant_id", length = 50)
    private String instMid;
    /**
     * 账单二维码
     */
    @Column(name = "bill_qr_code", length = 150)
    private String billQRCode;
    /**
     * 商户订单编号（扫码支付时，使用该字段作为商户订单号）
     */
    @Column(name = "bill_no", length = 50)
    private String billNo;
    /**
     * 账单状态
     * PAID
     * UNPAID
     * REFUND
     * CLOSED
     * UNKNOWN
     */
    @Column(name = "bill_status", length = 50)
    private String billStatus;

    /**
     * 商户订单编号（非扫码支付时，使用该字段作为商户订单号）
     */
    @Column(name = "merchant_order_id", length = 50)
    private String merOrderId;
    /**
     * 订单时间，格式yyyy-MM-dd
     */
    @Column(name = "bill_date", length = 50)
    private String billDate;
    /**
     * 支付通知Id(通知唯一ID，重发通知的notifyId不变)
     */
    @Column(name = "notify_id", length = 50)
    private String notifyId;
    /**
     * 消息来源编号
     */
    @Column(name = "msg_id", length = 50)
    private String msgId;

    /**
     * 消息类型
     */
    @Column(name = "msg_type", length = 50)
    private String msgType;
    /**
     * 消息来源
     */
    @Column(name = "msg_src", length = 50)
    private String msgSrc;
    /**
     * 系统Id
     */
    @Column(name = "system_id", length = 50)
    private String systemId;
    /**
     * 签名类型
     */
    @Column(name = "sign_type", length = 50)
    private String signType;
    /**
     * 签名
     */
    @Column(name = "sign", length = 50)
    private String sign;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_log_detail_id")
    private PaymentQrCodeLogDetail paymentQrCodeLogDetail;

    public Boolean isCallBacked() {
        return !StringUtils.isEmpty(billStatus);
    }

    public void updateOrderDetail(String orderSn, String billQRCode) {
        this.orderSn = orderSn;
        this.billQRCode = billQRCode;
    }
}
