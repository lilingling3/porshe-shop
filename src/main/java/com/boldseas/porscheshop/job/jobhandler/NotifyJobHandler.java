package com.boldseas.porscheshop.job.jobhandler;


import com.boldseas.porscheshop.notify.NotifyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@JobHandler("sendPaySuccessOrdersBeforeYesterdayJobHandler")
@Component
public class NotifyJobHandler extends IJobHandler {

    private NotifyService notifyService;

    @Autowired
    public NotifyJobHandler(NotifyService notifyService) {
        this.notifyService = notifyService;
    }
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        notifyService.sendYesterdayPaySuccessMail();
        return ReturnT.SUCCESS;
    }
}
