package com.boldseas.porscheshop.client;

import com.boldseas.porscheshop.dtos.LeadsDispatchDTO;
import com.boldseas.porscheshop.dtos.OrderDispatchDTO;
import com.boldseas.porscheshop.dtos.OrderDispatchResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Chen Jingxuan
 * @version 2018/5/24
 */
@Component
@FeignClient(name = "dispatchClient", url = "${pmp.url}")
public interface DispatchClient {
    /**
     * 线索派发
     * @param apiKey apiKey
     * @param leadsDispatchDTO 线索信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/orders/workflow-create")
    void dispatchLeads(@RequestHeader("apiKey") String apiKey, @RequestBody LeadsDispatchDTO leadsDispatchDTO);

    /**
     * 订单派发
     * @param apiKey
     * @param orderDispatchDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/sps/orders/online-paid")
    OrderDispatchResult dispatchOrder(@RequestHeader("apiKey")String apiKey, @RequestBody OrderDispatchDTO orderDispatchDTO);
}
