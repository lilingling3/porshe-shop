package com.boldseas.porscheshop.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yujie.li
 * @version 2018/5/11
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(@RequestParam(required = false) String source, ModelMap modelMap) {
        modelMap.addAttribute("source", source);
        return "login/index";
    }


}


