package com.boldseas.porscheshop.notify;

import com.boldseas.porscheshop.dtos.OrderDTO;

/**
 * @author feng
 */
public interface NotifyService {
    /**
     * 发送订单支付成功短信
     * @param order
     */
    void sendPaySuccessSMS(OrderDTO order);

    /**
     * 给经销商发送用户申请退款邮件
     * @param order
     */
    void sendRefundMail(OrderDTO order);

    /**
     * 给经销商发送前一天支付成功的订单邮件
     */
    void sendYesterdayPaySuccessMail();

    /**
     * 保时捷商城日报邮件
     */
    void sendDailyReportMail();
}
