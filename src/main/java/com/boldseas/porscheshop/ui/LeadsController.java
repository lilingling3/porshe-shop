
package com.boldseas.porscheshop.ui;

import com.boldseas.porscheshop.dtos.LeadsDTO;
import com.boldseas.porscheshop.user.LeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 线索
 *
 * @author huiru.guo
 * @version 2018/5/16
 */
@Controller
@RequestMapping("/leads")
public class LeadsController {
    private final LeadsService leadsService;

    @Autowired
    public LeadsController(LeadsService leadsService) {
        this.leadsService = leadsService;
    }

    /**
     * 线索页
     *
     * @return
     */
    @GetMapping
    public String leads() {
        return "leads/index";
    }

    @GetMapping("/success/{leadsId}")
    public String leadsSuccess(@PathVariable Long leadsId, ModelMap modelMap) {
        LeadsDTO leadsDTO = leadsService.get(leadsId);
        modelMap.addAttribute("leads", leadsDTO);
        return "leads/success";
    }
}
