package com.boldseas.porscheshop.pay.service.impl;

import com.boldseas.porscheshop.pay.dto.*;
import com.boldseas.porscheshop.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huisheng.jin
 * @version 2018/5/11.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private QrCodePayService qrCodePayService;
    private WapPayService wapPayService;
    private UnionPayService unionPayService;

    @Autowired
    public PayServiceImpl(QrCodePayService qrCodePayService, WapPayService wapPayService, UnionPayService unionPayService) {
        this.qrCodePayService = qrCodePayService;
        this.wapPayService = wapPayService;
        this.unionPayService = unionPayService;
    }

    //region 二维码支付

    @Override
    public GenerateQrCodeUrlResult generateQrCodeUrl(String orderSn) {
        return qrCodePayService.generateQrCodeUrl(orderSn);
    }

    @Override
    public Boolean qrCodePayCallBack(CallBackInput callBackInput) {
        return qrCodePayService.callBack(callBackInput);
    }

    @Override
    public String searchQrCodePayResult(String merchantOrderId) {
        return qrCodePayService.searchPayResult(merchantOrderId);
    }

    @Override
    public Boolean syncQrCodePayResult(String merchantOrderId) {
        return qrCodePayService.syncPayResult(merchantOrderId);
    }

    //endregion

    //region wap端支付（包括支付宝,微信公众号支付,云闪付)

    @Override
    public GenerateWapPayUrlOutput generateWapPayUrl(GenerateWapPayUrlInput generateWapPayUrlInput) {
        return wapPayService.generateWapPayUrl(generateWapPayUrlInput);
    }

    @Override
    public Boolean h5PayCallBack(CallBackInput callBackInput) {
        return wapPayService.callBack(callBackInput);
    }

    @Override
    public String searchWapPayResult(String merchantOrderId) {
        return wapPayService.searchPayResult(merchantOrderId);
    }

    @Override
    public Boolean syncWapPayResult(String merchantOrderId) {
        return wapPayService.syncPayResult(merchantOrderId);
    }

    //endregion

    //region 网关支付

    @Override
    public String generateUnionPayFormHtml(String orderSn, String terminal) {
        return unionPayService.generateGateWayPayFormHtml(orderSn, terminal);
    }

    @Override
    public boolean unionPayCallBack(GateWayPayCallBackInput callBackInput) {
        return unionPayService.callBack(callBackInput);
    }

    @Override
    public String searchUnionPayResult(String merchantOrderId) {
        return unionPayService.searchPayResult(merchantOrderId);
    }

    @Override
    public Boolean syncUnionPayResult(String merchantOrderId) {
        return unionPayService.syncPayResult(merchantOrderId);
    }
    //endregion

}
