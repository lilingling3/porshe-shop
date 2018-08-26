package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 银联支付参数对象
 *
 * @author huisheng.jin
 * @version 2018/5/11.
 */
@Data
public class QrCodePayUrlInput extends BasePayInput {
    /**
     * 账单号
     */
    private String billNo;
    /**
     * 账单描述-- 对应支付调起时的商品详情
     */
    private String billDesc = "购车意向金";

    /**
     * 订单时间
     */
    private String billDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    public void updateOrderDetail(String billNo, int totalAmount, MerchantDto merchantDto) {
        this.billNo = billNo;
        super.setTotalAmount(totalAmount);
        super.setMid(merchantDto.getMerchantId());
        super.setTid(merchantDto.getTid());
    }
}
