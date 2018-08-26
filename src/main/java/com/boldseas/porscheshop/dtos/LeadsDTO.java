package com.boldseas.porscheshop.dtos;

import lombok.Data;

import java.util.List;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
@Data
public class LeadsDTO {
    /**
     * leads id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     *名
     */
    private String firstName;

    /**
     *姓
     */
    private String lastName;

    /**
     *手机号
     */
    private String phone;

    /**
     *称呼
     */
    private Gender gender;

    /**
     *省
     */
    private String province;

    /**
     *省code
     */
    private String provinceCode;

    /**
     *市
     */
    private String city;

    /**
     *市code
     */
    private String cityCode;

    /**
     *详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private Integer postalCode;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 线索总的经销商
     */
    private List<LeadsDealerDTO> leadsDealers;
}
