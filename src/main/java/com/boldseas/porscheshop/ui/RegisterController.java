package com.boldseas.porscheshop.ui;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.interceptor.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册
 *
 * @author fei.ye
 * @version 2018/5/9.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserProvider userProvider;

    /**
     * 注册页
     *
     * @param map
     * @param intention = reservation 预定(默认) || intention = subscription 订阅
     * @return
     */
    @GetMapping("/{intention}")
    public String register(ModelMap map, @PathVariable String intention) {
        map.addAttribute("user", JSON.toJSONString(userProvider.getCurrentUser()));
        map.addAttribute("intention", intention);
        return "register/index";
    }

    /**
     * 注册成功页
     *
     * @param map
     * @return
     */
    @GetMapping("/success")
    public String registerSuccess(ModelMap map) {
        map.addAttribute("user", JSON.toJSONString(userProvider.getCurrentUser()));
        return "register/success";
    }

}


