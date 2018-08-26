package com.boldseas.porscheshop.pay.dto;

import lombok.Data;

/**
 * @author huisheng.jin
 * @version 2018/5/25.
 */
@Data
public class GateWayPayCallBackInput {

    //region 基本信息
    /**
     * 版本号  按规范填写。 固定填写
     */
    private String version;
    /**
     * 编码方式  填写报文使用的字符编码，UTF-8|GBK|GB2312|GB18030
     * 若不填写，默认取值：UTF-8  可取值UTF-8或GBK
     */
    private String encoding;
    /**
     * 证书ID  填写签名私钥证书的 Serial Number，该值可通过银联提 供的 SDK 获取
     */
    private String certId;
    /**
     * 签名方法  非对称签名：01（表示采用 RSA 签名） 固定填写：01（表示采用 RSA 签名）
     */
    private String signMethod;
    /**
     * 签名  填写对报文摘要的签名，可通过SDK生成签名 填写对报文摘要的签名，可通过SDK生成签名
     */
    private String signature;
    /**
     * 交易类型  取值：
     * 00：查询交易，01：消费，02：预授权，03：预授权完成，04：退货，05：圈存，11：代收，12：代付，
     * 13：账单支付，14：转账（保留），21：批量交易，22：批量查询，31：消费撤销，32：预授权撤销，33：预授权完成撤销，71：余额查询，
     * 72：实名认证-建立绑定关系，73：账单查询，74：解除绑定关系，75：查询绑定关系，77：发送短信验证码交易，78：开通查询交易，79：开通交易，94：IC卡脚本通知 95：查询更新加密公钥证书
     * 固定填写：01
     */
    private String txnType;
    /**
     * 交易子类   依据实际交易类型填写。默认取值：00 固定填写：01：自助消费，通过地址的方式区 分前台消费和后台消费（含无跳转 支付）
     */
    private String txnSubType;
    /**
     * 产品类型
     * 依据实际业务场景填写(目前仅使用后 4 位，签名 2 位 默认为 00) 默认取值：000000
     * 具体取值范围： 000201：B2C 网关支付 000301：认证支付 2.0 000302：评级支付 000401：代付 000501：代收 000601：账单支付 000801：跨行收单 000901：绑定支付 001001：订购 000202：B2B 000201
     */
    private String bizType;
    //endregion

    //region  商户信息

    /**
     * 接入类型  0：商户直连接入1：收单机构接入 2：平台商户接入 0：普通商户直连接入 1：收单机构接入
     */
    private String accessType;
    /**
     * 商户代码  已被批准加入银联互联网系统的商户代码
     */
    private String merId;
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

    //endregion

    //region 订单信息
    /**
     * 商户订单号  商户订单号，不应含“-”或“_” 商户端生成，例：12345asdf
     */
    private String orderId;
    /**
     * 交易币种  币种格式必须为3位代码，境内客户取值：156（人民币） 固定填写：156
     */
    private String currencyCode;
    /**
     * 交易金额  单位为分，不能带小数点，样例：1元送100 单位为分，例：1元填写100
     */
    private String txnAmt;
    /**
     * 订单发送时间  YYYYMMDDHHmmss M 必须使用当前北京时间（年年年年月月日日时时分分秒秒）24小时制，样例：20151123152540，北京时间 商户发送交易时间 商户发送交易时间，例：20151118100505
     */
    private String txnTime;
    /**
     * 订单接收超时时间  单位为毫秒，交易发生时，该笔交易在银联全渠道系统中有效的最长时间。当距离交易发送时间超过该时间时，银联全渠道系统不再为该笔交易提供支付服务 1、前台类消费交易时上送 2、认证支付 2.0，后台交易时可选
     */
    private String orderTimeout;
    /**
     * 支付超时时间  YYYYMMDDHHmmss O 订单支付超时时间，超过此时间用户支付成功的交易， 不通知商户，系统自动退款，大约 5 个工作日金额返还 到用户账户 非网银的交易：超过此时间未完成支付时银联页面会提示超时，不允许后续支付。 跳转网银的交易银联无法控制，超过此时间用户支付成功的交易，不通知商户，系统自动退款，大约5个工作日金额返还到用户账户。
     */
    private String payTimeout;
    /**
     * 发卡机构代码  当账号类型为 02-存折时需填写 在前台类交易时填写默认银行代码，支持直接跳转到网 银  1、当帐号类型为02-存折时需填写 2、在前台类交易时填写默认银行代码，支持直接跳转到网银。银行简码列表参考附录C.1、C.2，其中C.2银行列表仅支持借记卡，例：工行填写ICBC
     */
    private String issInsCode;
    /**
     * 请求方自定义域   商户自定义保留域，交易应答时会原样返回 商户自定义保留域，交易应答时会原样返回
     */
    private String reqReserved;

    //endregion

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

    /******************** 新增字段 ***********************/

    /**
     * 签名公钥 证书
     * 使用 RSA 签名方式时必选，此域填写银联签名公钥证书
     */
    private String signPubKeyCert;
    /**
     * 支付方式
     * 默认不返回此域，如需要返此域，需要提交申请，视商户配置返回，可在消费类交易中返回以下中的一种：0001：认证支付 0002：快捷支付 0004：储值卡支付
     * 0005：IC卡支付 0201：网银支付 1001：牡丹畅通卡支付 1002：中铁银通卡支付 0401：信用卡支付——暂定 0402：小额临时支付 0403：认证支付 2.0
     * 0404：互联网订单手机支付 9000：其他无卡支付(如手机客户端支付)
     */
    private String payType;
    /**
     * 账号
     * 交易账号。请求时使用加密公钥对交易账号加密，并做 Base64 编码后上送；应答时如需返回，则使用签名私钥 进行解密。
     * 前台交易可由银联页面采集，也可由商户上送并返显。 如需锁定返显卡号，应通过保留域（reserved）上送卡 号锁定标识。
     */
    private String accNo;
    /**
     * 支付卡类型
     * 消费交易，视商户配置返回。该域取值为： 00：未知 01：借记账户 02：贷记账户 03：准贷记账户 04：借贷合一账户 05：预付费账户 06：半开放预付费账户
     */
    private String payCardType;
    /**
     * 保留域
     */
    private String reserved;

    //region 通知信息
    /**
     * 查询流水号
     * 由银联返回，用于在后续类交易中唯一标识一笔交易 消费交易的流水号，供后续查询用
     */
    private String queryId;
    /**
     * 系统跟踪号  收单机构对账时使用，该域由银联系统产生
     */
    private String traceNo;
    /**
     * 交易传输时间  （月月日日时时分分秒秒）24小时制收单机构对账时使用，该域由银联系统产生
     */
    private String traceTime;
    /**
     * 清算日期
     * 为银联和入网机构间的交易结算日期。一般前一日23点至当天23点为一个清算日。也就是23点前的交易，当天23点之后开始结算，23点之后的交易，要第二天23点之后才会结算。测试环境为测试需要，23:30左右日切，所以23:30到23:30为一个清算日，当天23:30之后为下个清算日。
     */
    private String settleDate;
    /**
     * 清算币种  境内返回156
     */
    private String settleCurrencyCode;
    /**
     * 清算金额 取值同交易金额
     */
    private String settleAmt;
    /**
     * 应答码 成功：0000000
     */
    private String respCode;
    /**
     * 应答信息
     */
    private String respMsg;

    public Boolean isSuccess() {
        return "00".equals(respCode);
    }

    public Long getId() {
        return null;
    }
    //endregion
}
