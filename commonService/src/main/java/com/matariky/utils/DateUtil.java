package com.matariky.utils;

import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.utils.RenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /** oracle Type 的24小时制时分秒 */
    public static final String ORACLE_DATE_TIME_24_PATTERN = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_TIME_24_PATTERN_SIMPLE = "yyyyMMddHHmmss";
    /** 格式完整的日期时分秒 */
    public static final String DATE_TIME_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FULL_SIMPLE = "yyyy-MM-dd HH:mm";
    /** 格式日期 时分 */
    public static final String DATE_HM_TIME_PATTERN = "HH:mm";
    public static final String DATE_TIME_YEAR_MONTH_DAY = "yyyy年MM月dd日";
    public static final String DATE_TIME_YEAR_MONTH_DAY_HM = "yyyy年MM月dd日 HH:mm";
    /** 日期格式到天 */
    public static final String DATE_TIME_DAY = "yyyy-MM-dd";
    /** 只得到 Time ,不要年月日 */
    public static final String DATE_TIME_TIME = "HH:mm:ss";
    /** 日期格式不带中横杠 */
    public static final String DATE_TIME_DAY_SIMPLE = "yyyyMMdd";
    /** 日期格式不带中横杠 年月格式 */
    public static final String DATE_TIME_DAY_SIMPLE_NO_DAY = "yyyyMM";
    /** Time 格式 */
    public static final String DATE_TIME_TERM = "HH.mm.ss";
    public static final String DATE_TIME_CHINESE = "MM月dd日HH时mm分";

    public static final int C_ONE_SECOND = 1000;
    public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
    public static final long C_ONE_HOUR = (long) 60 * C_ONE_MINUTE;
    public static final long C_ONE_DAY = (long) 24 * C_ONE_HOUR;

    public static final String DAY_ENDTIME = "235959";

    /**
     * Retrieve指定年和月份的最后一天
     *
     * @param year
     * @param month
     * @return lastDayOfMonth = 2019-10-31
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // Configuration年份
        cal.set(Calendar.YEAR, year);
        // Configuration月份
        cal.set(Calendar.MONTH, month - 1);
        // Retrieve某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // Configuration日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * 根据传入的字符串 Time 格式成yyyy-MM-dd
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatStringDate(String date) {
        if (date == null)
            return null;
        return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
    }

    /**
     * 根据传入的字符串 Time 格式成yyyyMMdd
     *
     * @param date
     * @return yyyyMMdd
     */
    public static String StringDate(String date) {
        if (date == null)
            return null;
        return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
    }

    /**
     * Retrieve Current Time yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Retrieve Current Time yyyyMMddHHmmss
     */
    public static String getCurrentTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    /**
     * Retrieve Current Time 的年月日yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDateyyyyMMdd() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Retrieve Current Time 的年月日yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDateyyyyMMdd(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * Retrieve Current Time 的 时分秒
     *
     * @return
     */
    public static String getCurrentTimeHHmmss() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Date型的 Time 转换成String型的格式为：yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 返回指定日期格式的字符串 如：yyyyMMdd,yyyyMMddhhmmss
     *
     * @param format
     * @return yyyyMMdd,yyyyMMddhhmmss
     */
    public static String getCurrentDateFormatStr(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    /**
     * Date型的 Time 转换成String型的格式为：yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */

    public static String getStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Retrieve Current 日期
     */
    public static Date getCurrentDate() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String curDate = simpleDateFormat.format(new Date());

        try {
            return simpleDateFormat.parse(curDate);
        } catch (ParseException e) {

            logger.error(e.getMessage());
        }
        return null;
    }

    public static Date getCurrentDate3() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String curDate = simpleDateFormat.format(new Date());

        try {
            return simpleDateFormat.parse(curDate);
        } catch (ParseException e) {

            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 获得 Current 日期和 Time
     *
     * @return
     */
    public static Date getCurrentDateAndTime() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String curDate = simpleDateFormat.format(new Date());

        try {
            return simpleDateFormat.parse(curDate);
        } catch (ParseException e) {

            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieve格式后的 Data ,返回yyyyMMdd
     */
    public static String getFormatTime(String Date) {
        if (null == Date) {
            return Date;
        }
        if (Date.equals("")) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(Date.substring(0, 4));
        strBuf.append(Date.substring(5, 7));
        strBuf.append(Date.substring(8, 10));
        return strBuf.toString();
    }

    public static String dbFormatToDateFormat(String dbFormat) {
        if (dbFormat != null && !"".equals(dbFormat) && dbFormat.trim().length() >= 8) {
            StringBuffer strBuf = new StringBuffer(dbFormat.substring(0, 4));
            strBuf.append("-");
            strBuf.append(dbFormat.substring(4, 6));
            strBuf.append("-");
            strBuf.append(dbFormat.substring(6, 8));
            strBuf.append(dbFormat.substring(8));
            return strBuf.toString();
        }
        return dbFormat;
    }

    /**
     * Date日期转换成String
     */
    public static final String date2String(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * Date日期转换成String
     */
    public static final String date2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /***
     * Date日期转成带-String
     */
    public static final String dateToSting_(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * String日期转换成Date
     */
    public static final Date string2date(String dateStr) {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * String日期转换成Date( yyyy-MM-dd HH:mm:ss)
     *
     * @throws ParseException
     */
    public static final Date string2Datetime(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(dateStr);
    }

    /**
     * String转换成Date
     */
    public static final Timestamp string2timestamp(String dateStr) {
        Date date;
        try {
            date = string2Datetime(dateStr);

            if (null != date) {
                return new Timestamp(date.getTime());
            } else {
                return null;
            }
        } catch (ParseException e) {
            logger.error("string2timestamp 日期转换出问题", e.getMessage());
            throw new RenException("string2timestamp 日期转换出问题：" + e);
        }
    }

    /**
     * String日期转换成Date(yyyymmdd)
     */
    public static final Date string2Dateyyyymmdd(String dateStr) {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * String日期转换成Date(yyyyMMddHHmmss)
     */
    public static final Date string2DateyyyyMMddHHmmss(String dateStr) {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 按日加,指定日期
     *
     * @param date
     * @param value
     * @return
     */
    public static final Date addDay(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DAY_OF_YEAR, value);
        return now.getTime();
    }

    /**
     * 按小时加 ,指定日期
     *
     * @param date
     * @param value
     * @return
     */
    public static final Date addHours(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.HOUR_OF_DAY, value);
        return now.getTime();
    }

    /**
     * 按分钟加 ,指定日期
     *
     * @param date
     * @param value
     * @return
     */
    public static final Date addMinutes(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MINUTE, value);
        return now.getTime();
    }

    /**
     * 按秒加 ,指定日期
     *
     * @param date
     * @param value
     * @return
     */
    public static final Date addSecond(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.SECOND, value);
        return now.getTime();
    }

    /**
     * 按月加
     *
     * @param value
     * @return
     */
    public static final Date addMonth(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        // now.add(Calendar.MONTH, 1);
        // now.set(Calendar.DATE, value);
        // return now.getTime();
        now.add(Calendar.MONTH, value);
        return now.getTime();
    }

    /**
     * 计算两个日期之间相差的 Time （毫秒）
     *
     * @param startDate Start Time
     * @param endDate   结束 Time
     * @return 返回两个 Time 相差的毫秒数
     */
    public static long getInterval(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long interval = endTime - startTime;
        return interval;
    }

    /**
     * 计算两个日期之间相差的 Time （秒）
     *
     * @param startDate Start Time
     * @param endDate   结束 Time
     * @return 返回两个 Time 相差的毫秒数
     */
    public static int getIntervalSeconds(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        int interval = (int) ((endTime - startTime) / 1000);
        return interval;
    }

    /**
     * 计算两个日期之间相差的 Time （天）
     *
     * @param startDate Start Time
     * @param endDate   结束 Time
     * @return 返回两个 Time 相差的毫秒数
     */
    public static int caculate2Days(Date startDate, Date endDate) {
        long calendarNum1 = startDate.getTime();
        long calendarNum2 = endDate.getTime();
        return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param startDate
     * @param endDate
     * @return 相差天数
     *
     * @throws ParseException
     */
    public static int daysBetween(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(sdf.format(startDate));
        Date date2 = sdf.parse(sdf.format(endDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();

        return Math.abs((int) ((time1 - time2) / C_ONE_DAY));
    }

    /**
     * 将StringType 转成dateType 功能 Description : <br>
     * 〈功能Detail Description 〉
     *
     * @param dateStr
     * @return
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static Date parseStr2Date(String dateStr, String pattern) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            logger.error("日期转换 Error ：" + e.getMessage());
            throw new RenException("日期转换 Error ：" + e);
        }
    }

    /**
     * 将StringType 转成dateType 功能 Description : <br>
     * 〈功能Detail Description 〉
     *
     * @param dateStr
     * @return
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static Date parseStr2DateIgnoreError(String dateStr, String pattern) {

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            logger.error("日期转换 Error ：", e);
        }
        return date;
    }

    /**
     * 将日期格式转换成String格式
     *
     * @param date           需要转换的 Time
     * @param pattern        转换的 Time 格式
     * @param allowException 转为时为空 , Wether 抛出异常。如果为false ,则返回空字符串
     * @return
     */
    public static String parseDate2String(Date date, String pattern, boolean allowException) {
        if (null == date) {
            if (allowException) {
                throw new IllegalArgumentException("the date must not be null!");
            } else {
                return "";
            }
        }
        SimpleDateFormat dateFormart = new SimpleDateFormat(pattern);
        return dateFormart.format(date);
    }

    /**
     * 将Date转换成指定格式的string
     *
     * @param date    需要转换的 Time
     * @param pattern 转换成的 Time 格式
     * @return
     */
    public static String parseDate2String(Date date, String pattern) {
        return parseDate2String(date, pattern, false);
    }

    /**
     * 目标 Time Wether 在起止 Time 段内,起止 Time 都为空表示不判断 起始 Time 为空 ,认为不考虑起始 Time 截至 Time
     * 为空 ,认为不考虑截至 Time date.before after不包含日期相同的情况。
     *
     * @param destDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenDate(Date destDate, Date startDate, Date endDate) {
        if (destDate == null) {
            return false;
        }

        if (startDate == null && endDate == null) {
            return true;
        }

        if (startDate == null && endDate != null) {
            return endDate.compareTo(destDate) >= 0;
        } else if (startDate != null && endDate == null) {
            return destDate.compareTo(startDate) >= 0;
        } else {
            return (destDate.compareTo(startDate) >= 0 && endDate.compareTo(destDate) >= 0);
        }

    }

    /**
     * 上个月最后一天
     *
     * @return
     */
    public static Date getLastMonthEnd() {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.MONTH, -1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        return endCalendar.getTime();
    }

    /**
     * 去年最后一天
     *
     * @return
     */
    public static Date getLastYearEnd() {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.YEAR, -1);
        endCalendar.set(Calendar.MONTH, 12 - 1);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 0);
        endCalendar.set(Calendar.SECOND, 0);
        endCalendar.set(Calendar.MILLISECOND, 0);
        return endCalendar.getTime();
    }

    /**
     * Current 日期所在季度最后一天年Y 月M 日D
     *
     * @return
     */
    public static Map<String, Integer> getQuarterEndYMD() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Date date = new Date();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(date);
        endCalendar.set(Calendar.DATE, 0);
        int Day = endCalendar.get(Calendar.DATE);
        int year = endCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH) + 1;

        int lastQuarterMonth = 0;
        if (month < 3) {
            lastQuarterMonth = 3; // 3月
        } else if (month < 6) {
            lastQuarterMonth = 6; // 6月
        } else if (month < 9) {
            lastQuarterMonth = 9;// 9月
        } else {
            lastQuarterMonth = 12;// 12月
        }

        map.put("year", year);
        map.put("month", lastQuarterMonth);
        map.put("day", Day);

        return map;
    }

    /**
     * 计算 Time Wether 失效(入参 Time 小于 Current Time )（毫秒）
     *
     * @param startDate Start Time
     * @param endDate   结束 Time
     * @return 返回两个 Time 相差的毫秒数
     */
    /**
     * 功能 Description : 计算 Time Wether 失效(入参 Time 小于 Current Time )<br>
     * 〈功能Detail Description 〉
     *
     * @param date
     * @return true:失效；false:未失效
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static boolean isInvalid(Date date) {
        if (date == null) {
            return true;
        }
        long startTime = date.getTime();
        long endTime = new Date().getTime();
        long interval = endTime - startTime;
        if (interval >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能 Description : Retrieve当天的起始 Time <br>
     * 〈功能Detail Description 〉
     *
     * @param date
     * @return
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static Date getStartTimeOfDay(Date date) {

        Calendar todayStart = DateUtil.toCalendar(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();

    }

    /**
     * 功能 Description : Retrieve当天的结束 Time <br>
     * 〈功能Detail Description 〉
     *
     * @param date
     * @return
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static Date getEndTimeOfDay(Date date) {
        Calendar todayEnd = DateUtil.toCalendar(date);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 99);
        return todayEnd.getTime();
    }

    /**
     * 将StringType 转成dateType 功能 Description : <br>
     * 〈功能Detail Description 〉
     *
     * @param dateStr
     * @return
     *
     * @see [相关类/ Method ](可选)
     * @since [产品/模块 Version](可选)
     */
    public static boolean validateDate(String dateStr, String pattern) {
        if (StringUtil.isEmpty(dateStr)) {
            return false;
        }

        boolean isDate = true;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("日期转换 Error ：" + e.getMessage());
            isDate = false;
        }
        return isDate;
    }

    // Retrieve指定 Time 月份第一天0点
    public static Date getFirstDayOfMonth(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);// Configuration为1号, Current 日期既为本月第一天
        String first = format.format(cal.getTime());
        return format.parse(first);
    }

    /**
     * @Description: Time 搓转localDateTime
     * @Author: bo.chen
     * @Date: 2023/9/13 15:08
     * @param timestamp
     * @return java.time.LocalDateTime
     **/
    public static LocalDateTime toLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * @Description: Time 转cron表达式
     * @Author: bo.chen
     * @Date: 2023/9/25 10:05
     * @param localDateTime
     * @return java.lang.String
     **/
    public static String getCron(LocalDateTime localDateTime) {
        return localDateTime.format(createFormatter("ss mm HH dd MM ? yyyy"));
    }

    /**
     * @Description: Time 转cron表达式
     * @Author: bo.chen
     * @Date: 2023/9/25 18:29
     * @param localDateTime
     * @param hour
     * @return java.lang.String
     **/
    public static String getCronForHour(LocalDateTime localDateTime, Integer hour) {
        return localDateTime.format(createFormatter("ss mm */" + hour + " dd MM ? yyyy"));
    }

    /**
     * @Description: Time 转cron表达式
     * @Author: bo.chen
     * @Date: 2023/9/25 18:29
     * @param localDateTime
     * @param day
     * @return java.lang.String
     **/
    public static String getCronForDay(LocalDateTime localDateTime, Integer day) {
        return localDateTime.format(createFormatter("ss mm HH */" + day + " MM ? yyyy"));
    }

    /**
     * @Description: Time 转cron表达式
     * @Author: bo.chen
     * @Date: 2023/9/25 18:29
     * @param localDateTime
     * @param week
     * @return java.lang.String
     **/
    public static String getCronForWeek(LocalDateTime localDateTime, Integer week) {
        return localDateTime.format(createFormatter("ss mm HH dd MM */" + week + " yyyy"));
    }

    /**
     * @Description: Time 转cron表达式
     * @Author: bo.chen
     * @Date: 2023/9/25 18:29
     * @param localDateTime
     * @param month
     * @return java.lang.String
     **/
    public static String getCronForMonth(LocalDateTime localDateTime, Integer month) {
        return localDateTime.format(createFormatter("ss mm HH dd */" + month + " ? yyyy"));
    }

    /**
     * @Description: Create 日期转换格式
     * @Author: bo.chen
     * @Date: 2023/9/25 10:05
     * @param pattern
     * @return java.time.format.DateTimeFormatter
     **/
    private static DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault());
    }

    /**
     * 得到 Current 日期
     * 
     * @return
     */
    private static String getCurrentDate2() {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }

    /**
     * @param date1 需要比较的 Time 不能为空(null),需要正确的日期格式
     * @param date2 被比较的 Time 为空(null)则为 Current Time
     * @param stype 返回值Type 0为多少天 ,1为多少个月 ,2为多少年
     * @return
     */
    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;

        String[] u = { "天", "月", "年" };
        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        date2 = date2 == null ? getCurrentDate2() : date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e3) {
            System.out.println("wrong occured");
        }
        // List list = new ArrayList();
        while (!c1.after(c2)) { // 循环对比 ,直到相等 ,n 就是所要的结果
            // list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 Print 出来
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // 比较月份 ,月份+1
            } else {
                c1.add(Calendar.DATE, 1); // 比较天数 ,日期+1
            }
        }

        n = n - 1;

        if (stype == 2) {
            BigDecimal b1 = new BigDecimal(n);
            BigDecimal b2 = new BigDecimal("365");
            BigDecimal b = b1.divide(b2, 0, BigDecimal.ROUND_UP);
            n = b.intValue();
        }

        System.out.println(date1 + " -- " + date2 + " 相差多少" + u[stype] + ":" + n);
        return n;
    }

    public static void main(String[] args) {
        String date = "2009-06-12";

        // DateUtil.compareDate(date, null, 0);
        // DateUtil.compareDate(date, null, 1);
        // DateUtil.compareDate(date, null, 2);
        //
        // date = "2006-06-03";
        // DateUtil.compareDate(date, null, 0);
        // DateUtil.compareDate(date, null, 1);
        // DateUtil.compareDate(date, null, 2);
        // DateUtil.compareDate(date, "2009-06-01", 0);
        // DateUtil.compareDate(date, "2009-06-01", 1);
        DateUtil.compareDate(date, "2009-06-12", 2);
        DateUtil.compareDate(date, "2009-06-12", 2);
    }
}
