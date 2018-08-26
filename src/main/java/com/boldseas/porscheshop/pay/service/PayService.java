package com.boldseas.porscheshop.pay.service;

import com.boldseas.porscheshop.pay.dto.*;

/**
 * 支付服务
 *
 * @author huisheng.jin
 * @version 2018/5/11.
 */
public interface PayService {

    //region 二维码支付相关

    /**
     * 生成二维码 针对PC端(支付宝，微信扫码支付)
     *
     * @param orderSn
     * @return
     */
    GenerateQrCodeUrlResult generateQrCodeUrl(String orderSn);

    /**
     * 二维码支付回调接口
     *
     * @param callBackInput
     * @return true / false
     */
    Boolean qrCodePayCallBack(CallBackInput callBackInput);

    /**
     * 查询二维码订单支付结果
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    String searchQrCodePayResult(String merchantOrderId);

    /**
     * 同步支付结果（后期运维处理）
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    Boolean syncQrCodePayResult(String merchantOrderId);

    //endregion

    //region wap端支付相关 h5支付宝，微信支付

    /**
     * 生成wap端支付URL
     *
     * @param generateWapPayUrlInput
     * @return
     */
    GenerateWapPayUrlOutput generateWapPayUrl(GenerateWapPayUrlInput generateWapPayUrlInput);

    /**
     * h5支付回调接口
     *
     * @param callBackInput
     * @return true / false
     */
    Boolean h5PayCallBack(CallBackInput callBackInput);

    /**
     * 查询wap端订单支付结果
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    String searchWapPayResult(String merchantOrderId);

    /**
     * 同步支付结果（后期运维处理）
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    Boolean syncWapPayResult(String merchantOrderId);
    //endregion

    //region 银联支付相关

    /**
     * 生成银联支付表单html
     *
     * @param orderSn
     * @param terminal pc or wap
     * @return
     */
    String generateUnionPayFormHtml(String orderSn, String terminal);

    /**
     * 银联回调接口
     *
     * @param callBackInput
     * @return
     */
    boolean unionPayCallBack(GateWayPayCallBackInput callBackInput);


    /**
     * 查询银联订单支付结果
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    String searchUnionPayResult(String merchantOrderId);

    /**
     * 同步支付结果（后期运维处理）
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    Boolean syncUnionPayResult(String merchantOrderId);

    //endregion
}
