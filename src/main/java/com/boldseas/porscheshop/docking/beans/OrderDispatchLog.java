package com.boldseas.porscheshop.docking.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Chen Jingxuan
 * @version 2018/5/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "order_dispatch_log")
public class OrderDispatchLog extends BaseEntity<Long> {
    /**
     * order sn
     */
    @Column(name = "order_sn", length = 20)
    private String orderSn;
    /**
     * 派发是否成功
     */
    @Column(name = "success")
    private boolean success;
    /**
     * 派发异常简要信息
     */
    @Column(name = "error_message", length = 1000)
    private String errorMessage;


    public OrderDispatchLog(boolean success, String errorMessage, String orderSn) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.orderSn = orderSn;
    }
}
