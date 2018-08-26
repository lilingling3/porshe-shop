package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import com.boldseas.porscheshop.events.OrderStatusChangeEvent;
import com.google.common.eventbus.AsyncEventBus;

/**
 * @author huisheng.jin
 * @version 2018/6/20.
 */
public class BasePayService {

    private final AsyncEventBus eventBus;

    public BasePayService(AsyncEventBus eventBus) {
        this.eventBus = eventBus;
    }

    protected void sendMessage(Boolean isPaySuccess, String orderSn, TerminalEnum terminalEnum, String payType) {
        OrderStatusEnum statusEnum = isPaySuccess ? OrderStatusEnum.paySuccess : OrderStatusEnum.payFailure;
        OrderStatusChangeEvent event = new OrderStatusChangeEvent(orderSn, statusEnum, terminalEnum, payType);

        eventBus.post(event);
    }
}
