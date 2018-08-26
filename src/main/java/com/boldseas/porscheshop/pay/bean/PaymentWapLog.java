package com.boldseas.porscheshop.pay.bean;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;

/**
 * h5支付记录
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payment_wap_log")
@Data
public class PaymentWapLog extends BaseEntity<Long> {

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
     * 商户订单编号
     */
    @Column(name = "merchant_order_id", length = 50)
    private String merOrderId;
    /**
     * 账单状态
     * NEW_ORDER 新订单
     * UNKNOWN 不明确的交易状态
     * TRADE_CLOSED 在指定时间段内未支付时关闭的交易；在交易完成全额退款成功时关闭的交易；支付失败的交易。
     * WAIT_BUYER_PAY 交易创建，等待买家付款。
     * TRADE_SUCCESS 支付成功
     * TRADE_REFUND 订单转入退货流程
     */
    @Column(name = "status", length = 50)
    private String status;

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

    public Boolean isCallBacked() {
        return !StringUtils.isEmpty(status);
    }

    @Override
    public void setId(Long id) {

    }
}
