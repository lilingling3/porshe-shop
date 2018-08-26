package com.boldseas.porscheshop.order.beans;

import com.boldseas.porscheshop.common.jpa.entity.BaseEntity;
import com.boldseas.porscheshop.common.utils.Md5Util;
import com.boldseas.porscheshop.dtos.OrderStatusEnum;
import com.boldseas.porscheshop.dtos.TerminalEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * 订单表
 *
 * @author feng
 */
@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity<Long> {

    public static final String ALIPAY_PREFIX = "Alipay";
    public static final String ALIPAY_STRING = "支付宝";
    public static final String ACP_PREFIX = "ACP";
    public static final String ACP_STRING = "云闪付";
    public static final String WX_PREFIX = "WX";
    public static final String WX_STRING = "微信";
    public static final String UNION_PREFIX = "Union";
    public static final String UNION_STRING = "银联";
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 订单号
     */
    @Column
    private String orderSn;
    /**
     * 用户ID
     */
    @Column
    private Long userId;
    /**
     * 手机号码
     */
    @Column
    private String phone;
    /**
     * 商品ID
     */
    @Column
    private Long goodsId;
    /**
     * 购车人
     */
    @Column
    private String consignee;
    /**
     * 身份证
     */
    @Column
    private String idCard;
    /**
     * 经销商代码
     */
    @Column
    private String dealerCode;
    /**
     * 经销商ID
     */
    @Column
    private Integer dealerId;
    /**
     * 经销商名字
     */
    @Column
    private String dealerName;
    /**
     * 公司中文名
     */
    @Column
    private String companyCn;
    /**
     * 经销商省
     */
    @Column
    private String province;
    /**
     * 经销商省code
     */
    @Column
    private String provinceCode;
    /**
     * 经销商市
     */
    @Column
    private String city;
    /**
     * 经销商市code
     */
    @Column
    private String cityCode;
    /**
     * 购车人地址
     */
    @Column
    private String address;
    /**
     * 商品价格
     */
    @Column
    private Double price;

    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatusEnum status;
    /**
     * 终端
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TerminalEnum terminal;
    /**
     * 支付方式
     */
    @Column(length = 20)
    private String payType;

    /**
     * 来源渠道
     */
    @Column(name = "source")
    private String source;

    /**
     * 千分位价格，单位为元。
     * 例：20,000
     *
     * @return
     */
    @Transient
    public String getPriceNumber() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        return numberFormat.format((int) (this.price / 100));
    }

    /**
     * 生成订单号
     *
     * @return
     */
    public void generateOrderSn() {
        String md5str = Md5Util.generateMd5(UUID.randomUUID().toString());
        String sn = md5str.toUpperCase().substring(10, 22);
        this.orderSn = getRandom() + getRandom() + sn + getRandom() + getRandom();
    }

    public void updateOrderStatus(String payType, TerminalEnum terminalEnum, OrderStatusEnum statusEnum) {
        this.status = statusEnum;
        if (Objects.nonNull(terminalEnum)) {
            this.terminal = terminalEnum;
        }
        if (!StringUtils.isEmpty(payType)) {
            this.payType = payType;
        }
    }

    public String getPayTypeString() {
        if (StringUtils.isEmpty(payType)) {
            return "";
        }
        if (payType.startsWith(ALIPAY_PREFIX)) {
            return ALIPAY_STRING;
        }
        if (payType.startsWith(ACP_PREFIX)) {
            return ACP_STRING;
        }
        if (payType.startsWith(WX_PREFIX)) {
            return WX_STRING;
        }
        if (payType.startsWith(UNION_PREFIX)) {
            return UNION_STRING;
        }
        return "";
    }

    @Transient
    public boolean isCanBeDeleted() {
        return Objects.equals(this.status, OrderStatusEnum.waitingPay) && this.isValid();
    }

    private String getRandom() {
        int index = new Random().nextInt(26);
        Character character = ALPHABET.charAt(index);
        return character.toString();
    }
}
