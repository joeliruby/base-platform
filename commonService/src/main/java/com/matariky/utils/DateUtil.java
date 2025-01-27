package com.matariky.utils;

import com.github.pagehelper.util.StringUtil;
import com.matariky.commonservice.upload.utils.RenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    /** 24-hour format time of Oracle type */
    public static final String ORACLE_DATE_TIME_24_PATTERN = "yyyy-mm-dd hh24:mi:ss";
    public static final String DATE_TIME_24_PATTERN_SIMPLE = "yyyyMMddHHmmss";
    /** Complete date and time format */
    public static final String DATE_TIME_FULL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FULL_SIMPLE = "yyyy-MM-dd HH:mm";
    /** Date format Hour:Minute */
    public static final String DATE_HM_TIME_PATTERN = "HH:mm";
    public static final String DATE_TIME_YEAR_MONTH_DAY = "yyyy年MM月dd日";
    public static final String DATE_TIME_YEAR_MONTH_DAY_HM = "yyyy年MM月dd日 HH:mm";
    /** Date format to day */
    public static final String DATE_TIME_DAY = "yyyy-MM-dd";
    /** Get only the time, without year, month, and day */
    public static final String DATE_TIME_TIME = "HH:mm:ss";
    /** Date format without hyphens */
    public static final String DATE_TIME_DAY_SIMPLE = "yyyyMMdd";
    /** Date format without hyphens for year and month */
    public static final String DATE_TIME_DAY_SIMPLE_NO_DAY = "yyyyMM";
    /** Time format */
    public static final String DATE_TIME_TERM = "HH.mm.ss";
    public static final String DATE_TIME_CHINESE = "MM月dd日HH时mm分";

    public static final int C_ONE_SECOND = 1000;
    public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
    public static final long C_ONE_HOUR = (long) 60 * C_ONE_MINUTE;
    public static final long C_ONE_DAY = (long) 24 * C_ONE_HOUR;

    public static final String DAY_ENDTIME = "235959";

    /**
     * Retrieve the last day of the specified year and month
     *
     * @param year
     * @param month
     * @return lastDayOfMonth = 2019-10-31
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        // Set year
        cal.set(Calendar.YEAR, year);
        // Set month
        cal.set(Calendar.MONTH, month - 1);
        // Retrieve the maximum number of days in the month
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        // Set the maximum number of days in the month in the calendar
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }

    /**
     * Format the input string date as yyyy-MM-dd
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String formatStringDate(String date) {
        if (date == null)
            return null;
        return date.substring(0, 4) + "-" + date;
    }

    /**
     * Convert Date type time to String type format: yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Return a string with the specified date format, e.g.: yyyyMMdd,
     * yyyyMMddhhmmss
     *
     * @param format
     * @return yyyyMMdd, yyyyMMddhhmmss
     */
    public static String getCurrentDateFormatStr(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }

    /**
     * Convert Date type time to String type format: yyyy-MM-dd
     *
     * @return yyyy-MM-dd
     */
    public static String getStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * Retrieve Current Date
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
     * Get Current Date and Time
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
     * Retrieve formatted Data, return yyyyMMdd
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
     * Convert Date to String
     */
    public static final String date2String(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    /**
     * Convert Date to String
     */
    public static final String date2String(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /***
     * Convert Date to String with hyphens
     */
    public static final String dateToSting_(Date date) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * Convert String to Date
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
     * Convert String to Date (yyyy-MM-dd HH:mm:ss)
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
     * Convert String to Date
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
            logger.error("string2timestamp date conversion issue", e.getMessage());
            throw new RenException("string2timestamp date conversion issue: " + e);
        }
    }

    /**
     * Convert String to Date (yyyymmdd)
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
     * Convert String date to Date (yyyyMMddHHmmss)
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
     * Add days to the specified date
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
     * Add hours to the specified date
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
     * Add minutes to the specified date
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
     * Add seconds to the specified date
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
     * Add months to the specified date
     *
     * @param date
     * @param value
     * @return
     */
    public static final Date addMonth(Date date, int value) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MONTH, value);
        return now.getTime();
    }

    /**
     * Calculate the time interval between two dates (milliseconds)
     *
     * @param startDate Start time
     * @param endDate   End time
     * @return Returns the number of milliseconds between two times
     */
    public static long getInterval(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long interval = endTime - startTime;
        return interval;
    }

    /**
     * Calculate the time interval between two dates (seconds)
     *
     * @param startDate Start time
     * @param endDate   End time
     * @return Returns the number of seconds between two times
     */
    public static int getIntervalSeconds(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        int interval = (int) ((endTime - startTime) / 1000);
        return interval;
    }

    /**
     * Calculate the time interval between two dates (days)
     *
     * @param startDate Start time
     * @param endDate   End time
     * @return Returns the number of days between two dates
     */
    public static int caculate2Days(Date startDate, Date endDate) {
        long calendarNum1 = startDate.getTime();
        long calendarNum2 = endDate.getTime();
        return Math.abs((int) ((calendarNum1 - calendarNum2) / C_ONE_DAY));
    }

    /**
     * Calculate the number of days between two dates
     *
     * @param startDate
     * @param endDate
     * @return Number of days
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
     * Convert String type to Date type Function Description: <br>
     * <Function Detail Description>
     *
     * @param dateStr
     * @return
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
     */
    public static Date parseStr2Date(String dateStr, String pattern) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateStr);
            return date;
        } catch (ParseException e) {
            logger.error("Date conversion error: " + e.getMessage());
            throw new RenException("Date conversion error: " + e);
        }
    }

    /**
     * Convert String type to Date type Function Description: <br>
     * <Function Detail Description>
     *
     * @param dateStr
     * @return
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
     */
    public static Date parseStr2DateIgnoreError(String dateStr, String pattern) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            logger.error("Date conversion error: ", e);
        }
        return date;
    }

    /**
     * Convert date format to String format
     *
     * @param date           Date to be converted
     * @param pattern        Time format for conversion
     * @param allowException If true, throws an exception when date is null. If
     *                       false, returns an empty string
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
     * Convert Date to specified format string
     *
     * @param date    Date to be converted
     * @param pattern Time format for conversion
     * @return
     */
    public static String parseDate2String(Date date, String pattern) {
        return parseDate2String(date, pattern, false);
    }

    /**
     * Whether the target time is within the start and end time period
     * If both start and end times are null, it is not considered
     * If the start time is null, the start time is not considered
     * If the end time is null, the end time is not considered
     * date.before after does not include the same date
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
            return (destDate.compareTo(startDate) >= 0 && (endDate == null || endDate.compareTo(destDate) >= 0));
        }

    }

    /**
     * Last day of the previous month
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
     * Last day of the previous year
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
     * Last day of the current quarter year Y month M day D
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
            lastQuarterMonth = 3; // March
        } else if (month < 6) {
            lastQuarterMonth = 6; // June
        } else if (month < 9) {
            lastQuarterMonth = 9; // September
        } else {
            lastQuarterMonth = 12; // December
        }

        map.put("year", year);
        map.put("month", lastQuarterMonth);
        map.put("day", Day);

        return map;
    }

    /**
     * Calculate if the time is invalid (input time is less than the current time)
     * (milliseconds)
     *
     * @param startDate Start time
     * @param endDate   End time
     * @return Returns the number of milliseconds between the two times
     */
    /**
     * Function description: Calculate if the time is invalid (input time is less
     * than the current time)
     * <Function detail description>
     *
     * @param date
     * @return true: invalid; false: not invalid
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
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
     * Function description: Retrieve the start time of the day
     * <Function detail description>
     *
     * @param date
     * @return
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
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
     * Function description: Retrieve the end time of the day
     * <Function detail description>
     *
     * @param date
     * @return
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
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
     * Convert String type to Date type
     * Function description:
     * <Function detail description>
     *
     * @param dateStr
     * @return
     *
     * @see [Related Class/Method] (optional)
     * @since [Product/Module Version] (optional)
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
            logger.error("Date conversion error: " + e.getMessage());
            isDate = false;
        }
        return isDate;
    }

    // Retrieve the first day of the specified month at 0 o'clock
    public static Date getFirstDayOfMonth(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1); // Set to the 1st, the current date is the first day of the month
        String first = format.format(cal.getTime());
        return format.parse(first);
    }

    public static LocalDateTime toLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static String getCron(LocalDateTime localDateTime) {
        return localDateTime.format(createFormatter("ss mm HH dd MM ? yyyy"));
    }

    public static String getCronForHour(LocalDateTime localDateTime, Integer hour) {
        return localDateTime.format(createFormatter("ss mm */" + hour + " dd MM ? yyyy"));
    }

    public static String getCronForDay(LocalDateTime localDateTime, Integer day) {
        return localDateTime.format(createFormatter("ss mm HH */" + day + " MM ? yyyy"));
    }

    public static String getCronForWeek(LocalDateTime localDateTime, Integer week) {
        return localDateTime.format(createFormatter("ss mm HH dd MM */" + week + " yyyy"));
    }

    public static String getCronForMonth(LocalDateTime localDateTime, Integer month) {
        return localDateTime.format(createFormatter("ss mm HH dd */" + month + " ? yyyy"));
    }

    private static DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault()).withZone(ZoneId.systemDefault());
    }

    /**
     * Get the current date
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
     * @param date1 Time to be compared, cannot be null and must be in the correct
     *              date format
     * @param date2 Time to compare with, if null then it is the current time
     * @param stype Return value type: 0 for days, 1 for months, 2 for years
     * @return
     */
    public static int compareDate(String date1, String date2, int stype) {
        int n = 0;

        String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

        date2 = date2 == null ? getCurrentDate2() : date2;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (Exception e) {
            System.out.println("Error occurred");
        }

        while (!c1.after(c2)) { // Loop and compare until equal, n is the result
            n++;
            if (stype == 1) {
                c1.add(Calendar.MONTH, 1); // Compare months, add 1 month
            } else {
                c1.add(Calendar.DATE, 1); // Compare days, add 1 day
            }
        }

        n = n - 1;

        if (stype == 2) {
            BigDecimal b1 = new BigDecimal(n);
            BigDecimal b2 = new BigDecimal("365");
            BigDecimal b = b1.divide(b2, 0, RoundingMode.UP);
            n = b.intValue();
        }

        return n;
    }

    /**
     * get current Time yyyyMMddHHmmss
     */
    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }
}