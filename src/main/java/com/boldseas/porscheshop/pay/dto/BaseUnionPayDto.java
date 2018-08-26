package com.boldseas.porscheshop.pay.dto;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.pay.utils.CertUtils;
import com.boldseas.porscheshop.pay.utils.SignatureUtils;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * note:
 * 有很多参数都是固定值，没必要写在配置文件中
 *
 * @author huisheng.jin
 * @version 2018/6/11.
 */
@Data
public class BaseUnionPayDto {
    /**
     * 版本号  按规范填写。 固定填写
     */
    private String version = "5.1.0";
    /**
     * 编码方式  填写报文使用的字符编码，UTF-8|GBK|GB2312|GB18030
     * 若不填写，默认取值：UTF-8  可取值UTF-8或GBK
     */
    private String encoding = "UTF-8";
    /**
     * 签名方法  非对称签名：01（表示采用 RSA 签名） 固定填写：01（表示采用 RSA 签名）
     */
    private String signMethod = "01";
    /**
     * 渠道类型  05：语音07：互联网08：移动 16：数字机顶盒 固定填写：07
     */
    private String channelType = "07";
    /**
     * 接入类型  0：商户直连接入1：收单机构接入 2：平台商户接入 0：普通商户直连接入 1：收单机构接入
     */
    private String accessType = "0";
    /**
     * 交易币种  币种格式必须为3位代码，境内客户取值：156（人民币） 固定填写：156
     */
    private String currencyCode = "156";
    /**
     * 订单发送时间  YYYYMMDDHHmmss M 必须使用当前北京时间（年年年年月月日日时时分分秒秒）24小时制，样例：20151123152540，北京时间 商户发送交易时间 商户发送交易时间，例：20151118100505
     */
    private String txnTime = getCurrentTime();

    /**
     * 签名  填写对报文摘要的签名，可通过SDK生成签名 填写对报文摘要的签名，可通过SDK生成签名
     */
    private String signature;
    /**
     * 证书ID  填写签名私钥证书的 Serial Number，该值可通过银联提 供的 SDK 获取
     */
    private String certId = CertUtils.getPfxCertSerialNumber();
    /**
     * 商户代码  已被批准加入银联互联网系统的商户代码
     */
    private String merId;
    /**
     * 商户订单号  商户订单号，不应含“-”或“_” 商户端生成，例：12345asdf
     */
    private String orderId;

    private String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    protected void createSign() {
        this.signature = SignatureUtils.sign(this.toOrderedKeyValuePairStringWithoutSignatureField());
    }
    public Map<String, String> toMap() {
        return toFilteredBlankMap();
    }

    private Map<String, String> toFilteredBlankMap() {
        HashMap mapFields = JSON.parseObject(JSON.toJSONString(this), HashMap.class);
        return filterBlank(mapFields);
    }

    /**
     * 转换为有序的键值对字符串
     *
     * @return
     */
    private String toOrderedKeyValuePairStringWithoutSignatureField() {
        Map<String, String> map = toMap();
        return toOrderedKeyValuePairStringWithoutSignatureField(filterBlank(map));
    }

    /**
     * 转换为有序键值对字符串（排除签名字段）
     *
     * @param data
     * @return
     */
    private String toOrderedKeyValuePairStringWithoutSignatureField(Map<String, String> data) {
        TreeMap<String, String> tree = new TreeMap<>();
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            if ("signature".equals(en.getKey().trim())) {
                continue;
            }
            tree.put(en.getKey(), en.getValue());
        }
        it = tree.entrySet().iterator();
        StringBuffer sf = new StringBuffer();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            sf.append(en.getKey() + "=" + en.getValue()
                    + "&");
        }
        return sf.substring(0, sf.length() - 1);
    }

    private Map<String, String> filterBlank(Map<String, String> contentData) {
        Map<String, String> submitFromData = new HashMap<>(20);
        Set<String> keySet = contentData.keySet();

        for (String key : keySet) {
            String value = contentData.get(key);
            if (value != null && !"".equals(value.trim())) {
                submitFromData.put(key, value.trim());
            }
        }
        return submitFromData;
    }

}
