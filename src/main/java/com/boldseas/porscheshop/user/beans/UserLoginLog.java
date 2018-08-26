package com.boldseas.porscheshop.user.beans;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户登录记录表
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Entity
@Table(name = "user_login_log")
@Data
public class UserLoginLog {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private LocalDateTime loginTime = LocalDateTime.now();

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户手机号
     */
    @Column(name = "phone", length = 20)
    private String phone;

    /**
     * 登录状态
     */
    @Column(name = "status", length = 100)
    private String status;

    /**
     * 登录设备
     */
    @Column(name = "device", length = 100)
    private String device;

    /**
     * 登录来源
     */
    @Column(name = "source")
    private String source;

    /**
     * 登录ip
     */
    @Column(name = "ip", length = 100)
    private String ip;


    public void setId(Long id) {
        this.id = null;
    }
}
