package com.boldseas.porscheshop.order.repository;

import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.order.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author feng
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /***
     * 按orderSn查找订单
     * @param orderSn
     * @return
     */
    Order findByOrderSn(String orderSn);

    /**
     * 根据用户ID查询订单
     *
     * @param userId
     * @return
     */
    List<Order> findByUserIdAndValidIsTrue(Long userId);

    /**
     * 根据订单号
     * @param sn 订单号列表
     * @return
     */
    List<Order> findByOrderSnIn(List<String> sn);

    /**
     * 获取指定电话的订单列表
     * @param phones
     * @return
     */
    List<Order> findByPhoneIn(List<String> phones);

    /**
     * 获取某一时点之前的所有订单
     * @param localDateTime
     * @return
     */
    List<Order> findByCreateDateBefore(LocalDateTime localDateTime);
}
