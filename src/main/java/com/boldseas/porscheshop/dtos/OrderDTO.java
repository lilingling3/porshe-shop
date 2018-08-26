package com.boldseas.porscheshop.dtos;

import com.boldseas.porscheshop.common.config.Goods;
import com.boldseas.porscheshop.common.constant.OrderValidMessage;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * @author feng
 */
@Data
public class OrderDTO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 购车人
     */
    @NotBlank(message = OrderValidMessage.CONSIGNEE_EMPTY)
    private String consignee;
    /**
     * 身份证
     */
    @NotBlank(message = OrderValidMessage.IDCARD_EMPTY)
    private String idCard;
    /**
     * 经销商代码
     */
    private String dealerCode;
    /**
     * 经销商ID
     */
    private Integer dealerId;
    /**
     * 经销商名字
     */
    @NotBlank(message = OrderValidMessage.DEALER_EMPTY)
    private String dealerName;
    /**
     * 经销商省
     */
    @NotBlank(message = OrderValidMessage.PROVINCE_EMPTY)
    private String province;
    /**
     * 经销商省
     */
    private String provinceCode;
    /**
     * 经销商市
     */
    @NotBlank(message = OrderValidMessage.CITY_EMPTY)
    private String city;
    /**
     * 经销商市
     */
    private String cityCode;
    /**
     * 购车人地址
     */
    private String address;
    /**
     * 商品价格
     */
    private Double price;
    /**
     * 订单状态
     */
    private OrderStatusEnum status;
    /**
     * 公司中文名
     */
    private String companyCn;
    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;
    /**
     * 订单创建时间
     */
    private LocalDateTime createDate;

    /**
     * 千分位价格
     */
    private String priceNumber;

    /**
     * 设置订单商品
     *
     * @param goods
     */
    public void setGoodsInfo(Goods goods) {
        this.goodsId = goods.getId();
        this.price = goods.getPrice();
    }

    /**
     * 设置用户信息
     *
     * @param user
     */
    public void setUserInfo(UserDTO user) {
        this.userId = user.getId();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }

    public Long getId() {
        return null;
    }

    /**
     * 获取订单创建时间
     * @return 创建时间（格式：yyyy-MM-dd）
     */
    public LocalDate getCreateDate() {
        return Objects.nonNull(this.createDate)?this.createDate.toLocalDate():null;
    }
}
