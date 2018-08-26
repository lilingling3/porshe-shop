package com.boldseas.porscheshop.pay.service;

import com.boldseas.porscheshop.pay.dto.MerchantDto;
import com.boldseas.porscheshop.pay.dto.PayTypeEnum;

/**
 * 商户相关服务
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
public interface MerchantService {
    /**
     * 获取某种支付方式的商户信息
     *
     * @param dealerCode
     * @param payTypeEnum
     * @return
     */
    MerchantDto getMerchantId(String dealerCode, PayTypeEnum payTypeEnum);
}
