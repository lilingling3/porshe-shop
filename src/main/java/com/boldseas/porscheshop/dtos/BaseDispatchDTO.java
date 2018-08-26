package com.boldseas.porscheshop.dtos;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author Chen Jingxuan
 * @version 2018/5/22
 */
@Data
public class BaseDispatchDTO {
    /**
     *姓
     */
    private String lastName;
    /**
     *名
     */
    private String firstName;

    /**
     *手机号
     */
    private String mobilePhone;

    /**
     *称呼
     */
    private Gender gender;

    /**
     *电子邮箱
     */
    private String email;

    /**
     *省code
     */
    private Integer provinceId;

    /**
     *市
     */
    private Integer cityId;

    /**
     * 经销商id
     */
    private Integer dealerId;

    /**
     * 邮编
     */
    private String postalCode;

    public void setProvinceId(String provinceCode) {
        if (!StringUtils.isEmpty(provinceCode)) {
            this.provinceId = Integer.parseInt(provinceCode);
        }
    }

    public void setCityId(String cityCode) {
        if (!StringUtils.isEmpty(cityCode)) {
            this.cityId = Integer.parseInt(cityCode);
        }
    }

    public Long getId() {
        return null;
    }
}
