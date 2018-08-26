package com.boldseas.porscheshop.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.pay.bean.PaymentWapLog;
import com.boldseas.porscheshop.pay.clients.UmsPayClient;
import com.boldseas.porscheshop.pay.config.BusinessType;
import com.boldseas.porscheshop.pay.config.PayConfig;
import com.boldseas.porscheshop.pay.dto.*;
import com.boldseas.porscheshop.pay.service.MerchantService;
import com.boldseas.porscheshop.pay.utils.UmsPayUtils;
import com.google.common.eventbus.AsyncEventBus;
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
public class WapPayService extends BasePayService {
    private WapPaymentLogService wapPaymentLogService;
    private OrderService orderService;
    private MerchantService merchantService;
    private PayConfig payConfig;
    private ModelMapper mapper;
    private UmsPayClient umsPayClient;
    private UserProvider userProvider;

    @Autowired
    public WapPayService(AsyncEventBus eventBus, WapPaymentLogService wapPaymentLogService,
                         OrderService orderService, MerchantService merchantService,
                         PayConfig payConfig, ModelMapper mapper,
                         UmsPayClient umsPayClient,UserProvider userProvider) {
        super(eventBus);
        this.wapPaymentLogService = wapPaymentLogService;
        this.orderService = orderService;
        this.merchantService = merchantService;
        this.payConfig = payConfig;
        this.mapper = mapper;
        this.umsPayClient = umsPayClient;
        this.userProvider = userProvider;
    }

    public Boolean callBack(CallBackInput callBackInput) {
        return syncPayResult(callBackInput);
    }

    public GenerateWapPayUrlOutput generateWapPayUrl(GenerateWapPayUrlInput generateWapPayUrlInput) {
        PayTypeEnum payTypeEnum = PayTypeEnum.valueOf(generateWapPayUrlInput.getPayType());

        Order order = orderService.getOrderBySn(generateWapPayUrlInput.getOrderSn());
        MerchantDto merchant = merchantService.getMerchantId(order.getDealerCode(), payTypeEnum);
        BusinessType businessType = payConfig.getBusinessType(payTypeEnum);

        PayUrlDetail payUrlDetail = createPayUrlDetail(merchant, order.getPrice(), businessType, order.getOrderSn());
        String url = generatePayUrl(payUrlDetail, businessType.getPayUrl());

        log.info("---generate pay url result,generateWapPayUrl:{},payUrlDetail:{},pay url:{}", generateWapPayUrlInput, payUrlDetail, url);
        wapPaymentLogService.saveWapPayLog(order.getOrderSn(), payUrlDetail);
        return new GenerateWapPayUrlOutput(true, url);
    }

    private PayUrlDetail createPayUrlDetail(MerchantDto merchant, Double price, BusinessType businessType, String shopOrderSn) {
        PayUrlDetail payUrlDetail = mapper.map(payConfig, PayUrlDetail.class);
        mapper.map(businessType, payUrlDetail);

        payUrlDetail.updateReturnUrl(shopOrderSn,userProvider.getUserSource());
        payUrlDetail.updateOrderDetail(payConfig.generateMerchantOrderId(), price.intValue(), merchant);
        payUrlDetail.createSign(payConfig.getPrivateKey());

        return payUrlDetail;
    }

    private String generatePayUrl(PayUrlDetail payUrlDetail, String payUrl) {
        return String.format("%s?%s", payUrl, UmsPayUtils.generateUrlParams(payUrlDetail));
    }

    public String searchPayResult(String merchantOrderId) {
        return search(merchantOrderId);
    }

    public Boolean syncPayResult(String merchantOrderId) {
        String payResult = search(merchantOrderId);
        CallBackInput input = JSON.parseObject(payResult, CallBackInput.class);

        return syncPayResult(input);
    }

    private String search(String merchantOrderId) {
        PaymentWapLog paymentWapLog = wapPaymentLogService.getByMerOrderId(merchantOrderId);
        SearchWapPayDto searchWapPayDto = mapper.map(paymentWapLog, SearchWapPayDto.class);
        searchWapPayDto.updateMsgType();

        String sign = UmsPayUtils.generateSign(searchWapPayDto, payConfig.getPrivateKey());
        searchWapPayDto.updateSign(sign);

        return umsPayClient.searchWapPayResult(searchWapPayDto);
    }

    private Boolean syncPayResult(CallBackInput callBackInput) {
        PaymentWapLog paymentWapLog = wapPaymentLogService.getByMerOrderId(callBackInput.getMerOrderId());
        if (paymentWapLog.isCallBacked()) {
            return true;
        }

        wapPaymentLogService.updateWapPaymentLog(callBackInput, paymentWapLog);
        sendMessage(callBackInput.isSuccess(), paymentWapLog.getOrderSn(), TerminalEnum.wap, callBackInput.getTargetSys());
        return true;
    }
}
