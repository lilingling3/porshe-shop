package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

/**
 * 支付回调详情信息
 *
 * @author huisheng.jin
 * @version 2018/5/15.
 */
@Data
public class BillPayment {

    /**
     * 账单业务类型
     */
    private String billBizType;
    /**
     * 开票金额
     */
    private Integer invoiceAmount;
    /**
     * 商户订单号
     */
    private String merOrderId;
    /**
     * 交易参考号
     */
    private String paySeqId;
    /**
     * 账单流水总金额
     */
    private Integer totalAmount;
    /**
     * 实付金额
     */
    private Integer buyerPayAmount;
    /**
     * 钱包折扣金额
     */
    private Integer couponAmount;
    /**
     * 折扣金额
     */
    private Integer discountAmount;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 买家用户名
     */
    private String buyerUsername;
    /**
     * 支付详情
     */
    private String payDetail;
    /**
     * 支付时间，格式yyyy-MM-dd HH:mm:ss
     */
    private String payTime;
    /**
     * 结算时间，格式yyyy-MM-dd
     */
    private String settleDate;
    /**
     * 交易状态
     */
    private String status;
    /**
     * 目标平台单号
     */
    private String targetOrderId;
    /**
     * 目标系统
     */
    private String targetSys;

    public Long getId() {
        return null;
    }
}
