package com.boldseas.porscheshop.pay.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author huisheng.jin
 * @version 2018/5/16.
 */
@Data
@ApiModel
public class GenerateWapPayUrlInput {
    @ApiModelProperty(value = "订单号", required = true, example = "DAD00723B06D553Y")
    @NotBlank(message = "订单号不能为空")
    private String orderSn;
    @NotBlank(message = "支付类型不能为空")
    @ApiModelProperty(value = "支付类型", required = true, allowableValues = "h5AliPay,支付宝h5支付;wxPublic,微信公众号支付;h5QuickPass,云闪付", example = "h5AliPay")
    private String payType;
}
