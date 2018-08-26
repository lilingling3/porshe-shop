package com.boldseas.porscheshop.dtos;

import lombok.Data;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
@Data
public class LeadsDealerDTO {
    /**
     * id
     */
    private Long id;
    /**
     *经销商所在的省
     */
    private String province;

    /**
     *经销商所在的省的code
     */
    private String provinceCode;

    /**
     *经销商所在的市
     */
    private String city;

    /**
     *经销商所在的市的code
     */
    private String cityCode;
    /**
     * 经销商id
     */
    private Integer dealerId;
    /**
     * 经销商code
     */
    private String dealerCode;

    /**
     * 经销商名称
     */
    private String dealerName;
}
