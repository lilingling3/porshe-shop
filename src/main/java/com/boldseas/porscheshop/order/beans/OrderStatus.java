package com.boldseas.porscheshop.order.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 订单状态
 *
 * @author feng
 */
@Entity
@Table(name = "order_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderStatus extends BaseEntity<Long> {
    /**
     * 订单ID
     */
    @Column
    private String orderSn;
    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatusEnum status;

}
