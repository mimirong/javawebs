package cn.com.hugedata.spark.commons.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomUtils;

/**
 * 用于加解密密码.
 */
public class PasswordEncryption {

    /**
     * 生成随机的AES加密密钥并返回Base64编码后的结果.
     * @return 密码加密密钥
     */
    public static String randomKey() {
        byte[] key = RandomUtils.nextBytes(16);
        return Base64.encodeBase64String(key);
    }
    
    /**
     * 加密密码，返回Base64编码后的结果.
     * @param password          密码明文
     * @param base64EncodedKey  Base64编码后的加密密钥
     * @return                  加密后的密码
     */
    public static String encrypt(String password, String base64EncodedKey) {
        try {
            byte[] plain = password.getBytes("UTF-8");
            byte[] key = Base64.decodeBase64(base64EncodedKey);
            byte[] encrypted = AesUtils.encrypt(plain, key);
            return Base64.encodeBase64String(encrypted);
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 解密密码，返回Base64编码后的结果.
     * @param encryptedPassword 密码密文
     * @param base64EncodedKey  Base64编码后的加密密钥
     * @return                  密码明文
     */
    public static String decrypt(String encryptedPassword, String base64EncodedKey) {
        try {
            byte[] encrypted = Base64.decodeBase64(encryptedPassword);
            byte[] key = Base64.decodeBase64(base64EncodedKey);
            
            byte[] decrypted = AesUtils.decrypt(encrypted, key);
            return new String(decrypted, "UTF-8");
            
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void main(String[] args) {
        String key = randomKey();
        System.out.println("Key: " + key);
        String encrypted = encrypt("123456", key);
        System.out.println("Encrypted: " + encrypted);
    }
}
