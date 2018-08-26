package com.boldseas.porscheshop.ui;

import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单预定
 * @author yujie.li
 * @version 2018/5/11
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private UserProvider userProvider;

    @Autowired
    public OrderController(OrderService orderService,UserProvider userProvider){
        this.orderService = orderService;
        this.userProvider = userProvider;
    }

    /**
     * 订单生成页
     * @return
     */
    @GetMapping("/generate")
    public String completeInfo() {
        Long userId = userProvider.getCurrentUser().getId();
        if(orderService.isExistValidOrder(userId)){
            return "redirect:/account";
        }
        return "order/generate";
    }

    @GetMapping("/agreement")
    public String agreement() {
        return "order/agreement";
    }

}
