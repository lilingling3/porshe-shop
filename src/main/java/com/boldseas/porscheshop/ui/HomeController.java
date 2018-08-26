
package com.boldseas.porscheshop.ui;

import com.boldseas.porscheshop.common.utils.RequestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 *
 * @author huiru.guo
 * @version 2018/5/16
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping({"", "/home", "/index"})
    public String home() {
        return "home/index";
    }

    @GetMapping("/question")
    public String question() {
        return "question/index";
    }

    @GetMapping("/home/wechat-interceptor")
    public String wechatIntercept(HttpServletRequest request) {
        if (RequestUtils.isWeixinEnv(request)) {
            return "home/notify";
        }
        return "redirect:/home";
    }
}
