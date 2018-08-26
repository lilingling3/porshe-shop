package com.boldseas.porscheshop.ui.api;

import com.boldseas.porscheshop.dtos.*;
import com.boldseas.porscheshop.interceptor.UserProvider;
import com.boldseas.porscheshop.user.LeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chen Jingxuan
 * @version 2018/5/18
 */
@RestController
@RequestMapping("/v1/api")
public class LeadsApiController {
    private final LeadsService leadsService;

    @Autowired
    public LeadsApiController(LeadsService leadsService) {
        this.leadsService = leadsService;
    }

    @PostMapping(path = "/leads/save")
    public RestResult<LeadsDTO> save(@RequestBody List<LeadsDealerSaveInputDTO> leadsDealerSaveInputDTOList) {
        LeadsDTO leadsDTO = leadsService.save(leadsDealerSaveInputDTOList);
        return RestResult.success(leadsDTO);
    }
}
