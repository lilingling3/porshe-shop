package com.boldseas.porscheshop.client;

import com.boldseas.porscheshop.dtos.AccountDTO;
import com.boldseas.porscheshop.dtos.DealerDTO;
import com.boldseas.porscheshop.dtos.PositionDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/14.
 */
@Component
@FeignClient(name = "BaseDataClient",url ="${pmp.url}")
public interface BaseDataClient{

    /**
     * 获取存在经销商的省列表
     * @param apiKey
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/geographyProvinceForReferral")
    List<PositionDTO> getProvincesForDealer(@RequestHeader("apiKey")String apiKey);

    /**
     * 获取所有省列表
     * @param apiKey
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/geographyProvince")
    List<PositionDTO> getProvinces(@RequestHeader("apiKey")String apiKey);

    /**
     * 根据省Id获取下属存在经销商的市列表
     * @param apiKey
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/geographyCityForReferral/{parentId}")
    List<PositionDTO> getCitiesForDealerByProvince(@RequestHeader("apiKey")String apiKey, @PathVariable("parentId") Integer parentId);

    /**
     * 根据省Id获取下属所有市
     * @param apiKey
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/geographyCity/{parentId}")
    List<PositionDTO> getCitiesByProvince(@RequestHeader("apiKey")String apiKey, @PathVariable("parentId") Integer parentId);

    /**
     * 根据市Id获取下属经销商列表
     * @param apiKey
     * @param cityId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/dealer/getDealersByCityForJ1/{cityId}")
    List<DealerDTO> getDealersByCity(@RequestHeader("apiKey")String apiKey, @PathVariable("cityId") Integer cityId);

    /**
     * 根据市Id获取下属经销商列表 直辖市特殊处理
     * @param apiKey
     * @param cityId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/dealer/getDealersByDirectCityForJ1/{cityId}")
    List<DealerDTO> getDealersByCityForReferral(@RequestHeader("apiKey")String apiKey, @PathVariable("cityId") Integer cityId);

    /**
     * 根据dealerId获取下属账户信息
     * @param apiKey
     * @param dealerId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/api/sps/accounts/_get/{dealerId}/4")
    List<AccountDTO> getAccountByDealerId(@RequestHeader("apiKey")String apiKey, @PathVariable("dealerId") Integer dealerId);

}
