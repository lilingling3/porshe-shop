package com.boldseas.porscheshop.job.datajob;

import com.boldseas.porscheshop.common.config.TimerConfig;
import com.boldseas.porscheshop.notify.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author feng
 */
@Component
public class OrderJob {
    private NotifyService notifyService;
    private TimerConfig timerConfig;

    @Autowired
    public OrderJob(NotifyService notifyService, TimerConfig timerConfig) {
        this.notifyService = notifyService;
        this.timerConfig = timerConfig;
    }

    /**
     * 每天早9点发送前一天支付成功的订单
     * @return
     */
    //@Scheduled(cron = "${timer.sendPaySuccessForDealer}")

    public void sendPaySuccessOrderForYesterday() {
        if (timerConfig.getRun()) {
            notifyService.sendYesterdayPaySuccessMail();
        }
    }
}
