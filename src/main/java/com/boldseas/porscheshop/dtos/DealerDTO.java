package com.boldseas.porscheshop.dtos;

import lombok.Data;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/15.
 */
@Data
public class DealerDTO{
    private Integer id;

    private Integer masterDealerId;

    private Boolean slave;
    /**
     *区域
     */
    private String region;
    /**
     *子区域
     */
    private String subRegion;

    private Integer provinceId;

    private Integer cityId;

    private Integer levelId;

    private String dealerCode;
    /**
     *名称
     */
    private String nameCn;
    private String nameEn;
    /**
     *公司
     */
    private String companyCn;
    private String companyEn;
    /**
     *地址
     */
    private String addressCn;
    private String addressEn;
    /**
     *品牌名
     */
    private String dealerBrandName;
    /**
     *经销商分组
     */
    private String dealerGroupName;
    /**
     *服务电话
     */
    private String serviceTel;
    /**
     *邮政
     */
    private String postalCode;
    /**
     *传真
     */
    private String fax;

    private Boolean distributable;
    /**
     *是否有效
     */
    private Boolean valid;
    /**
     *简拼
     */
    private String shortForm;
    /**
     *描述
     */
    private String description;
    /**
     *排序
     */
    private Integer sort;
    /**
     *经度
     */
    private String longitude;
    /**
     *纬度
     */
    private String latitude;
    /**
     * 简拼
     */
    private String subShortForm;
}
