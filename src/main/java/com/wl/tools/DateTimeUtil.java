package com.wl.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 公共日期时间工具类
 * @author wanglu
 * @date 2015.10.28
 * @version 1.0
 */
public final class DateTimeUtil {
    private static final Log log = LogFactory.getLog(DateTimeUtil.class);
    
    public static final String DATE_CHINESE ="yyyy年MM月dd日   HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     * */
    public static final String DATE_24SECOND_ = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd HH:mm
     * */
    public static final String DATE_24MINITE_ = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式：yyyy-MM-dd HH
     * */
    public static final String DATE_24HOUR_   = "yyyy-MM-dd HH";
    /**
     * 日期格式：yyyy-MM-dd
     * */
    public static final String DATE_DAY_	  = "yyyy-MM-dd";
    /**
     * 日期格式：yyyy-MM
     * */
    public static final String DATE_MONTH_    = "yyyy-MM";
    /**
     * 日期格式：yyyy
     * */
    public static final String DATE_YEAR_     = "yyyy";
    /**
     * 日期格式：HH:mm:ss
     * */
    public static final String DATE_24TIME_   = "HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd hh
     * */
    public static final String DATE_12HOUR_   = "yyyy-MM-dd hh";
    /**
     * 日期格式：yyyy-MM-dd hh:mm
     * */
    public static final String DATE_12MINITE_ = "yyyy-MM-dd hh:mm";
    /**
     * 日期格式：yyyy-MM-dd hh:mm:ss
     * */
    public static final String DATE_12SECOND_ = "yyyy-MM-dd hh:mm:ss";
    /**
     * 日期格式：hh:mm:ss
     * */
    public static final String DATE_12TIME_   = "hh:mm:ss";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss
     * */
    public static final String DATE_24SECOND  = "yyyy/MM/dd HH:mm:ss";
    /**
     * 日期格式：yyyy/MM/dd HH:mm
     * */
    public static final String DATE_24MINITE  = "yyyy/MM/dd HH:mm";
    /**
     * 日期格式：yyyy/MM/dd HH
     * */
    public static final String DATE_24HOUR    = "yyyy/MM/dd HH";
    /**
     * 日期格式：yyyy/MM/dd
     * */
    public static final String DATE_DAY       = "yyyy/MM/dd";
    /**
     * 日期格式：yyyy/MM
     * */
    public static final String DATE_MONTH     = "yyyy/MM";
    /**
     * 日期格式：yyyy/MM/dd hh
     * */
    public static final String DATE_12HOUR    = "yyyy/MM/dd hh";
    /**
     * 日期格式：yyyy/MM/dd hh:mm
     * */
    public static final String DATE_12MINITE  = "yyyy/MM/dd hh:mm";
    /**
     * 日期格式：yyyy/MM/dd hh:mm:ss
     * */
    public static final String DATE_12SECOND  = "yyyy/MM/dd hh:mm:ss";
    /**
     * 日期格式：yyyyMMddHHmmss
     * */
    public static final String DATE24SECOND   = "yyyyMMddHHmmss";
    /**
     * 日期格式：yyyyMMddhhmmss
     * */
    public static final String dATE12SECOND   = "yyyyMMddhhmmss";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:S
     * */
    public static final String DATE_12MILL_1  = "yyyy/MM/dd HH:mm:ss:S";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:SS
     * */
    public static final String DATE_12MILL_2  = "yyyy/MM/dd HH:mm:ss:SS";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:SSS
     * */
    public static final String DATE_12MILL_3  = "yyyy/MM/dd HH:mm:ss:SSS";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:S
     * */
    public static final String DATE_12MILL_1_ = "yyyy-MM-dd HH:mm:ss:S";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:SS
     * */
    public static final String DATE_12MILL_2_ = "yyyy-MM-dd HH:mm:ss:SS";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:SSS
     * */
    public static final String DATE_12MILL_3_ = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:S
     * */
    public static final String DATE_24MILL_1  = "yyyy/MM/dd HH:mm:ss:S";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:SS
     * */
    public static final String DATE_24MILL_2  = "yyyy/MM/dd HH:mm:ss:SS";
    /**
     * 日期格式：yyyy/MM/dd HH:mm:ss:SSS
     * */
    public static final String DATE_24MILL_3  = "yyyy/MM/dd HH:mm:ss:SSS";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:S
     * */
    public static final String DATE_24MILL_1_ = "yyyy-MM-dd HH:mm:ss:S";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:SS
     * */
    public static final String DATE_24MILL_2_ = "yyyy-MM-dd HH:mm:ss:SS";
    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss:SSS
     * */
    public static final String DATE_24MILL_3_ = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * 日期格式：yyyyMMddhhmmssS
     * */
    
    public static final String DATEMILL_1     = "yyyyMMddhhmmssS";
    /**
     * 日期格式：yyyyMMddhhmmssSS
     * */
    public static final String DATEMILL_2     = "yyyyMMddhhmmssSS";
    /**
     * 日期格式：yyyyMMddhhmmssSSS
     * */
    public static final String DATEMILL_3     = "yyyyMMddhhmmssSSS";

    /**
     * 默认构造方法 不允许实例化
     */
    private DateTimeUtil() {

    }

    /**
     * 将date型日期转换为想要的字符格式
     * @param date       Date 类型的日期
     * @param dateFormat 日期格式，工具类里面有
     * @return String
     */
    public static String formatDateToString(Date date, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 将String型日期转换为想要的date型 <一句话功能简述> <功能详细描述>
     * @param currentTime String类型日期
     * @param dateFormat  日期格式
     * @return Date
     */
    public static Date formatStringToDate(String currentTime, String dateFormat) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            date = df.parse(currentTime);
        }
        catch (ParseException e) {
            log.error("SimpleDateFormat parse is ERROR", e);
        }
        return date;
    }

    /**
     * 得到当前格林威治的日期和时间
     * @param dateFormat 日期格式：如yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String getUTCDateTimeNow(String dateFormat) {
        // 取时区
        TimeZone zone = TimeZone.getDefault();

        Calendar c = Calendar.getInstance();

        Date date = new Date();

        // 计算时区偏差
        c.setTimeInMillis(date.getTime() - zone.getOffset(date.getTime()));

        // 格式化
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        String dateString = formatter.format(c.getTime());

        return dateString;
    }
    /**
     * 获得当前时间
     * @return "YYYY-MM-DD"
     */
    public static String getTime() {
        String temp = "yyyy-MM-dd HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(temp);
        java.util.Date currentTime = new java.util.Date();
        return formatter.format(currentTime);
    }
    /**
     * 获取当前系统毫秒数的Long型
     * */
    public static Long getMills(){
    	return System.currentTimeMillis();
    }

}


