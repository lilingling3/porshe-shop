package com.boldseas.porscheshop.pay.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huisheng.jin
 * @version 2018/5/25.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GateWayPayDetail extends BaseUnionPayDto {
    /**
     * 交易类型  取值：
     * 00：查询交易，01：消费，02：预授权，03：预授权完成，04：退货，05：圈存，11：代收，12：代付，
     * 13：账单支付，14：转账（保留），21：批量交易，22：批量查询，31：消费撤销，32：预授权撤销，33：预授权完成撤销，71：余额查询，
     * 72：实名认证-建立绑定关系，73：账单查询，74：解除绑定关系，75：查询绑定关系，77：发送短信验证码交易，78：开通查询交易，79：开通交易，94：IC卡脚本通知 95：查询更新加密公钥证书
     * 固定填写：01
     */
    private String txnType = "01";
    /**
     * 交易子类   依据实际交易类型填写。默认取值：00 固定填写：01：自助消费，通过地址的方式区 分前台消费和后台消费（含无跳转 支付）
     */
    private String txnSubType = "01";
    /**
     * 产品类型
     * 依据实际业务场景填写(目前仅使用后 4 位，签名 2 位 默认为 00) 默认取值：000000
     * 具体取值范围： 000201：B2C 网关支付 000301：认证支付 2.0 000302：评级支付 000401：代付 000501：代收 000601：账单支付 000801：跨行收单 000901：绑定支付 001001：订购 000202：B2B 000201
     */
    private String bizType = "000201";
    /**
     * 前台通知地址  前台返回商户结果时使用，前台类交易需上送 不支持换行符等不可见字符 前台返回商户结果时使用，例：https://xxx.xxx.com/xxx
     */
    private String frontUrl;
    /**
     * 后台通知地址  后台返回商户结果时使用，如上送，则发送商户后台交 易结果通知，不支持换行符等不可见字符，
     * 如需通过专线通知，需要在通知地址前面加上前缀：专线的首字母 加竖线 ZX| 后台返回商户结果时使用，例：https://xxx.xxx.com/xxx
     */
    private String backUrl;
    /**
     * 失败交易前台跳转地址  前台消费交易若商户上送此字段，则在支付失败时，页面跳转至商户该URL（不带交易信息，仅跳转），支持HTTP与HTTPS协议，互联网可访问
     * 前台消费交易若商户上送此字段，则在支付失败时，页面跳转至商户该URL（不带交易信息，仅跳转），支持HTTP与HTTPS协议，互联网可访问
     */
    private String frontFailUrl;


    //region 订单信息
    /**
     * 交易金额  单位为分，不能带小数点，样例：1元送100 单位为分，例：1元填写100
     */
    private String txnAmt;
    /**
     * 订单接收超时时间  单位为毫秒，交易发生时，该笔交易在银联全渠道系统中有效的最长时间。当距离交易发送时间超过该时间时，银联全渠道系统不再为该笔交易提供支付服务 1、前台类消费交易时上送 2、认证支付 2.0，后台交易时可选
     */
    private String orderTimeout;
    /**
     * 支付超时时间  YYYYMMDDHHmmss O 订单支付超时时间，超过此时间用户支付成功的交易， 不通知商户，系统自动退款，大约 5 个工作日金额返还 到用户账户 非网银的交易：超过此时间未完成支付时银联页面会提示超时，不允许后续支付。 跳转网银的交易银联无法控制，超过此时间用户支付成功的交易，不通知商户，系统自动退款，大约5个工作日金额返还到用户账户。
     */
    private String payTimeout = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000);
    /**
     * 发卡机构代码  当账号类型为 02-存折时需填写 在前台类交易时填写默认银行代码，支持直接跳转到网 银  1、当帐号类型为02-存折时需填写 2、在前台类交易时填写默认银行代码，支持直接跳转到网银。银行简码列表参考附录C.1、C.2，其中C.2银行列表仅支持借记卡，例：工行填写ICBC
     */
    private String issInsCode;
    /**
     * 请求方自定义域   商户自定义保留域，交易应答时会原样返回 商户自定义保留域，交易应答时会原样返回
     */
    private String reqReserved;


    //region 机构信息

    /**
     * 收单机构代码  已被批准加入银联互联网系统的收单机构代码，适用于收单机构接入测试场景。在商户接入时不返回此域，且上送报文中不应出现此域。
     */
    private String acqInsCode;
    /**
     * 商户类别  填写MCC码，接入类型为收单机构接入时需上送
     */
    private String merCatCode;
    /**
     * 商户名称  接入类型为收单机构接入时需上送,不支持换行符等不可见字符
     */
    private String merName;
    /**
     * 商户简称  接入类型为收单机构接入时需上送最长 8 位，不支持换行符等不可见字符
     */
    private String merAbbr;
    //endregion

    private GateWayPayDetail(String merchantId, String merchantOrderId, String price, String frontUrl, String backUrl) {
        super.setMerId(merchantId);
        super.setOrderId(merchantOrderId);

        this.txnAmt = price;
        this.frontUrl = frontUrl;
        this.backUrl = backUrl;

        createSign();
    }

    public Long getId() {
        return null;
    }

    public String getPayType() {
        return "";
    }

    public static class GateWayPayDetailBuilder {
        private String price;
        private String merchantId;
        private String merchantOrderId;
        private String frontUrl;
        private String backUrl;

        public GateWayPayDetailBuilder() {
        }

        public GateWayPayDetailBuilder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public GateWayPayDetailBuilder merchantOrderId(String merchantOrderId) {
            this.merchantOrderId = merchantOrderId;
            return this;
        }

        public GateWayPayDetailBuilder price(String price) {
            this.price = price;
            return this;
        }

        public GateWayPayDetailBuilder frontUrl(String frontUrl) {
            this.frontUrl = frontUrl;
            return this;
        }

        public GateWayPayDetailBuilder backUrl(String backUrl) {
            this.backUrl = backUrl;
            return this;
        }

        public GateWayPayDetail build() {
            return new GateWayPayDetail(merchantId, merchantOrderId, price, frontUrl, backUrl);
        }
    }
}
