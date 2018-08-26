package com.boldseas.porscheshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chen Jingxuan
 * @version 2018/5/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserDTO {
    private Long userId;
    private String phone;
}
