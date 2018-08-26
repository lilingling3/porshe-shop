package com.boldseas.porscheshop.dtos;

import lombok.*;

/**
 * @author: jiaChun.Yu
 * @version: 2018/5/15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResultDTO extends RestResult<UserDTO> {

    private Boolean isExistValidOrder;

    public RegisterResultDTO(Boolean success, String message) {
        super(success, message);
    }

    public RegisterResultDTO(Boolean success, Boolean isExistValidOrder, UserDTO user) {
        super(success, user);
        this.isExistValidOrder = isExistValidOrder;
    }
}
