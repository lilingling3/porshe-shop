package com.boldseas.porscheshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.mobile.device.Device;

/**
 * 用户登录传入的参数
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInputDTO {
    private final String DEVICE_PC = "PC";
    /**
     * 手机号
     */
    @NotBlank(message = "请输入手机号")
    @Length(min = 11, message = "请输入正确的手机号")
    private String phone;

    /**
     * 验证码
     */
    @NotBlank(message = "请输入验证码")
    @Length(min = 4, max = 4, message = "验证码错误")
    private String verificationCode;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 用户登录的来源
     */
    private String source;
    /**
     * 用户的设备：pc/mobile
     */
    private String device;

    public void setDevice(Device device){
        this.device = device.isMobile() ? device.getDevicePlatform().name() : DEVICE_PC;
    }
}
