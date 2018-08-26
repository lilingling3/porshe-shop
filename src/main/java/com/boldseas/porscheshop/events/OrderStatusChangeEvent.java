package com.boldseas.porscheshop.events;

import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import lombok.Data;

/**
 * @author fei.ye
 * @version 2018/5/10.
 */
@Data
public class OrderStatusChangeEvent {
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

    public OrderStatusChangeEvent(String orderSn, OrderStatusEnum orderStatusEnum) {
        updateOrderStatus(orderSn, orderStatusEnum);
    }

    public OrderStatusChangeEvent(String orderSn, OrderStatusEnum orderStatusEnum, TerminalEnum terminalEnum, String payType) {
        updateOrderStatus(orderSn, orderStatusEnum);
        this.terminalEnum = terminalEnum;
        this.payType = payType;
    }

    private void updateOrderStatus(String orderSn, OrderStatusEnum orderStatusEnum) {
        this.orderSn = orderSn;
        this.statusEnum = orderStatusEnum;
    }

}
