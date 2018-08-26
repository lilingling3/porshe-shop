package com.boldseas.porscheshop.pay.dto;


import com.boldseas.porscheshop.dtos.RestResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/5/16.
 */
@Data
@ApiModel
public class GenerateWapPayUrlOutput extends RestResult {
    @ApiModelProperty(name = "支付url")
    private String payUrl;

    public GenerateWapPayUrlOutput(Boolean success, String payUrl) {
        super.setSuccess(success);
        this.payUrl = payUrl;
    }
}
