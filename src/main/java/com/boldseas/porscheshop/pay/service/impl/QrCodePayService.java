package com.boldseas.porscheshop.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.pay.bean.PaymentQrCodeLog;
import com.boldseas.porscheshop.pay.clients.UmsPayClient;
import com.boldseas.porscheshop.pay.config.BusinessType;
import com.boldseas.porscheshop.pay.config.PayConfig;
import com.boldseas.porscheshop.pay.dto.*;
import com.boldseas.porscheshop.pay.service.MerchantService;
import com.boldseas.porscheshop.pay.utils.UmsPayUtils;
import com.google.common.eventbus.AsyncEventBus;
import jdk.nashorn.internal.codegen.CompilerConstants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/6/20.
 */
@Service
@Slf4j
public class QrCodePayService extends BasePayService {
    private OrderService orderService;
    private MerchantService merchantService;
    private UmsPayClient umsPayClient;
    private QrCodePaymentLogService qrCodePaymentLogService;
    private ModelMapper mapper;
    private PayConfig payConfig;

    @Autowired
    public QrCodePayService(AsyncEventBus eventBus, OrderService orderService,
                            MerchantService merchantService,
                            UmsPayClient umsPayClient, QrCodePaymentLogService qrCodePaymentLogService,
                            ModelMapper mapper, PayConfig payConfig) {
        super(eventBus);
        this.orderService = orderService;
        this.merchantService = merchantService;
        this.umsPayClient = umsPayClient;
        this.qrCodePaymentLogService = qrCodePaymentLogService;
        this.mapper = mapper;
        this.payConfig = payConfig;
    }

    //region 二维码支付

    public GenerateQrCodeUrlResult generateQrCodeUrl(String orderSn) {
        Order order = orderService.getOrderBySn(orderSn);
        MerchantDto merchant = merchantService.getMerchantId(order.getDealerCode(), PayTypeEnum.qrCode);

        QrCodePayUrlInput input = createQrCodePayUrlInput(merchant, order.getPrice());
        QrCodePayUrlOutput output = umsPayClient.generateQrCode(input);

        if (!output.isSuccess()) {
            log.error("---generate qrCode failure,qrCodeInput:{},qrCodePayOutput:{}", input, output);
            return new GenerateQrCodeUrlResult(false, output.getErrMsg());
        }

        log.info("---generate qrCode result,qrCodeInput:{},qrCodeOutput:{}", input, output);
        qrCodePaymentLogService.saveQrCodePayLog(order.getOrderSn(), output);
        return new GenerateQrCodeUrlResult(true, output.getBillQRCode());
    }

    public Boolean callBack(CallBackInput callBackInput) {
        return syncPayResult(callBackInput);
    }

    private QrCodePayUrlInput createQrCodePayUrlInput(MerchantDto merchant, Double price) {
        QrCodePayUrlInput qrCodePayUrlInput = mapper.map(payConfig, QrCodePayUrlInput.class);

        BusinessType businessType = payConfig.getBusinessType(PayTypeEnum.qrCode);
        mapper.map(businessType, qrCodePayUrlInput);

        qrCodePayUrlInput.updateOrderDetail(payConfig.generateMerchantOrderId(), price.intValue(), merchant);
        qrCodePayUrlInput.createSign(payConfig.getPrivateKey());
        return qrCodePayUrlInput;
    }

    public String searchPayResult(String merchantOrderId) {
        return search(merchantOrderId);
    }

    public Boolean syncPayResult(String merchantOrderId) {
        String result = search(merchantOrderId);
        CallBackInput input = JSON.parseObject(result, CallBackInput.class);
        input.setBillPaymentObj(JSON.parseObject(input.getBillPayment(), BillPayment.class));

        return syncPayResult(input);
    }

    private Boolean syncPayResult(CallBackInput callBackInput) {
        PaymentQrCodeLog paymentQrCodeLog = qrCodePaymentLogService.getByBillNo(callBackInput.getBillNo());
        if (paymentQrCodeLog.isCallBacked()) {
            return true;
        }

        qrCodePaymentLogService.updateQrCodePaymentLog(callBackInput, paymentQrCodeLog);
        sendMessage(callBackInput.isSuccess(), paymentQrCodeLog.getOrderSn(), TerminalEnum.pc, callBackInput.getBillPaymentObj().getTargetSys());
        return true;
    }

    private String search(String merchantOrderId) {
        PaymentQrCodeLog paymentQrCodeLog = qrCodePaymentLogService.getByBillNo(merchantOrderId);
        SearchQrCodePayDto searchQrCodePayDto = mapper.map(paymentQrCodeLog, SearchQrCodePayDto.class);
        searchQrCodePayDto.updateMsgType();

        String sign = UmsPayUtils.generateSign(searchQrCodePayDto, payConfig.getPrivateKey());
        searchQrCodePayDto.updateSign(sign);

        return umsPayClient.searchQrCodePayResult(searchQrCodePayDto);
    }

    //endregion

}
