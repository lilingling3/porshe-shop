package com.boldseas.porscheshop.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/11.
 */
@Data
public class RegisterInputDTO{
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Gender gender;

    private String email;

    private String province;

    private String provinceCode;

    private String city;

    private String cityCode;

    private String address;

    private Integer postalCode;

    /**
     * 用户意向   reservation 预定  subscription 订阅    improve 完善
     */
    private String intention;
}
