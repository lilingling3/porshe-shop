package com.boldseas.porscheshop.pay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author huisheng.jin
 * @version 2018/5/18.
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class PayUrlDetail extends BasePayInput {
    /**
     * 商户订单号
     */
    private String merOrderId;
    /**
     * 账单描述-- 对应支付调起时的商品详情
     */
    private String orderDesc = "购车意向金";

    public void updateOrderDetail(String merOrderId, Integer totalAmount, MerchantDto merchant) {
        this.merOrderId = merOrderId;
        super.setTotalAmount(totalAmount);
        super.setMid(merchant.getMerchantId());
        super.setTid(merchant.getTid());
    }

    public Long getId() {
        return null;
    }
}
