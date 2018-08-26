package com.boldseas.porscheshop.pay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/6/5.
 */
@Data
@AllArgsConstructor
public class MerchantDto {
    /**
     * 商户号
     */
    private String merchantId;
    /**
     * 终端号
     */
    private String tid;
}
