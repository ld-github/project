package com.ld.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * <p>Title: DateUtil</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:时间工具类</p>
 *
 * @author LD
 *
 * @date 2015-08-14
 */
public class DateUtil {

    public static final String TEMPORALTYPE_TIMESTAMP = "yyyy-MM-dd hh:mm:ss";
    public static final String TEMPORALTYPE_DATE = "yyyy-MM-dd";
    public static final String TEMPORALTYPE_TIME = "hh:mm:ss";

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化当前时间
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String formatNow(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(Date date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(format(date, pattern));
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parse(String date, String pattern) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.parse(date);
    }

    /**
     * 
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseNow(String pattern) throws ParseException {
        return parse(new Date(), pattern);
    }

    /**
     * 计算天
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date calcuDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 当前时间基础上计算天
     * 
     * @param day
     * @return
     */
    public static Date calcuDateNow(int day) {
        return calcuDate(new Date(), day);
    }
}
