package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.dtos.RestResult;
import com.boldseas.porscheshop.events.OrderStatusChangeEvent;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.pay.bean.RefundCauseLog;
import com.boldseas.porscheshop.pay.dto.RefundInput;
import com.boldseas.porscheshop.pay.repository.RefundCauseLogRepository;
import com.boldseas.porscheshop.pay.service.RefundService;
import com.google.common.eventbus.AsyncEventBus;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/23.
 */
@Slf4j
@Service
public class RefundServiceImpl implements RefundService{

    private RefundCauseLogRepository refundCauseLogRepository;
    private AsyncEventBus asyncEventBus;
    private OrderService orderService;
    private ModelMapper mapper;

    @Autowired
    public RefundServiceImpl(RefundCauseLogRepository refundCauseLogRepository, ModelMapper mapper,
                             AsyncEventBus asyncEventBus,OrderService orderService){
        this.refundCauseLogRepository =refundCauseLogRepository;
        this.asyncEventBus = asyncEventBus;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @Override
    public RestResult orderRefund(RefundInput refundInput){
        Order order = orderService.getOrderBySn(refundInput.getOrderSn());
        if(!OrderStatusEnum.paySuccess.equals(order.getStatus())){
            return new RestResult<RefundCauseLog>(false,"退款失败");
        }
        RefundCauseLog refundCauseLog = refundCauseLogRepository.save(mapper.map(refundInput, RefundCauseLog.class));
        log.info(String.format("send refund event------orderSn = %s",refundInput.getOrderSn()));
        postRefundMessage(refundInput.getOrderSn());
        return new RestResult<>(true, refundCauseLog);
    }

    /**
     * 发送退款通知
     * @param orderSn
     */
    private void postRefundMessage(String orderSn){
        OrderStatusChangeEvent orderStatusChangeEvent = new OrderStatusChangeEvent(orderSn,OrderStatusEnum.refund);
        asyncEventBus.post(orderStatusChangeEvent);
    }
}
