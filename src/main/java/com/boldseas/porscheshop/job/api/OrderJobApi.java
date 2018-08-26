package com.boldseas.porscheshop.job.api;

import com.boldseas.porscheshop.notify.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author feng
 */
@RestController
@RequestMapping("order/job")
public class OrderJobApi {

    private NotifyService notifyService;

    @Autowired
    public OrderJobApi(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @GetMapping("/sendPaySuccessOrderForYesterday")
    public String sendPaySuccessOrderForYesterday(){
        notifyService.sendYesterdayPaySuccessMail();
        return "SUCCESS";
    }
}
