package com.boldseas.porscheshop.ui.api;

import com.boldseas.porscheshop.dtos.RestResult;
import com.boldseas.porscheshop.pay.dto.*;
import com.boldseas.porscheshop.pay.service.PayService;
import com.boldseas.porscheshop.pay.service.RefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * 支付Api
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@RestController
@RequestMapping("/api/v0/pay/")
@Api(description = "支付相关接口")
public class PayApiController {

    private PayService payService;
    private RefundService refundService;

    @Autowired
    public PayApiController(PayService payService, RefundService refundService) {
        this.payService = payService;
        this.refundService = refundService;
    }

    /**
     * 生成二维码地址(PC端支付)
     *
     * @param orderSn
     * @return
     */
    @ApiOperation("根据订单号生成支付二维码url")
    @PostMapping(path = "qr-code/{orderSn}")
    public GenerateQrCodeUrlResult generateQrCode(@ApiParam(required = true, value = "订单号", example = "DAD00723B06D553Y") @PathVariable("orderSn") String orderSn) {
        return payService.generateQrCodeUrl(orderSn);
    }

    /**
     * 二维码支付结果查询
     *
     * @return 返回支付结果字符串 （先不用对象接收，只用来进行展示察看，不做下一步业务处理）
     */
    @ApiOperation("查询二维码支付结果")
    @PostMapping(path = "/qr-code-pay-result/{merchantOrderId}")
    public String searchQrCodePayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "3194000414547743") @PathVariable String merchantOrderId) {
        return payService.searchQrCodePayResult(merchantOrderId);
    }

    /**
     * 同步二维码支付结果
     *
     * @return 返回支付结果字符串 （先不用对象接收，只用来进行展示察看，不做下一步业务处理）
     */
    @ApiOperation("同步二维码支付结果")
    @PostMapping(path = "/sync-qr-code-pay-result/{merchantOrderId}")
    public Boolean syncQrCodePayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "3194000414547743") @PathVariable String merchantOrderId) {
        return payService.syncQrCodePayResult(merchantOrderId);
    }

    /**
     * 生成支付跳转地址(WAP端支付宝h5支付，微信公众号支付）
     *
     * @param generateWapPayUrlInput
     * @return
     */
    @ApiOperation("生成wap端订单支付地址(不包括银联支付)")
    @PostMapping(path = "wap-pay-url")
    public GenerateWapPayUrlOutput generateWapPayUrl(@Valid @RequestBody GenerateWapPayUrlInput generateWapPayUrlInput) throws UnsupportedEncodingException {
        return payService.generateWapPayUrl(generateWapPayUrlInput);
    }

    /**
     * wap端支付结果查询
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return 返回支付结果字符串
     */
    @ApiOperation("查询WAP端支付结果")
    @PostMapping(path = "/wap-pay-result/{merchantOrderId}")
    public String searchWapPayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "3194000414547743") @PathVariable String merchantOrderId) {
        return payService.searchWapPayResult(merchantOrderId);
    }

    /**
     * 同步wap端支付结果
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return 返回支付结果字符串
     */
    @ApiOperation("同步wap端支付结果")
    @PostMapping(path = "/sync-wap-pay-result/{merchantOrderId}")
    public Boolean syncWapPayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "3194000414547743") @PathVariable String merchantOrderId) {
        return payService.syncWapPayResult(merchantOrderId);
    }

    /**
     * 银联支付结果查询
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    @ApiOperation("查询银联支付结果")
    @PostMapping(path = "/union-pay-result/{merchantOrderId}")
    public String searchUnionPayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "908057E5E4F1440199EAC67AB5754DE0") @PathVariable String merchantOrderId) {
        return payService.searchUnionPayResult(merchantOrderId);
    }

    /**
     * 同步银联支付结果
     *
     * @param merchantOrderId 订单商户号 ---针对支付的订单号，不是商城的
     * @return
     */
    @ApiOperation("同步银联支付结果")
    @PostMapping(path = "/sync-union-pay-result/{merchantOrderId}")
    public Boolean syncUnionPayResult(@ApiParam(required = true, value = "支付商户订单号(不是商城订单号)", example = "908057E5E4F1440199EAC67AB5754DE0") @PathVariable String merchantOrderId) {
        return payService.syncUnionPayResult(merchantOrderId);
    }

    /**
     * 订单退款申请
     *
     * @param refundInput
     * @return
     */
    @ApiOperation("订单退款")
    @PostMapping(path = "/refund")
    public RestResult orderRefund(@RequestBody RefundInput refundInput) {
        return refundService.orderRefund(refundInput);
    }
}
