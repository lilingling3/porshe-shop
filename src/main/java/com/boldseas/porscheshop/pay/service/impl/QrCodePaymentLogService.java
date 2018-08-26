package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.bean.PaymentQrCodeLog;
import com.boldseas.porscheshop.pay.bean.PaymentQrCodeLogDetail;
import com.boldseas.porscheshop.pay.dto.CallBackInput;
import com.boldseas.porscheshop.pay.dto.QrCodePayUrlOutput;
import com.boldseas.porscheshop.pay.repository.PaymentQrCodeLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/28.
 */
@Service
public class QrCodePaymentLogService {
    private PaymentQrCodeLogRepository paymentQrCodeLogRepository;
    private ModelMapper mapper;

    @Autowired
    public QrCodePaymentLogService(PaymentQrCodeLogRepository paymentQrCodeLogRepository, ModelMapper mapper) {
        this.paymentQrCodeLogRepository = paymentQrCodeLogRepository;
        this.mapper = mapper;
    }

    public PaymentQrCodeLog getByBillNo(String billNo) {
        return paymentQrCodeLogRepository.findByBillNo(billNo);
    }

    public void updateQrCodePaymentLog(CallBackInput callBackInput, PaymentQrCodeLog paymentQrCodeLog) {
        mapper.map(callBackInput, paymentQrCodeLog);
        PaymentQrCodeLogDetail paymentQrCodeLogDetail = mapper.map(callBackInput.getBillPaymentObj(), PaymentQrCodeLogDetail.class);
        paymentQrCodeLog.setPaymentQrCodeLogDetail(paymentQrCodeLogDetail);

        paymentQrCodeLogRepository.save(paymentQrCodeLog);
    }

    public void saveQrCodePayLog(String orderSn, QrCodePayUrlOutput unionPayOutput) {
        PaymentQrCodeLog paymentQrCodeLog = mapper.map(unionPayOutput, PaymentQrCodeLog.class);
        paymentQrCodeLog.updateOrderDetail(orderSn, unionPayOutput.getBillQRCode());
        paymentQrCodeLogRepository.save(paymentQrCodeLog);
    }
}
