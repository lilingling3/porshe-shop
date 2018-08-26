package com.boldseas.porscheshop.user.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.dtos.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Chen Jingxuan
 * @version 2018/5/18
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "leads")
@Data
public class Leads extends BaseEntity<Long> {
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;
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

    /**
     * 电子邮件
     */
    @Column(name = "email")
    private String email;

    /**
     * 来源渠道
     */
    @Column(name = "source")
    private String source;

    /**
     * 线索中的经销商信息
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "leads_id")
    private List<LeadsDealer> leadsDealers;


    @Override
    public void setId(Long id) {
        this.id = null;
    }
}
