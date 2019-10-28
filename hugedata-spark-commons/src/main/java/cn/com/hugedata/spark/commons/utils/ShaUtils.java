package cn.com.hugedata.spark.commons.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * SHA算法工具.
 */
public class ShaUtils {
    /**
     * 计算SHA1并转换为字符串.
     * @param data Hash数据
     * @return     SHA1字符串

     */
    public static String hashToString(byte[] data) {
        return ByteUtils.toHexString(hash(data));
    }
    
    /**
     * 计算SHA1并转换为字符串.
     * @param data   Hash数据
     * @param offset 开始位置
     * @param length 长度
     * @return       SHA1字符串
     */
    public static String hashToString(byte[] data, int offset, int length) {
        return hashToString(Arrays.copyOfRange(data, offset, offset + length));
    }
    
    /**
     * 计算SHA1.
     * @param data Hash数据
     * @return     SHA1
     */
    public static byte[] hash(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] hashed = digest.digest(data);
            return hashed;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to get md5 algorithm.");
        }
    }
    
    /**
     * 计算SHA1.
     * @param data   Hash数据
     * @param offset 开始位置
     * @param length 长度
     * @return       SHA1
     */
    public static byte[] hash(byte[] data, int offset, int length) {
        return hash(Arrays.copyOfRange(data, offset, offset + length));
    }
}
