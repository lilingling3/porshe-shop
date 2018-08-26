package com.boldseas.porscheshop.pay.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yefei
 */
@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "unionPay")
public class UnionPayConfig {
    /**
     * 前台请求URL.
     */
    private String frontTransUrl;
    /**
     * 后台请求URL.
     */
    private String backTransUrl;
    /**
     * 单笔查询
     */
    private String singleQueryUrl;
    /**
     * 批量查询
     */
    private String batchQueryUrl;
    /**
     * 批量交易
     */
    private String batchTransUrl;
    /**
     * 文件传输
     */
    private String fileTransUrl;
    /**
     * 签名证书路径.
     */
    private String signCertPath;
    /**
     * 签名证书密码.
     */
    private String signCertPwd;
    /**
     * 签名证书类型.
     */
    private String signCertType;
    /**
     * 加密公钥证书路径.
     */
    private String encryptCertPath;
    /**
     * 按照商户代码读取指定签名证书目录.
     */
    private String signCertDir;
    /**
     * 磁道加密证书路径.
     */
    private String encryptTrackCertPath;
    /**
     * 磁道加密公钥模数.
     */
    private String encryptTrackKeyModulus;
    /**
     * 磁道加密公钥指数.
     */
    private String encryptTrackKeyExponent;
    /**
     * 有卡交易.
     */
    private String cardRequestUrl;
    /**
     * app交易
     */
    private String appRequestUrl;
    /**
     * 证书使用模式(单证书/多证书)
     */
    private String singleMode;
    /**
     * 安全密钥(SHA256和SM3计算时使用)
     */
    private String secureKey;
    /**
     * 中级证书路径
     */
    private String middleCertPath;
    /**
     * 根证书路径
     */
    private String rootCertPath;
    /**
     * 是否验证验签证书CN，除了false都验
     */
    private boolean ifValidateCNName = true;
    /**
     * 是否验证https证书，默认都不验
     */
    private boolean ifValidateRemoteCert = false;
    /**
     * signMethod，没配按01吧
     */
    private String signMethod;
    /**
     * version，没配按5.0.0
     */
    private String version;
    /**
     * frontUrl
     */
    private String frontUrl;
    /**
     * backUrl
     */
    private String backUrl;
    /**
     * frontFailUrl
     */
    private String frontFailUrl;

    /**
     * 配置文件中的前台URL常量.
     */
    public static final String SDK_FRONT_URL = "acpsdk.frontTransUrl";
    /**
     * 配置文件中的后台URL常量.
     */
    public static final String SDK_BACK_URL = "acpsdk.backTransUrl";
    /**
     * 配置文件中的单笔交易查询URL常量.
     */
    public static final String SDK_SIGNQ_URL = "acpsdk.singleQueryUrl";
    /**
     * 配置文件中的批量交易查询URL常量.
     */
    public static final String SDK_BATQ_URL = "acpsdk.batchQueryUrl";
    /**
     * 配置文件中的批量交易URL常量.
     */
    public static final String SDK_BATTRANS_URL = "acpsdk.batchTransUrl";
    /**
     * 配置文件中的文件类交易URL常量.
     */
    public static final String SDK_FILETRANS_URL = "acpsdk.fileTransUrl";
    /**
     * 配置文件中的有卡交易URL常量.
     */
    public static final String SDK_CARD_URL = "acpsdk.cardTransUrl";
    /**
     * 配置文件中的app交易URL常量.
     */
    public static final String SDK_APP_URL = "acpsdk.appTransUrl";

    /**
     * 以下缴费产品使用，其余产品用不到，无视即可
     */
    // 前台请求地址
    public static final String JF_SDK_FRONT_TRANS_URL = "acpsdk.jfFrontTransUrl";
    // 后台请求地址
    public static final String JF_SDK_BACK_TRANS_URL = "acpsdk.jfBackTransUrl";
    // 单笔查询请求地址
    public static final String JF_SDK_SINGLE_QUERY_URL = "acpsdk.jfSingleQueryUrl";
    // 有卡交易地址
    public static final String JF_SDK_CARD_TRANS_URL = "acpsdk.jfCardTransUrl";
    // App交易地址
    public static final String JF_SDK_APP_TRANS_URL = "acpsdk.jfAppTransUrl";
    // 人到人
    public static final String QRC_BACK_TRANS_URL = "acpsdk.qrcBackTransUrl";
    // 人到人
    public static final String QRC_B2C_ISS_BACK_TRANS_URL = "acpsdk.qrcB2cIssBackTransUrl";
    // 人到人
    public static final String QRC_B2C_MER_BACK_TRANS_URL = "acpsdk.qrcB2cMerBackTransUrl";

    /**
     * 以下综合认证产品使用，其余产品用不到，无视即可
     */
    // 前台请求地址
    public static final String ZHRZ_SDK_FRONT_TRANS_URL = "acpsdk.zhrzFrontTransUrl";
    // 后台请求地址
    public static final String ZHRZ_SDK_BACK_TRANS_URL = "acpsdk.zhrzBackTransUrl";
    // 单笔查询请求地址
    public static final String ZHRZ_SDK_SINGLE_QUERY_URL = "acpsdk.zhrzSingleQueryUrl";
    // 有卡交易地址
    public static final String ZHRZ_SDK_CARD_TRANS_URL = "acpsdk.zhrzCardTransUrl";
    // App交易地址
    public static final String ZHRZ_SDK_APP_TRANS_URL = "acpsdk.zhrzAppTransUrl";
    // 图片识别交易地址
    public static final String ZHRZ_SDK_FACE_TRANS_URL = "acpsdk.zhrzFaceTransUrl";

    /**
     * 配置文件中签名证书路径常量.
     */
    public static final String SDK_SIGNCERT_PATH = "acpsdk.signCert.path";
    /**
     * 配置文件中签名证书密码常量.
     */
    public static final String SDK_SIGNCERT_PWD = "acpsdk.signCert.pwd";
    /**
     * 配置文件中签名证书类型常量.
     */
    public static final String SDK_SIGNCERT_TYPE = "acpsdk.signCert.type";
    /**
     * 配置文件中密码加密证书路径常量.
     */
    public static final String SDK_ENCRYPTCERT_PATH = "acpsdk.encryptCert.path";
    /**
     * 配置文件中磁道加密证书路径常量.
     */
    public static final String SDK_ENCRYPTTRACKCERT_PATH = "acpsdk.encryptTrackCert.path";
    /**
     * 配置文件中磁道加密公钥模数常量.
     */
    public static final String SDK_ENCRYPTTRACKKEY_MODULUS = "acpsdk.encryptTrackKey.modulus";
    /**
     * 配置文件中磁道加密公钥指数常量.
     */
    public static final String SDK_ENCRYPTTRACKKEY_EXPONENT = "acpsdk.encryptTrackKey.exponent";
    /**
     * 配置文件中验证签名证书目录常量.
     */
    public static final String SDK_VALIDATECERT_DIR = "acpsdk.validateCert.dir";

    /**
     * 配置文件中是否加密cvn2常量.
     */
    public static final String SDK_CVN_ENC = "acpsdk.cvn2.enc";
    /**
     * 配置文件中是否加密cvn2有效期常量.
     */
    public static final String SDK_DATE_ENC = "acpsdk.date.enc";
    /**
     * 配置文件中是否加密卡号常量.
     */
    public static final String SDK_PAN_ENC = "acpsdk.pan.enc";
    /**
     * 配置文件中证书使用模式
     */
    public static final String SDK_SINGLEMODE = "acpsdk.singleMode";
    /**
     * 配置文件中安全密钥
     */
    public static final String SDK_SECURITYKEY = "acpsdk.secureKey";
    /**
     * 配置文件中根证书路径常量
     */
    public static final String SDK_ROOTCERT_PATH = "acpsdk.rootCert.path";
    /**
     * 配置文件中根证书路径常量
     */
    public static final String SDK_MIDDLECERT_PATH = "acpsdk.middleCert.path";
    /**
     * 配置是否需要验证验签证书CN，除了false之外的值都当true处理
     */
    public static final String SDK_IF_VALIDATE_CN_NAME = "acpsdk.ifValidateCNName";
    /**
     * 配置是否需要验证https证书，除了true之外的值都当false处理
     */
    public static final String SDK_IF_VALIDATE_REMOTE_CERT = "acpsdk.ifValidateRemoteCert";
    /**
     * signmethod
     */
    public static final String SDK_SIGN_METHOD = "acpsdk.signMethod";
    /**
     * version
     */
    public static final String SDK_VERSION = "acpsdk.version";
    /**
     * 后台通知地址
     */
    public static final String SDK_BACKURL = "acpsdk.backUrl";
    /**
     * 前台通知地址
     */
    public static final String SDK_FRONTURL = "acpsdk.frontUrl";
    /**
     * 前台失败通知地址
     */
    public static final String SDK_FRONT_FAIL_URL = "acpsdk.frontFailUrl";
}
