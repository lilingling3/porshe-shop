package com.boldseas.porscheshop.user.beans;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Chen Jingxuan
 * @version 2018/5/21
 */
@Entity
@Table(name = "leads_dealer")
@Data
public class LeadsDealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     *经销商所在的省
     */
    @Column(name = "province")
    private String province;

    /**
     *经销商所在的省的code
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     *经销商所在的市
     */
    @Column(name = "city")
    private String city;

    /**
     *经销商所在的市的code
     */
    @Column(name = "city_code")
    private String cityCode;
    /**
     * 经销商id
     */
    @Column(name = "dealer_id")
    private Integer dealerId;
    /**
     * 经销商代码
     */
    @Column(name = "dealer_code")
    private String dealerCode;

    /**
     * 经销商名字
     */
    @Column(name = "dealer_name")
    private String dealerName;

    public void setId(Long id){
        this.id = null;
    }
}
