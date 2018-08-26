package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.bean.PaymentUnionLog;
import com.boldseas.porscheshop.pay.dto.GateWayPayCallBackInput;
import com.boldseas.porscheshop.pay.dto.GateWayPayDetail;
import com.boldseas.porscheshop.pay.repository.PaymentUnionLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/28.
 */
@Service
public class UnionPaymentLogService {
    private PaymentUnionLogRepository paymentUnionLogRepository;
    private ModelMapper mapper;

    @Autowired
    public UnionPaymentLogService(PaymentUnionLogRepository paymentUnionLogRepository, ModelMapper mapper) {
        this.paymentUnionLogRepository = paymentUnionLogRepository;
        this.mapper = mapper;
    }

    public void saveUnionPayLog(String orderSn, String merchantOrderId, GateWayPayDetail payDetail,String terminal) {
        PaymentUnionLog paymentLog = mapper.map(payDetail, PaymentUnionLog.class);

        // merchantOrderId没有通过mapper映射，如果映射影响签名
        paymentLog.updateOrderDetail(orderSn, merchantOrderId,terminal);
        paymentUnionLogRepository.save(paymentLog);
    }

    public PaymentUnionLog getByMerchantOrderId(String merchantOrderId) {
        return paymentUnionLogRepository.findByOrderId(merchantOrderId);
    }

    public void updateUnionPaymentLog(GateWayPayCallBackInput callBackInput, PaymentUnionLog paymentUnionLog) {
        mapper.map(callBackInput, paymentUnionLog);
        paymentUnionLogRepository.save(paymentUnionLog);
    }
}
