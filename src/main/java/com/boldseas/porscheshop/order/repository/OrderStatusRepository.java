package com.boldseas.porscheshop.order.repository;

import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.order.beans.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author feng
 */
@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long>{
    /**
     * 根据orderSn获取订单状态记录
     *
     * @param orderSn
     * @return
     */
    List<OrderStatus> findByOrderSn(String orderSn);

    /**
     * 根据状态和时间查询之前的数据
     * @param statusEnum
     * @param before
     * @return
     */
    List<OrderStatus> findByStatusAndCreateDateBefore(OrderStatusEnum statusEnum, LocalDateTime before);
}
