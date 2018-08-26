package com.boldseas.porscheshop.ui;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.order.OrderService;
import com.boldseas.porscheshop.order.beans.Order;
import com.boldseas.porscheshop.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    private static final String TERMINAL_PC = "pc";
    private static final String TERMINAL_WAP = "wap";
    private final OrderService orderService;
    private PayService payService;

    @Autowired
    public PayController(OrderService orderService, PayService payService) {
        this.orderService = orderService;
        this.payService = payService;
    }

    @RequestMapping(path = "/index")
    public String index(@RequestParam(value = "orderSn") String orderSn, ModelMap modelMap, HttpServletRequest request) {
        Order order = orderService.getOrderBySn(orderSn);
        if (order.getStatus().equals(OrderStatusEnum.paySuccess)) {
            return "redirect:/account";
        }
        modelMap.put("order", order);
        return "pay/index";
    }

    @RequestMapping(path = "/pc-gate-way/{orderSn}")
    @ResponseBody
    public String pcGateWay(@PathVariable String orderSn) {
        return payService.generateUnionPayFormHtml(orderSn, TERMINAL_PC);
    }

    @RequestMapping(path = "/wap-gate-way/{orderSn}")
    @ResponseBody
    public String wapGateWay(@PathVariable String orderSn) {
        return payService.generateUnionPayFormHtml(orderSn, TERMINAL_WAP);
    }

    @RequestMapping(path = "/success/{sn}", method = {RequestMethod.GET, RequestMethod.POST})
    public String paySuccess(@PathVariable String sn, ModelMap modelMap) {
        Order order = orderService.getOrderBySn(sn);
        if (order.getStatus() != OrderStatusEnum.paySuccess) {
            return "redirect:/pay/index?orderSn=" + sn;
        }

        modelMap.put("order", order);
        return "pay/success";
    }

    @GetMapping("/initiate-refund/{sn}")
    public String initiateRefund(@PathVariable String sn, ModelMap modelMap) {
        modelMap.addAttribute("order", JSON.toJSONString(orderService.getOrderBySn(sn)));
        return "pay/initiate_refund";
    }
}
