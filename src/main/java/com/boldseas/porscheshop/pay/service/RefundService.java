package com.boldseas.porscheshop.pay.service;

import com.boldseas.porscheshop.dtos.RestResult;
import com.boldseas.porscheshop.pay.bean.RefundCauseLog;
import com.boldseas.porscheshop.pay.dto.RefundInput;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/23.
 */
public interface RefundService{

    /**
     * 订单退款
     * @param refundInput
     * @return
     */
    RestResult orderRefund(RefundInput refundInput);
}
