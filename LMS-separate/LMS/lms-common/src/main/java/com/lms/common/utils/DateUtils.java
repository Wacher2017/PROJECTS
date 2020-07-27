package com.lms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String YYYY = "yyyy";
    public static String YYYYMM = "yyyyMM";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_STUB = "yyyy-MM";
    public static String YYYY_MM_DD_STUB = "yyyy-MM-dd";
    public static String YYYY_MM_DD_HH_MM_STUB = "yyyy-MM-dd HH:mm";
    public static String YYYY_MM_DD_HH_MM_SS_STUB = "yyyy-MM-dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM_SS_SSS_STUB = "yyyy-MM-dd HH:mm:ss.SSS";

    public static String YYYY_MM_SPRIT = "yyyy/MM";
    public static String YYYY_MM_DD_SPRIT = "yyyy/MM/dd";
    public static String YYYY_MM_DD_HH_MM_SPRIT = "yyyy/MM/dd HH:mm";
    public static String YYYY_MM_DD_HH_MM_SS_SPRIT = "yyyy/MM/dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM_SS_SSS_SPRIT = "yyyy/MM/dd HH:mm:ss.SSS";

    public static String YYYY_MM_POINT = "yyyy.MM";
    public static String YYYY_MM_DD_POINT = "yyyy.MM.dd";
    public static String YYYY_MM_DD_HH_MM_POINT = "yyyy.MM.dd HH:mm";
    public static String YYYY_MM_DD_HH_MM_SS_POINT = "yyyy.MM.dd HH:mm:ss";
    public static String YYYY_MM_DD_HH_MM_SS_SSS_POINT = "yyyy.MM.dd HH:mm:ss.SSS";

    public static String YYYY_MM_PARSE = "yyyy年MM月";
    public static String YYYY_MM_DD_PARSE = "yyyy年MM月dd日";
    public static String YYYY_MM_DD_HH_MM_PARSE = "yyyy年MM月dd日 HH时mm分";
    public static String YYYY_MM_DD_HH_MM_SS_PARSE = "yyyy年MM月dd日 HH时mm分ss秒";
    public static String YYYY_MM_DD_HH_MM_SS_SSS_PARSE = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
    };

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 格式化Date时间
     *
     * @param date   Date类型时间
     * @param format String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(final Date date, final String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化Timestamp时间
     *
     * @param timestamp Timestamp类型时间
     * @param format    String类型格式
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp, String format) {
        return new SimpleDateFormat(format).format(timestamp);
    }

    /**
     * 格式化Date时间
     *
     * @param date         Date类型时间
     * @param format       String类型格式
     * @param defaultValue 默认值为当前时间Date
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date date, String format, final Date defaultValue) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            logger.error("date format error: {} parse to {} error", date, format);
            if (defaultValue != null) {
                return parseDateToStr(defaultValue, format);
            } else {
                return parseDateToStr(new Date(), format);
            }
        }
    }

    /**
     * 格式化Date时间
     *
     * @param date         Date类型时间
     * @param format       String类型格式
     * @param defaultValue 默认时间值String类型
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date date, String format, final String defaultValue) {
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            logger.error("date format error: {} parse to {} error", date, format);
            return defaultValue;
        }
    }

    /**
     * 将字符串类型日期时间转换为Date类型
     *
     * @param date   String类型时间
     * @param format String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String date, String format) {
        if (date == null || date.equals("")) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            logger.error("format error: {} parse to date type data error", date);
            return null;
        }
    }

    /**
     * 格式化String时间
     *
     * @param date         String类型时间
     * @param format       String类型格式
     * @param defaultValue 异常时返回的默认值
     * @return
     */
    public static Date parseStrToDate(String date, String format, Date defaultValue) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            logger.error("format error: {} parse to date type data error", date);
            return defaultValue;
        }
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseStrToDate(String date) {
        if (date == null) {
            return null;
        }
        try {
            return parseDate(date, parsePatterns);
        } catch (Exception e) {
            logger.error("format error: {} parse to date type data error", date);
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

}
