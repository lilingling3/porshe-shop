package com.boldseas.porscheshop.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/23.
 */
@Data
@ApiModel
public class RefundInput {

    /**
     * 订单sn
     */
    @ApiModelProperty(value = "订单号")
    private String orderSn;

    /**
     * 已购其它车型
     */
    @ApiModelProperty(value = "已购其它车型")
    private Boolean buyOtherCar;

    /**
     * 等待期过长
     */
    @ApiModelProperty(value = "等待期过长")
    private Boolean waittingLongTime;

    /**
     * 暂缓购车计划
     */
    @ApiModelProperty(value = "暂缓购车计划")
    private Boolean postponePlan;

    /**
     * 变更购车人信息信息
     */
    @ApiModelProperty(value = "变更购车人信息信息")
    private Boolean userInfoChange;


    /**
     * 提车城市变更
     */
    @ApiModelProperty(value = "提车城市变更")
    private Boolean pickCarCityChange;

    /**
     * 其他
     */
    @ApiModelProperty(value = "其他")
    private Boolean other;

    /**
     * 详细描述
     */
    @ApiModelProperty(value = "详细描述")
    private String detail;
}
