package com.hikvision.background.web.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;

/**
 * AES算法进行加密
 *
 * @author CodeGeek
 * @create 2018-01-23 上午11:00
 **/
public class EncryptUtils {

    /**
     * 密钥
     */
    public static final String KEY = "hj12345678987654";// AES加密要求key必须要128个比特位（这里需要长度为16，否则会报错）

    /**
     * 算法
     */
    public static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception 抛出异常
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     *
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }


    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    /*public static void main(String[] args) throws Exception {
        String content = "123456";
        System.out.println("加密前：" + content);

        System.out.println("加密密钥和解密密钥：" + KEY);

        String encrypt = aesEncrypt(content, KEY);
        System.out.println("加密后：" + encrypt);

        String decrypt = aesDecrypt(encrypt, KEY);

        System.out.println("解密后：" + decrypt);
    }*/
}