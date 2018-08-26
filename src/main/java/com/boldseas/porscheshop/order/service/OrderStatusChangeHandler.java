package com.boldseas.porscheshop.order.service;

import com.boldseas.porscheshop.EventListener;
import com.boldseas.porscheshop.docking.DispatchService;
import com.boldseas.porscheshop.dtos.OrderDTO;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.notify.NotifyService;
import com.boldseas.porscheshop.order.beans.Order;
import com.google.common.eventbus.Subscribe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单状态改变处理
 * @author feng
 */
@Service
@EventListener
public class OrderStatusChangeHandler {

    private ModelMapper modelMapper;
    private NotifyService notifyService;
    private DispatchService dispatchService;

    @Autowired
    public OrderStatusChangeHandler(ModelMapper modelMapper, NotifyService notifyService, DispatchService dispatchService) {
        this.modelMapper = modelMapper;
        this.notifyService = notifyService;
        this.dispatchService = dispatchService;
    }

    @Subscribe
    public void statusChangeHandler(Order order){
        OrderDTO orderDTO =modelMapper.map(order,OrderDTO.class);
        sendPaySuccessSMS(orderDTO);
        dispatchPaySuccessOrder(orderDTO);
        sendRefundMail(orderDTO);
    }

    /**
     * 申请退款给经销商发送邮件
     * @param orderDTO
     */
    private void sendRefundMail(OrderDTO orderDTO){
        if(orderDTO.getStatus().equals(OrderStatusEnum.refund)){
            notifyService.sendRefundMail(orderDTO);
        }
    }

    /**
     * 支付成功的订单发送短信
     * @param orderDTO
     */
    private void sendPaySuccessSMS(OrderDTO orderDTO){
        if(orderDTO.getStatus().equals(OrderStatusEnum.paySuccess)){
            notifyService.sendPaySuccessSMS(orderDTO);
        }
    }
    /**
     * 支付成功的订单派发PMP
     * @param orderDTO
     */
    private void dispatchPaySuccessOrder(OrderDTO orderDTO){
        if(orderDTO.getStatus().equals(OrderStatusEnum.paySuccess)) {
            dispatchService.orderDispatch(orderDTO);
        }
    }
}
