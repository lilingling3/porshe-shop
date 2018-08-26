package com.boldseas.porscheshop.common.utils.pdf;

import lombok.Data;

/**
 * @author fei.ye
 * @version 2018/5/30.
 */
@Data
class ReplaceRegion {
    private String aliasName;
    private Float x;
    private Float y;
    private Float w;
    private Float h;

    public ReplaceRegion(String aliasName) {
        this.aliasName = aliasName;
    }

}
