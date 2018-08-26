package com.boldseas.porscheshop.dtos;

import lombok.Data;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/14.
 */
@Data
public class PositionDTO{

    private Integer id;

    private Integer parentId;

    private String nameCn;

    private String nameEn;

    /**
     * 是否是直辖市
     */
    private Boolean directGovernedCity;
    /**
     * 邮政编码
     */
    private String postalCode;

    private String code;

    private Integer level;

    private Integer sort;

    private Boolean valid;

    /**
     * 描述： 省/市/区
     */
    private String description;
}
