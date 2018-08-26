package com.boldseas.porscheshop.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

/**
 * @author feng
 */
@Data
public class OrderStatusDTO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 订单状态
     */
    private OrderStatusEnum status;
    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}
