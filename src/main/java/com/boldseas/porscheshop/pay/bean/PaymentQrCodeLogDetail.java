package com.boldseas.porscheshop.pay.bean;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * 支付记录详情
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment_qr_code_log_detail")
@Data
public class PaymentQrCodeLogDetail extends BaseEntity<Long> {

    /**
     * 账单业务类型
     */
    @Column(name = "bill_biz_type", length = 50)
    private String billBizType;
    /**
     * 开票金额
     */
    @Column(name = "invoice_amount")
    private Integer invoiceAmount;
    /**
     * 商户订单号
     */
    @Column(name = "mer_order_id", length = 50)
    private String merOrderId;
    /**
     * 交易参考号
     */
    @Column(name = "pay_seq_id", length = 50)
    private String paySeqId;
    /**
     * 账单流水总金额
     */
    @Column(name = "total_amount")
    private Integer totalAmount;
    /**
     * 实付金额
     */
    @Column(name = "buyer_pay_amount")
    private Integer buyerPayAmount;
    /**
     * 钱包折扣金额
     */
    @Column(name = "coupon_amount")
    private Integer couponAmount;
    /**
     * 折扣金额
     */
    @Column(name = "discount_amount")
    private Integer discountAmount;
    /**
     * 买家ID
     */
    @Column(name = "buyer_id", length = 50)
    private String buyerId;
    /**
     * 买家用户名
     */
    @Column(name = "buyer_username", length = 50)
    private String buyerUsername;
    /**
     * 支付详情
     */
    @Column(name = "pay_detail", length = 50)
    private String payDetail;
    /**
     * 支付时间，格式yyyy-MM-dd HH:mm:ss
     */
    @Column(name = "pay_time",length = 50)
    private String payTime;
    /**
     * 结算时间，格式yyyy-MM-dd
     */
    @Column(name = "settle_date",length = 50)
    private String settleDate;
    /**
     * 交易状态
     */
    @Column(name = "status", length = 50)
    private String status;
    /**
     * 目标平台单号
     */
    @Column(name = "target_order_id", length = 50)
    private String targetOrderId;
    /**
     * 目标系统
     */
    @Column(name = "target_sys", length = 50)
    private String targetSys;
}
