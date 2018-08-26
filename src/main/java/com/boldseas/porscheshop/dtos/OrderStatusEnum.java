package com.boldseas.porscheshop.dtos;

/**
 * @author feng
 */

public enum OrderStatusEnum {
    /**
     * 待支付
     */
    waitingPay("待支付"),
    /**
     * 已支付
     */
    paySuccess("已支付"),
    /**
     * 退款进行中
     */
    refund("退款申请中"),
    /**
     * 退款成功
     */
    refundSuccess("退款成功"),
    /**
     * 支付失败
     */
    payFailure("支付失败"),
    /**
     * 已删除
     */
    deleted("已删除");

    private String desc;

    /**
     * 私有构造
     * @param desc
     */
    OrderStatusEnum(String desc){
        this.desc=desc;
    }

    /**
     * 返回描述
     * @return
     */
    public String getDesc(){
        return desc;
    }
}
