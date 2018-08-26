package com.boldseas.porscheshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginLogDTO {
    private Long userId;
    private String phone;
    private String status;
    private String source;
    private String ip;
    private String device;
}
