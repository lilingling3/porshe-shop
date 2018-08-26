package com.boldseas.porscheshop.job.jobhandler;

import com.boldseas.porscheshop.pay.service.PayService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author huisheng.jin
 * @version 2018/6/25.
 */
@JobHandler("sycQrCodePayResultHandler")
@Component
public class SycQrCodePayResultHandler extends IJobHandler {
    private PayService payService;

    @Autowired
    public SycQrCodePayResultHandler(PayService payService) {
        this.payService = payService;
    }

    /**
     * @param merchantOrderId 商户订单号
     * @return
     */
    @Override
    public ReturnT<String> execute(String merchantOrderId) {
        XxlJobLogger.log("------ SycQrCodePayResult Start merchantOrderId:{0}------",merchantOrderId);
        payService.syncQrCodePayResult(merchantOrderId);
        XxlJobLogger.log("------ SycQrCodePayResult End merchantOrderId:{0}------",merchantOrderId);
        return ReturnT.SUCCESS;
    }
}
