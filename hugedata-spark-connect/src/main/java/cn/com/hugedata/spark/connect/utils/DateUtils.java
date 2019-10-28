package cn.com.hugedata.spark.connect.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.hugedata.spark.commons.exception.ServiceException;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 将时间与当前时间比
     * @param date        传参时间
     * @param dateFormat   时间格式
     * @return String      当前时间是今天 就返回“今天 时分”
     *                              昨天 就返回“昨天 时分”
     *                              在这两天时间之前 就返回 “年 月 日 时分”
     */
    public static String convertDateToStr(Date date, String dateFormat) {

        SimpleDateFormat newSdf = new SimpleDateFormat("HH:mm"); //转换成时分格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);          //参数时间
        Calendar currCal = Calendar.getInstance();
        currCal.setTime(new Date());  //当期时间
        Integer gap = currCal.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        switch (gap){
            case 0:
                return "今天 " + newSdf.format(date);
            case 1:
                return "昨天 " + newSdf.format(date);
            default:
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                String str = sdf.format(date);
                return str;
        }
    }

    /**
     * 将时间字符串转换成时间
     * @param dateStr      时间字符串
     * @param dateFormat   时间格式
     * @return
     */
    public static Date convertDate(String dateStr,String dateFormat){
        DateFormat format = new SimpleDateFormat(dateFormat);
        try {
            Date  date = format.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     * 转换时间
     * @param date        传参时间
     * @param dateFormat  时间格式
     * @return String
     */
    public static String convertDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String str = sdf.format(date);
        return str;
    }
    
    private DateUtils(){
        
    }

    /**
     *
     * @Title: getDayArray
     * @Description: 获取时间段内 日期集合
     * @param beginTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return List<String>
     * @throws
     */
    public static List<String> getMonthArray(String beginTime, String endTime) throws ServiceException {
        if(StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)){
            return new ArrayList<String>();
        }
        List<String> strList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date beginDate = new SimpleDateFormat("yyyy-MM").parse(beginTime);
            Date endDate = new SimpleDateFormat("yyyy-MM").parse(endTime);//定义结束日期
            Calendar dd = Calendar.getInstance();//定义日期实例
            dd.setTime(beginDate);//设置日期起始时间
            while(dd.getTime().before(endDate)){//判断是否到结束日期
                String str = sdf.format(dd.getTime());
                strList.add(str);
                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }
            String endDateStr = sdf.format(endDate.getTime());
            strList.add(endDateStr);
        }
        catch (ParseException e) {
            LOGGER.error("日期格式不正确");
            throw new ServiceException("InvalidDate", "日期格式不正确");
        }
        return strList;
    }

    public static Date getMonthLastDay(Date date) throws ServiceException {
        // 获取当前月的最后一天
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        return cal.getTime();
    }

    public static void main(String[] args) {
        String startTime = "2017-01-19";
        String endTime = "2017-03-19";

        try {
            List<String> strList = getMonthArray(startTime,endTime);
            for (String day : strList){
                LOGGER.info(day);
            }
        } catch (ServiceException e) {
            LOGGER.error("main 日期格式不正确");
        }

    }

    public static Date subtractYear(Date date,int year){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        return c.getTime();
    }


}
