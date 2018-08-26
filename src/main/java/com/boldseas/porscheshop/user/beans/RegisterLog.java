package com.boldseas.porscheshop.user.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/11.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "register_log")
@Data
public class RegisterLog extends BaseEntity<Long>{

    /**
     *名
     */
    @Column(name = "first_name",length = 64)
    private String firstName;

    /**
     *姓
     */
    @Column(name = "last_name",length = 64)
    private String lastName;

    /**
     *手机号
     */
    @Column(name = "phone",length=30)
    private String phone;

    /**
     *称呼
     */
    @Column(name = "gender")
    private String gender;

    /**
     *邮件地址
     */
    @Column(name = "email",length = 64)
    private String email;

    /**
     *省
     */
    @Column(name = "province")
    private String province;

    /**
     *省code
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 市
     */
    @Column(name = "city")
    private String city;

    /**
     * 市code
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 详细地址
     */
    @Column(name = "address",length = 300)
    private String address;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private Integer postalCode;

    /**
     * 意向
     */
    @Column(name = "intention")
    private String intention;

    /**
     * 来源渠道
     */
    @Column(name = "source")
    private String source;

}
