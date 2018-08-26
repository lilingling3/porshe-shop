package com.boldseas.porscheshop.dtos;

import lombok.Data;
import lombok.ToString;

/**
 * @author Chen Jingxuan
 * @version 2018/6/5
 */
@Data
@ToString(callSuper = true)
public class LeadsDispatchDTO extends BaseDispatchDTO {
    /**
     *详细地址
     */
    private String street;

    /**
     * pmp中与商城线索关联的标识
     */
    private String leadsSource;
}
