package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.bean.PaymentWapLog;
import com.boldseas.porscheshop.pay.dto.CallBackInput;
import com.boldseas.porscheshop.pay.dto.PayUrlDetail;
import com.boldseas.porscheshop.pay.repository.PaymentWapLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/28.
 */
@Service
public class WapPaymentLogService {
    private PaymentWapLogRepository paymentWapLogRepository;
    private ModelMapper mapper;

    @Autowired
    public WapPaymentLogService(PaymentWapLogRepository paymentWapLogRepository, ModelMapper mapper) {
        this.paymentWapLogRepository = paymentWapLogRepository;
        this.mapper = mapper;
    }

    public PaymentWapLog getByMerOrderId(String merOrderId) {
        return paymentWapLogRepository.findByMerOrderId(merOrderId);
    }

    public void updateWapPaymentLog(CallBackInput callBackInput, PaymentWapLog paymentWapLog) {
        mapper.map(callBackInput, paymentWapLog);
        paymentWapLogRepository.save(paymentWapLog);
    }

    public void saveWapPayLog(String orderSn, PayUrlDetail payUrlDetail) {
        PaymentWapLog paymentWapLog = mapper.map(payUrlDetail, PaymentWapLog.class);
        paymentWapLog.setOrderSn(orderSn);
        paymentWapLogRepository.save(paymentWapLog);
    }
}
