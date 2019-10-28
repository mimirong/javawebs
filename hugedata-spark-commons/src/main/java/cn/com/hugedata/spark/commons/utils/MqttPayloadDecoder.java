package cn.com.hugedata.spark.commons.utils;

import java.io.UnsupportedEncodingException;

/**
 * 用于解析MQTT服务推送的Payload信息.
 * @author gaopeng
 */
public final class MqttPayloadDecoder {
    
    /**
     * 解析MQTT推送的Payload信息.
     * @param bytes                     Payload数据
     * @return                          解析后的JSON
     * @throws IllegalArgumentException 解析失败
     */
    public static String decode(byte[] bytes) throws IllegalArgumentException {
        try {
            int pos = -1;
            for (pos = 0; pos < Math.min(10, bytes.length); pos++) {
                if (bytes[pos] == '#') {
                    break;
                }
            }
            
            String lenStr = new String(bytes, 0, pos, "ASCII");
            int len = Integer.parseInt(lenStr);
            
            String data = new String(bytes, pos + 1, len, "UTF-8");
            return data;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to decode 'message length' data.", e);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Failed to decode payload.", e);
        }
    }
    
}
