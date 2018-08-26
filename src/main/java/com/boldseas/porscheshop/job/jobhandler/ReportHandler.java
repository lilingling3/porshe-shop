package com.boldseas.porscheshop.job.jobhandler;

import com.boldseas.porscheshop.notify.NotifyService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Chen Jingxuan
 * @version 2018/7/11
 */
@JobHandler("sendDailyReportMailHandler")
@Component
public class ReportHandler extends IJobHandler {
    private final NotifyService notifyService;

    @Autowired
    public ReportHandler(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        notifyService.sendDailyReportMail();
        return ReturnT.SUCCESS;
    }
}
