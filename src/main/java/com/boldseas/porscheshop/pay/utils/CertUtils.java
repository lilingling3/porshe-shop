package com.boldseas.porscheshop.pay.utils;

import com.boldseas.porscheshop.pay.config.UnionPayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;

/**
 * @author huisheng.jin
 * @version 2018/06/05
 */
@Slf4j
@Component
public class CertUtils {

    private static final String PROVIDER = "BC";
    private static final String X_509_STANDARD = "X.509";
    private UnionPayConfig sdkConfigInit;

    private static UnionPayConfig unionPayConfig;

    @Autowired
    public CertUtils(UnionPayConfig sdkConfigInit) {
        this.sdkConfigInit = sdkConfigInit;
    }

    @PostConstruct
    public void initStatic() {
        unionPayConfig = this.sdkConfigInit;
        init();
    }

    /**
     * 证书容器，存储对商户请求报文签名私钥证书.
     */
    private static KeyStore keyStore = null;
    /**
     * 验证银联返回报文签名的公钥证书存储Map.
     */
    private static Map<String, X509Certificate> certMap = new HashMap<>();

    /**
     * 初始化所有证书.
     */
    private static void init() {
        try {
            addProvider();
            initSignCert();
            initMiddleCert();
            initRootCert();
            initEncryptCert();
        } catch (Exception e) {
            log.error("init失败。（如果是用对称密钥签名的可无视此异常。）", e);
        }
    }

    /**
     * 添加签名，验签，加密算法提供者
     */
    private static void addProvider() {
        if (Security.getProvider("BC") == null) {
            log.info("add BC provider");
        } else {
            //解决eclipse调试时tomcat自动重新加载时，BC存在不明原因异常的问题。
            Security.removeProvider("BC");
            log.info("re-add BC provider");
        }
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private static void initEncryptCert() {
        log.info("加载敏感信息加密证书==>" + unionPayConfig.getEncryptCertPath());
        X509Certificate certificate = initCert(unionPayConfig.getEncryptCertPath());
        certMap.put(certificate.getSerialNumber().toString(), certificate);
        log.info("Load EncryptCert Successful");
    }

    private static void initSignCert() {
        keyStore = getKeyStore(unionPayConfig.getSignCertPath(),
                unionPayConfig.getSignCertPwd(), unionPayConfig.getSignCertType());
        log.info("InitSignCert Successful. CertId=["
                + getPfxCertSerialNumber() + "]");
    }

    private static void initMiddleCert() {
        log.info("加载中级证书==>" + unionPayConfig.getMiddleCertPath());
        X509Certificate certificate = initCert(unionPayConfig.getMiddleCertPath());
        certMap.put(certificate.getSerialNumber().toString(), certificate);
        log.info("Load MiddleCert Successful");
    }

    private static void initRootCert() {
        log.info("加载根证书==>" + unionPayConfig.getRootCertPath());
        X509Certificate certificate = initCert(unionPayConfig.getRootCertPath());
        certMap.put(certificate.getSerialNumber().toString(), certificate);
        log.info("Load RootCert Successful");
    }

    /**
     * 通过证书路径初始化为公钥证书
     *
     * @param certPath
     * @return
     */
    private static X509Certificate initCert(String certPath) {
        X509Certificate encryptCertTemp = null;
        CertificateFactory cf;
        Resource resource = new ClassPathResource(certPath);
        try {
            cf = CertificateFactory.getInstance(X_509_STANDARD, PROVIDER);
            encryptCertTemp = (X509Certificate) cf.generateCertificate(resource.getInputStream());
        } catch (CertificateException e) {
            log.error("InitCert Error", e);
        } catch (FileNotFoundException e) {
            log.error("InitCert Error File Not Found", e);
        } catch (NoSuchProviderException e) {
            log.error("LoadVerifyCert Error No BC Provider", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("LoadVerifyCert Error file not exists", e);
        }

        return encryptCertTemp;
    }

    /**
     * 通过keyStore 获取私钥签名证书PrivateKey对象
     *
     * @return
     */
    static PrivateKey getPfxCertPrivateKey() {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias,
                    unionPayConfig.getSignCertPwd().toCharArray());

            return privateKey;
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            log.error("getPfxCertPrivateKey Error", e);
            return null;
        }
    }

    /**
     * 获取配置文件acp_sdk.properties中配置的签名私钥证书certId  获取.pfx证书的序列号
     * 获取私钥签名证书序列号
     *
     * @return 签名证书序列号
     */
    public static String getPfxCertSerialNumber() {
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
            X509Certificate certificate = (X509Certificate) keyStore
                    .getCertificate(keyAlias);
            return certificate.getSerialNumber().toString();
        } catch (Exception e) {
            log.error("getSignCertId Error", e);
            return null;
        }
    }

    /**
     * 将签名私钥证书文件读取为证书存储对象
     *
     * @param pfxKeyFilePath 证书文件名
     * @param keyPwd         证书密码
     * @param type           证书类型
     * @return 证书对象
     */
    private static KeyStore getKeyStore(String pfxKeyFilePath, String keyPwd,
                                        String type) {
        Resource resource = new ClassPathResource(pfxKeyFilePath);
        try {
            KeyStore ks = KeyStore.getInstance(type, "BC");
            log.info("Load RSA CertPath=[" + pfxKeyFilePath + "],Pwd=[" + keyPwd + "],type=[" + type + "]");
            ks.load(resource.getInputStream(), keyPwd.toCharArray());
            return ks;
        } catch (Exception e) {
            log.error("getKeyInfo Error", e);
            return null;
        }
    }
}
