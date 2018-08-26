package com.boldseas.porscheshop.common.utils;

import org.springframework.util.DigestUtils;

/**
 * MD5Util
 *
 * @author Feng
 */
public class Md5Util {

    /**
     * Encodes a string 2 MD5
     *
     * @param content String to encode
     * @return Encoded String
     */
    public static String generateMd5(String content) {
        return DigestUtils.md5DigestAsHex(content.getBytes());
    }

    /**
     * Encodes a string 2 MD5
     *
     * @param content MString to encode
     * @param privateKey  private key
     * @return
     */
    public static String generateMd5(String content, String privateKey) {

        String tobeEncrypt = content + privateKey;
        return DigestUtils.md5DigestAsHex(tobeEncrypt.getBytes());
    }
}
