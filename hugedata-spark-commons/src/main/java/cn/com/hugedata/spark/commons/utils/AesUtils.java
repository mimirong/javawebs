package cn.com.hugedata.spark.commons.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES算法工具.
 */
public class AesUtils {
    /**
     * 解密数据.
     * @param data 需要解密的数据
     * @param key  密钥（16字节）
     * @return     解密后的数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            return aes.doFinal(data);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }

    /**
     * AES加密.
     * @param data 需要加密的数据
     * @param key  密钥（16字节）
     * @return     加密后的数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
            aes.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            return aes.doFinal(data);
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("Error when processing aes.", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }

    /**
     * AES加密字符串.
     * @param plain 需要加密的字符串
     * @param key   加密使用的密钥
     * @return      加密结果(Base64)
     */
    public static String encrypt(String plain, byte[] key) {
        try {
            byte[] data = plain.getBytes("UTF-8");
            byte[] encrypted = encrypt(data, key);
            return Base64Utils.encode(encrypted);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }

    /**
     * AES解密字符串.
     * 
     * @param encrypted 加密结果(Base64)
     * @param key       加密密钥
     * @return          解密后的字符串
     */
    public static String decrypt(String encrypted, byte[] key) {
        try {
            byte[] data = Base64Utils.decode(encrypted);
            byte[] decrypted = decrypt(data, key);
            return new String(decrypted, "UTF-8").trim();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }

    /**
     * AES加密字符串.
     * @param plain 需要加密的文本
     * @param key   加密密钥(实际使用的密钥为MD5(key))
     * @return      加密结果
     */
    public static String encrypt(String plain, String key) {
        try {
            return encrypt(plain, Md5Utils.hash(key.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }

    /**
     * AES解密字符串.
     * @param data 需要解密的字符串
     * @param key  解密密钥
     * @return     解密结果
     */
    public static String decrypt(String data, String key) {
        try {
            return decrypt(data, Md5Utils.hash(key.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error when processing aes.", e);
        }
    }
    
}
