package cn.com.hugedata.spark.commons.utils;

/**
 * 字节处理工具类.
 */
public final class ByteUtils {

    /**
     * 不允许创建实例.
     */
    private ByteUtils() {
    }
    
    /**
     * 将字节数据转换为可输出的表格形式.
     * @param data 字节数据
     * @return     可输出的表格，每行16字节
     */
    public static String toHexChart(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (i % 16 == 0 && i != 0) {
                sb.append("\n");
            }
            if (i % 16 == 8) {
                sb.append(" ");
            }
            sb.append(String.format("%02x ", data[i]));
        }
        return sb.toString();
    }
    
    /**
     * 将字节数据转换为以16进制表示的字符串.
     * @param data 字节数据
     * @return     以16进制表示的字符串
     */
    public static String toHexString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%02x", data[i]));
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换为字节.
     * @param str 十六进制编码字符串
     * @return 字节数据
     */
    public static byte[] toBytes(String str) {
        byte[] data = new byte[str.length() / 2];
        for (int i = 0; i < data.length; i++) {
            int a = digitToInteger(str.charAt(i * 2));
            int b = digitToInteger(str.charAt(i * 2 + 1));
            int c = a * 16 + b;
            data[i] = ubyteToByte(c);
        }
        return data;
    }
    
    /**
     * 合并byte数组.
     * @param data 需要合并的数据(一个或多个)
     * @return 合并后的数组
     */
    public static byte[] combine(byte[]... data) {
        int len = 0;
        for (int i = 0; i < data.length; i++) {
            len += data[i].length;
        }
        byte[] ret = new byte[len];
        int pos = 0;
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, ret, pos, data[i].length);
            pos += data[i].length;
        }
        return ret;
    }
    
    //------------------------------------------------------------------------------------------------------------------
    
    /**
     * 将十六进制字符转换为对应的十进制数值.
     * @param c 十六进制字符(0-9, a-f, A-F)
     * @return 对应的十进制值
     */
    private static int digitToInteger(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }
        return 0;
    }
    
    /**
     * 将8位无符号值转换为有符号值.
     * @param b 8位无符号数值
     * @return 转换后的有符号数值
     */
    private static byte ubyteToByte(int b) {
        if (b <= 127) {
            return (byte) b;
        } else {
            return (byte) (b - 256);
        }
    }
}
