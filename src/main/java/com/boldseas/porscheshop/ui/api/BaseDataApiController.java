package com.boldseas.porscheshop.ui.api;

import com.boldseas.porscheshop.client.BaseDataClient;
import com.boldseas.porscheshop.dtos.DealerDTO;
import com.boldseas.porscheshop.dtos.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/14.
 */
@RestController
@RequestMapping("/v1/api")
public class BaseDataApiController{
    @Autowired
    private BaseDataClient baseDataClient;

    @Value("${pmp.apiKey}")
    private String apiKey;

    /**
     * 获取存在经销商的省列表
     * @return
     */
    @GetMapping("/dealer/province")
    public List<PositionDTO> getProvincesForDealer(){
        return baseDataClient.getProvincesForDealer(apiKey);
    }

    /**
     * 获取所有省列表
     * @return
     */
    @GetMapping("/province")
    public List<PositionDTO> getProvinces(){
        return baseDataClient.getProvinces(apiKey);
    }

    /**
     * 根据省Id获取下属存在经销商的市列表
     * @param provinceId
     * @return
     */
    @GetMapping("/dealer/city/{provinceId}")
    public List<PositionDTO> getCitiesForDealerByProvince(@PathVariable Integer provinceId){
        return baseDataClient.getCitiesForDealerByProvince(apiKey,provinceId);
    }

    /**
     * 根据省Id获取下属所有市
     * @param provinceId
     * @return
     */
    @GetMapping("/city/{provinceId}")
    public List<PositionDTO> getCitiesByProvince(@PathVariable Integer provinceId){
        return baseDataClient.getCitiesByProvince(apiKey,provinceId);
    }

    /**
     * 根据市Id获取下属经销商列表
     * @param cityId
     * @return
     */
    @GetMapping("/dealer/{cityId}")
    public List<DealerDTO> getDealersByCity(@PathVariable Integer cityId, int level){
        if(level==1){
            return baseDataClient.getDealersByCityForReferral(apiKey,cityId);
        }
        return baseDataClient.getDealersByCity(apiKey,cityId);
    }

}
