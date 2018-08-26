package com.boldseas.porscheshop.pay.utils;

import com.alibaba.fastjson.JSON;
import com.boldseas.porscheshop.common.utils.Md5Util;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Ums相关工具类
 *
 * @author huisheng.jin
 * @version 2018/5/14.
 */
@Slf4j
public class UmsPayUtils {

    private static final String SIGN_STRING = "sign";
    private static final String GOODS_STRING = "goods";
    private static final String AND_STRING = "&";
    private static final String EQUAL_STRING = "=";
    private static final String ORDER_DESC = "orderDesc";

    /**
     * 生成商户订单号
     *
     * @param msgSrcId 商户订单号
     * @return
     */
    public static String generateMerchantOrderId(String msgSrcId) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        return msgSrcId + String.format("%012d", hashCodeV);
    }

    /**
     * 生成签名
     *
     * @param privateKey 加密私钥
     */
    public static String generateSign(Object obj, String privateKey) {
        String tobeSignedStr = generateTobeSignedString(obj);
        return Md5Util.generateMd5(tobeSignedStr, privateKey);
    }

    public static String generateUrlParams(Object obj) {
        TreeMap maps = getTreeMaps(obj);
        maps.put(ORDER_DESC, urlEncode(String.valueOf(maps.get(ORDER_DESC))));
        return convertMapToUrlParams(maps).replace(" ", "%20");
    }

    private static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (Exception ex) {
            log.error("UmsPay wapPay --- urlEncode  error: value={}", value, ex);
            return "";
        }
    }

    private static String generateTobeSignedString(Object obj) {
        TreeMap maps = getTreeMaps(obj);
        Map<String, Object> treeMap = getValueNotEmptyMap(maps);
        return generateToBeSignedString(treeMap);
    }

    private static TreeMap getTreeMaps(Object obj) {
        return JSON.parseObject(JSON.toJSONString(obj), TreeMap.class);
    }


    private static Map<String, Object> getValueNotEmptyMap(Map<String, Object> requestData) {
        Map<String, Object> treeMap = Maps.newTreeMap();
        requestData.forEach((key, value) -> {
            if (StringUtils.isNotBlank(String.valueOf(value))) {
                treeMap.put(key, value);
            }
        });
        return treeMap;
    }

    private static String generateToBeSignedString(Map<String, Object> treeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        treeMap.forEach((key, value) -> {
            if (SIGN_STRING.equalsIgnoreCase(key)) {
                return;
            }
            appendParams(stringBuilder, key, value);
        });
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }


    private static String convertMapToUrlParams(Map<String, Object> treeMap) {
        StringBuilder stringBuilder = new StringBuilder();
        treeMap.forEach((key, value) -> appendParams(stringBuilder, key, value));
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    private static void appendParams(StringBuilder stringBuilder, String key, Object value) {
        stringBuilder.append(key).append(EQUAL_STRING);
        if (GOODS_STRING.equalsIgnoreCase(key.trim())) {
            stringBuilder.append(JSON.toJSONString(value));
        } else {
            stringBuilder.append(value);
        }
        stringBuilder.append(AND_STRING);
    }
}
