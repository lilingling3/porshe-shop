package com.boldseas.porscheshop.dtos;

import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/05/30
 */
@Data
public class OrderStatusChangeDTO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 终端(PC ,WAP)
     */
    private TerminalEnum terminalEnum;
    /**
     * 支付方式 (WXPay,Alipay 2.0 ,ACP)
     */
    private String payType;
    /**
     * 状态
     */
    private OrderStatusEnum statusEnum;
}
