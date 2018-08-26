package com.boldseas.porscheshop.api;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.pay.dto.BillPayment;
import com.boldseas.porscheshop.pay.dto.CallBackInput;
import com.boldseas.porscheshop.pay.dto.GateWayPayCallBackInput;
import com.boldseas.porscheshop.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Ums 回调接口类
 *
 * @author huisheng.jin
 * @version 2018/5/15.
 */
@Controller
@RequestMapping("/ums/v0/")
@Slf4j
public class UmsApiController {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    private PayService payService;

    @Autowired
    public UmsApiController(PayService payService) {
        this.payService = payService;
    }

    /**
     * 二维码支付回调地址
     */
    @ResponseBody
    @PostMapping(path = "/api/qr-code/call-back")
    public String qrCodePayCallBack(@ModelAttribute CallBackInput callBackInput) {
        log.info("---qr-code-pay-call-back success ,complete callBackInput:{}", JSON.toJSONString(callBackInput));

        BillPayment billPayment = JSON.parseObject(callBackInput.getBillPayment(), BillPayment.class);
        callBackInput.setBillPaymentObj(billPayment);
        return payService.qrCodePayCallBack(callBackInput) ? SUCCESS : FAILED;
    }

    /**
     * h5支付回调地址
     */
    @ResponseBody
    @PostMapping(path = "/api/wap/call-back")
    public String h5PayCallBack(@ModelAttribute CallBackInput callBackInput) {
        log.info("---h5-pay-call-back success ,complete callBackInput:{}", JSON.toJSONString(callBackInput));
        return payService.h5PayCallBack(callBackInput) ? SUCCESS : FAILED;
    }

    /**
     * 网关支付回调地址
     */
    @ResponseBody
    @PostMapping(path = "/api/gate-way/call-back")
    public String gateWayPayCallBack(@ModelAttribute GateWayPayCallBackInput callBackInput) {
        log.info("---gate-way-pay-call-back success ,complete callBackInput:{}", JSON.toJSONString(callBackInput));
        return payService.unionPayCallBack(callBackInput) ? SUCCESS : FAILED;
    }
}
