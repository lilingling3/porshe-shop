package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.utils.ExcelColumnName;
import lombok.Data;


/**
 * @author feng
 * 顺序：
 * 保时捷中心,注册人称谓,注册人姓,注册人名,注册人手机号,电子邮箱,省/市,市/区,详细地址,邮政编码,购车人姓名,购车人身份证,意向单编号,意向单状态,意向单日期,意向单支付时间,下载购车意向单
 */
@Data
public class OrderMailDTO {

    @ExcelColumnName("保时捷中心")
    private String dealerName;

    @ExcelColumnName("注册人称谓")
    private String gender;

    @ExcelColumnName("注册人姓")
    private String lastName;

    @ExcelColumnName("注册人名")
    private String firstName;

    @ExcelColumnName("注册人手机号")
    private String phone;

    @ExcelColumnName("电子邮箱")
    private String email;

    @ExcelColumnName("省/市")
    private String province;

    @ExcelColumnName("市/区")
    private String city;

    @ExcelColumnName("详细地址")
    private String address;

    @ExcelColumnName("邮政编码")
    private String postalCode;

    @ExcelColumnName("购车人姓名")
    private String consignee;

    @ExcelColumnName("购车人身份证")
    private String idCard;

    @ExcelColumnName("意向单编号")
    private String orderSn;

    @ExcelColumnName("意向单状态")
    private String orderStatus;

    @ExcelColumnName("意向单日期")
    private String createDate;

    @ExcelColumnName("意向单支付时间")
    private String paymentTime;

    @ExcelColumnName("下载购车意向单")
    private String downloadPdf;
}
