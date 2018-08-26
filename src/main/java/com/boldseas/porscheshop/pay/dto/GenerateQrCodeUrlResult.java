package com.boldseas.porscheshop.pay.dto;

import com.boldseas.porscheshop.dtos.RestResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Getter
@ApiModel
public class GenerateQrCodeUrlResult extends RestResult {
    @ApiModelProperty(name = "二维码url", notes = "成功时为二维码url,失败时为错误信息")
    private String qrCodeUrl;

    public GenerateQrCodeUrlResult(Boolean success, String qrCodeUrl) {
        super.setSuccess(success);
        this.qrCodeUrl = qrCodeUrl;
    }
}
