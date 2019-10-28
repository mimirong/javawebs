package cn.com.hugedata.spark.commons.utils;

/**
 * 用于处理位置信息(经纬度)的工具类.
 * @author gaopeng
 */
public final class LocationUtils {

    /**
     * 地球半径.
     */
    private static final double EARTH_RADIUS = 6378.137;
    
    /**
     * 保存经纬度数据转换为Long型时的比例.
     */
    public static final long LOCATION_SAVING_RATIO = 10000000L;

    /**
     * 判断纬度值是否正确.
     * @param lat 纬度值
     * @return    是否正确
     */
    public static boolean isLatitudeValid(double lat) {
        return -90.0 <= lat && lat <= 90.0;
    }

    /**
     * 判断经度值是否正确.
     * @param lat 经度值
     * @return    是否正确
     */
    public static boolean isLongitudeValid(double lng) {
        return -180.0 <= lng && lng <= 180.0;
    }

    /**
     * 判断纬度值是否正确.
     * @param lat 纬度值
     * @return    是否正确
     */
    public static boolean isLatitudeValid(long lat) {
        return -90 * LOCATION_SAVING_RATIO <= lat && lat <= 90 * LOCATION_SAVING_RATIO;
    }

    /**
     * 判断经度值是否正确.
     * @param lat 经度值
     * @return    是否正确
     */
    public static boolean isLongitudeValid(long lng) {
        return -180 * LOCATION_SAVING_RATIO <= lng && lng <= 180 * LOCATION_SAVING_RATIO;
    }
    
    /**
     * 将double类型可显示的经纬度值转换为用于保存的long类型.
     * @param latOrLng double类型经纬度值
     * @return         long类型的经纬度值
     */
    public static long convertToLong(double latOrLng) {
        return (long) (latOrLng * LOCATION_SAVING_RATIO);
    }
    
    /**
     * 将用于保存的long类型经纬度值转换为用于显示的double类型.
     * @param latOrLng long类型的经纬度值
     * @return         double类型经纬度值
     */
    public static double convertToDouble(long latOrLng) {
        return (double) latOrLng / LOCATION_SAVING_RATIO;
    }
    
    /**
     * 计算两个经纬度点之间的距离.
     * @param lng1 第一个点经度
     * @param lat1 第一个点纬度
     * @param lng2 第二个点经度
     * @param lat2 第二个点维度
     * @return     距离(单位为米)
     */
    public static double calcDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 100000) / 100.0;
        return s;
    }

    /**
     * 计算两个经纬度点之间的距离.
     * @param lng1 第一个点经度
     * @param lat1 第一个点纬度
     * @param lng2 第二个点经度
     * @param lat2 第二个点维度
     * @return     距离(单位为米)
     */
    public static double calcDistance(long lng1, long lat1, long lng2, long lat2) {
        return calcDistance(
                convertToDouble(lng1),
                convertToDouble(lat1),
                convertToDouble(lng2),
                convertToDouble(lat2));
    }

    private static double rad(double d) {
       return d * Math.PI / 180.0;
    }
}
