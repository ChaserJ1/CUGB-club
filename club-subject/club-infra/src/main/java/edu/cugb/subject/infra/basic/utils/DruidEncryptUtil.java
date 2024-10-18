package edu.cugb.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @Author pengjia
 * @Data 2024/10/16 20:57
 * @Description: 数据库加密
 */
public class DruidEncryptUtil {
    private static String publicKey;

    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);

            privateKey = keyPair[0];
            System.out.println("私钥：" + privateKey);
            publicKey = keyPair[1];
            System.out.println("公钥：" + publicKey);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {//加密
        return ConfigTools.encrypt(privateKey, plainText);
    }

    public static String decrypt(String encryptText) throws Exception {//解密
        return ConfigTools.decrypt(publicKey, encryptText);
    }

    public static void main(String[] args) throws Exception {

        String encrypt = encrypt("123456");
        System.out.println("加密后的密码为：" + encrypt);
        String decrypt = decrypt(encrypt);
        System.out.println(decrypt);
    }
}
