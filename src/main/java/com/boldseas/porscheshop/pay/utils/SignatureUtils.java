package com.boldseas.porscheshop.pay.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * @author huisheng.jin
 * @version 2018/06/06
 */
@Slf4j
public class SignatureUtils {

    private static final String UTF_8 = "UTF-8";
    /**
     * 算法常量： SHA256
     */
    private static final String ALGORITHM_SHA256 = "SHA-256";
    /**
     * 算法常量：SHA256withRSA
     */
    private static final String BC_PROV_ALGORITHM_SHA256RSA = "SHA256withRSA";
    private static final String PROVIDER = "BC";


    /**
     * 签名方法
     *
     * @param keyValuePairString 待签名数据键值对字符串
     * @return
     */
    public static String sign(String keyValuePairString) {
        try {
            byte[] signDigest = sha256X16(keyValuePairString);
            byte[] byteSign = base64Encode(signBySoft256(
                    CertUtils.getPfxCertPrivateKey(), signDigest));
            return new String(byteSign);
        } catch (Exception e) {
            log.error("gate-wap-pay,signature generate fail,keyValuePairString:{}", keyValuePairString, e);
            throw new IllegalArgumentException(String.format("gate-wap-pay,signature generate fail,keyValuePairString:%s", keyValuePairString));
        }
    }

    /**
     * 判断字符串是否为NULL或空
     *
     * @param s 待判断的字符串数据
     * @return 判断结果 true-是 false-否
     */
    public static boolean isEmpty(String s) {
        return null == s || "".equals(s.trim());
    }

    /**
     * sha256计算后进行16进制转换
     *
     * @param data 待计算的数据
     * @return 计算结果
     */
    static byte[] sha256X16(String data) {
        byte[] bytes = sha256(data);
        StringBuilder sha256StrBuff = new StringBuilder();
        for (byte aByte : bytes) {
            if (Integer.toHexString(0xFF & aByte).length() == 1) {
                sha256StrBuff.append("0").append(
                        Integer.toHexString(0xFF & aByte));
            } else {
                sha256StrBuff.append(Integer.toHexString(0xFF & aByte));
            }
        }
        try {
            return sha256StrBuff.toString().getBytes(UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * sha256计算.
     *
     * @param data 待计算的数据
     * @return 计算结果
     */
    private static byte[] sha256(byte[] data) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(ALGORITHM_SHA256);
            md.reset();
            md.update(data);
            return md.digest();
        } catch (Exception e) {
            log.error("SHA256计算失败", e);
            return null;
        }
    }

    /**
     * sha256计算
     *
     * @param data 待计算的数据
     * @return
     */
    private static byte[] sha256(String data) {
        try {
            return sha256(data.getBytes(UTF_8));
        } catch (UnsupportedEncodingException e) {
            log.error("SHA256计算失败", e);
            return null;
        }
    }

    /**
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    private static byte[] signBySoft256(PrivateKey privateKey, byte[] data)
            throws Exception {
        byte[] result;
        Signature st = Signature.getInstance(BC_PROV_ALGORITHM_SHA256RSA, PROVIDER);
        st.initSign(privateKey);
        st.update(data);
        result = st.sign();
        return result;
    }

    /**
     * BASE64编码
     *
     * @param inputByte 待编码数据
     * @return 解码后的数据
     */
    private static byte[] base64Encode(byte[] inputByte) {
        return Base64.encodeBase64(inputByte);
    }
}
