package com.boldseas.porscheshop.dtos;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author Chen Jingxuan
 * @version 2018/5/25
 */

@Data
@ToString(callSuper = true)
public class OrderDispatchDTO extends BaseDispatchDTO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 下单时间
     */
    private LocalDateTime createDate;
    /**
     * 购车人
     */
    private String consignee;
    /**
     * 身份证
     */
    private String idCard;


    public void from(UserDTO userDTO) {
        this.setProvinceId(userDTO.getProvinceCode());
        this.setCityId(userDTO.getCityCode());
        super.setMobilePhone(userDTO.getPhone());
    }
}
