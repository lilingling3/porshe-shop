package com.boldseas.porscheshop.order;

import com.boldseas.porscheshop.dtos.OrderDTO;
import com.boldseas.porscheshop.dtos.OrderStatusDTO;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.order.beans.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author feng
 */
public interface OrderService {

    /***
     * 生成订单
     * @param order
     * @return
     */
    Order generateOrder(OrderDTO order);

    /***
     * 根据SN查询订单
     * @param orderSn
     * @return
     */
    Order getOrderBySn(String orderSn);

    /**
     * 用户是否存在已有效订单
     * @param userId
     * @return
     */
    boolean isExistValidOrder(Long userId);

    /**
     * 查询用户的所有订单
     * @param userId
     * @return
     */
    List<OrderDTO> getUserOrders(Long userId);

    /**
     * 查询一天前累计支付成功的订单
     * @return
     */
    List<OrderDTO> getPaySuccessOrdersBeForeToday();
    /**
     * 查询用户的所有订单完善信息包含支付时间
     * @param userId
     * @return
     */
    List<OrderDTO> getUserOrdersCompleteInfo(Long userId);

    /**
     * 查询订单状态
     * @param sn
     * @return
     */
    List<OrderStatusDTO> getOrderStatusBySn(String sn);

    /**
     * 查询订单完善信息包含支付时间
     * @param orderSn
     * @return
     */
    OrderDTO getOrderCompleteInfo(String orderSn);

    /**
     * 删除订单
     * 修改订单状态为已删除，订单置为无效
     * @param order
     */
    void deleteOrder(Order order);

    /**
     * 获取某一时点之前的所有订单
     * @param localDateTime
     * @return
     */
    List<OrderDTO> findBefore(LocalDateTime localDateTime);
}
