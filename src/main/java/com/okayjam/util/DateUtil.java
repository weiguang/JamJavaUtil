package com.okayjam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: Chen weiguang <chen2621978@gmail.com>
 * @create: 2019/01/29 18:24
 **/
public class DateUtil {

    public static final String H_M = "HH:mm";
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M = "yyyy-MM";
    public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String HM = "HHmm";
    public static final String YMD = "yyyyMMdd";
    public static final String YMDHM = "yyyyMMddHHmm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final long ONE_DAY_MILLS = 3600000 * 24;

    //使用ThreadLocal代替原来的new SimpleDateFormat
    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));



    /**
     * 日期转换为制定格式字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String formatDate(Date time, String format) {
        return sdf.get().format(time);
    }

    /**
     * 字符串转换为制定格式日期
     * (注意：当你输入的日期是2014-12-21 12:12，format对应的应为yyyy-MM-dd HH:mm
     * 否则异常抛出)

     * @param date
     * @param format
     * @return
     * @throws ParseException
     * @
     */
    public static Date parseDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.toString());
        }
    }

    /**
     * 获取两个日期的天数之差
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDaysBetween(Date startDate, Date endDate) {
        if(startDate == null || endDate == null) {return 0;}
        return (int) ((endDate.getTime() - startDate.getTime()) / ONE_DAY_MILLS);
    }

    /**
     * 根据日期取得星期几
     * @param date
     * @return
     */
    public static String getWeek(Date date){
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        return weeks[week_index];
    }

    /**
     * 根据指定的日期，增加或者减少天数
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 根据指定的日期，类型，增加或减少数量
     *
     * @param date
     * @param calendarField
     * @param amount
     * @return
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 获取相对于这一周的，周几时间
     * @param DayOfWeek 获取星期几
     * @param whichWeek 相对于这周的数，例如0标识这周，1表示下周，-1表示上周
     * @return 返回时间
     */
    public static Date getWeekDateFromNow(int DayOfWeek, int whichWeek){
        return getWeekDate(Calendar.getInstance(), DayOfWeek, whichWeek);
    }
    /**
     * 获取相对于这一周的，周几时间
     * @param cal 输入日期
     * @param DayOfWeek 获取星期几
     * @param whichWeek 相对于这周的数，例如0标识这周，1表示下周，-1表示上周
     * @return 返回时间
     */
    public static Date getWeekDate(Calendar cal, int DayOfWeek, int whichWeek){
        //判断当前日期是否为周末，因为周末是本周第一天，如果不向后推迟一天的到的将是下周一的零点，而不是本周周一零点
        if (1 == cal.get(Calendar.DAY_OF_WEEK)){
            cal.add(Calendar.DATE, -1);
        }
        cal.set(Calendar.DAY_OF_WEEK, DayOfWeek);
        cal.add(Calendar.DATE, 7 * whichWeek);
        return cal.getTime();
    }


    public static void main(String[] args) {
//        System.out.println(getWeek(new Date()));
        System.out.println(addDays(new Date(),1));
    }

}
