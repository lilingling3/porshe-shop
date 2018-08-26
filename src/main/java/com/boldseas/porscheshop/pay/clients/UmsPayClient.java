package com.boldseas.porscheshop.pay.clients;

import com.boldseas.porscheshop.pay.dto.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Ums支付服务(除银联支付）
 *
 * @author huisheng.jin
 * @version 2018/5/15.
 */
@Component
@FeignClient(name = "ums-pay-client", url = "${umsPay.ums-host}")
public interface UmsPayClient {

    /**
     * 生成二维码
     *
     * @param qrCodePayUrlInput
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "netpay-route-server/api/")
    QrCodePayUrlOutput generateQrCode(@RequestBody QrCodePayUrlInput qrCodePayUrlInput);

    /**
     * 查询二维码支付结果
     *
     * @param searchQrCodePayDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "netpay-route-server/api/")
    String searchQrCodePayResult(@RequestBody SearchQrCodePayDto searchQrCodePayDto);

    /**
     * 查询wap端订单支付结果
     *
     * @param searchWapPayDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "netpay-route-server/api/")
    String searchWapPayResult(@RequestBody SearchWapPayDto searchWapPayDto);
}
