package com.boldseas.porscheshop.pay.bean;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/23.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "refund_cause_log")
public class RefundCauseLog extends BaseEntity<Integer>{

    /**
     * 订单sn
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 已购其它车型
     */
    @Column(name = "buy_other_car")
    private Boolean buyOtherCar;

    /**
     * 等待期过长
     */
    @Column(name = "waitting_long_time")
    private Boolean waittingLongTime;

    /**
     * 暂缓购车计划
     */
    @Column(name = "postpone_plan")
    private Boolean postponePlan;

    /**
     * 变更购车人信息信息
     */
    @Column(name = "user_info_change")
    private Boolean userInfoChange;

    /**
     * 提车城市变更
     */
    @Column(name = "pick_car_city_change")
    private Boolean pickCarCityChange;

    /**
     * 其他
     */
    @Column(name = "other")
    private Boolean other;

    /**
     * 详细描述
     */
    @Column(name = "detail")
    private String detail;

}
