package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/5/15.
 */
@Data
public class CallBackInput {
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
     * 账单总金额
     */
    private Integer totalAmount;
    /**
     * 担保状态
     */
    private String secureStatus;
    /**
     * 担保完成金额（分）
     */
    private Integer completeAmount;
    /**
     * 支付通知ID
     */
    private String notifyId;
    /**
     * 签名
     */
    private String sign;

    //region 扫码支付独有参数

    /**
     * 账单号，原样返回
     */
    private String billNo;
    /**
     * 账单二维码
     */
    private String billQRCode;
    /**
     * 账单状态
     */
    private String billStatus;
    /**
     * 账单日期，原样返回
     */
    private String billDate;
    /**
     * 账单描述
     */
    private String billDesc;
    /**
     * 会员号
     */
    private String memberId;
    /**
     * 桌号、柜台号、房间号
     */
    private String counterNo;
    /**
     * 商户名称
     */
    private String merName;
    /**
     * 付款附言
     */
    private String memo;

    private String billPayment;

    private BillPayment billPaymentObj;

    //endregion

    //region h5支付 独有参数
    /**
     * 附加数据
     */
    private String attachedData;
    /**
     * 支付银行信息
     */
    private String bankCardNo;
    /**
     * 银行信息
     */
    private String bankInfo;
    /**
     * 资金渠道
     */
    private String billFunds;

    /**
     * 资金渠道说明
     */
    private String billFundsDesc;
    /**
     * 买家ID
     */
    private String buyerId;
    /**
     * 买家用户名
     */
    private String buyerUsername;

    /**
     * 网付计算的优惠金额
     */
    private Integer couponAmount;
    /**
     * 实付金额
     */
    private Integer buyerPayAmount;
    /**
     * 订单金额，单位分
     */
    private Integer invoiceAmount;
    /**
     * 商户订单号
     */
    private String merOrderId;
    /**
     * 支付时间，格式yyyy-MM-dd HH:mm:ss
     */
    private String payTime;
    /**
     * 实收金额
     */
    private Integer receiptAmount;
    /**
     * 支付银行卡参考号
     */
    private String refId;
    /**
     * 退款金额
     */
    private Integer refundAmount;

    /**
     * 退款说明
     */
    private String refundDesc;
    /**
     * 系统交易流水号
     */
    private String seqId;
    /**
     * 结算日期，格式yyyy-MM-dd
     */
    private String settleDate;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 卖家子ID
     */
    private String subBuyerId;
    /**
     * 渠道订单号
     */
    private String targetOrderId;
    /**
     * 支付渠道
     */
    private String targetSys;
    // endregion

    public Boolean isSuccess() {
        return "PAID".equals(this.billStatus) || "TRADE_SUCCESS".equals(this.status);
    }

    public Long getId() {
        return null;
    }
}