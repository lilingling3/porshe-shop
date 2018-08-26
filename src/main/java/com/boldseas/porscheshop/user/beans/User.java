package com.boldseas.porscheshop.user.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User  extends BaseEntity<Long>{

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
    @Column(name = "gender", length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

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
     *市
     */
    @Column(name = "city")
    private String city;

    /**
     *市code
     */
    @Column(name = "city_code")
    private String cityCode;
    /**
     *详细地址
     */
    @Column(name = "address",length = 300)
    private String address;

    /**
     * 邮政编码
     */
    @Column(name = "postal_code")
    private Integer postalCode;

    public User(String phone){
        this.phone = phone;
    }
}
