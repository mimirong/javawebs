package cn.com.hugedata.spark.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

/**
 * Base64工具类.
 */
public final class Base64Utils {

    /**
     * 不允许创建实例.
     */
    private Base64Utils() {
    }
    
    /**
     * 将一个java对象编码为字符串.
     * @param obj 需要序列化的对象
     * @return    序列化结果
     */
    public static String toBase64(Serializable obj) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out)) {

            oos.writeObject(obj);
            oos.flush();
            out.flush();
            return encode(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 将编码的字符串解码成对象.
     * @param base64code 需要解析的字符串
     * @return 解析结果对象
     */
    public static Object toObject(String base64code) {
        ByteArrayInputStream in = null;
        ObjectInputStream ois = null;
        try {
            byte[] bytes = decode(base64code);
            in = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(in);
            return ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        } finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(in);
        }
    }
    
    /**
     * Base64编码.
     * @param data 需要编码的数据.
     * @return 编码后的字符串
     */
    public static String encode(byte[] data) {
        Base64 base64 = new Base64();
        return base64.encodeToString(data);
    }
    
    /**
     * Base64解码.
     * @param data 需要解码的字符串
     * @return 解码后的数据
     */
    public static byte[] decode(String data) {
        Base64 base64 = new Base64();
        return base64.decode(data);
    }
}
