package com.boldseas.porscheshop.notify.beans;

import lombok.Data;

import javax.persistence.*;

/**
 * 电子邮箱账户
 * @author feng
 */
@Entity
@Table(name = "email_account")
@Data
public class EmailAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String dealerCode;

    @Column
    private String dealerName;

    @Column
    private String email;

    @Column
    private Boolean valid;
}
