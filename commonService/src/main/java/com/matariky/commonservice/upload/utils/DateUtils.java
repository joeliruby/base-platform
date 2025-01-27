package com.matariky.commonservice.upload.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date handling
 * 
 */
public class DateUtils {
    /** Time format (yyyy-MM-dd) */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /** Time format (yyyy-MM-dd HH:mm:ss) */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date formatting, date format: yyyy-MM-dd
     * 
     * @param date Date
     * @return Returns date in yyyy-MM-dd format
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * Date formatting, date format: yyyy-MM-dd
     * 
     * @param date    Date
     * @param pattern Format, e.g.: DateUtils.DATE_TIME_PATTERN
     * @return Returns date in yyyy-MM-dd format
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * Date parsing
     * 
     * @param date    Date
     * @param pattern Format, e.g.: DateUtils.DATE_TIME_PATTERN
     * @return Returns Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert string to date
     * 
     * @param strDate Date string
     * @param pattern Date format, e.g.: DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    /**
     * Retrieve start date and end date based on the week number
     * 
     * @param week Week number: 0 this week, -1 last week, -2 the week before last,
     *             1 next week, 2 the week after next
     * @return Returns date[0] start date, date[1] end date
     */
    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate(dateTime.plusWeeks(week));

        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[] { beginDate, endDate };
    }

    /**
     * Add/subtract seconds to/from a date
     *
     * @param date    Date
     * @param seconds Number of seconds, negative for subtraction
     * @return Date after adding/subtracting seconds
     */
    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    /**
     * Add/subtract minutes to/from a date
     *
     * @param date    Date
     * @param minutes Number of minutes, negative for subtraction
     * @return Date after adding/subtracting minutes
     */
    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    /**
     * Add/subtract hours to/from a date
     *
     * @param date  Date
     * @param hours Number of hours, negative for subtraction
     * @return Date after adding/subtracting hours
     */
    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusHours(hours).toDate();
    }

    /**
     * Add/subtract days to/from a date
     *
     * @param date Date
     * @param days Number of days, negative for subtraction
     * @return Date after adding/subtracting days
     */
    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(days).toDate();
    }

    /**
     * Add/subtract weeks to/from a date
     *
     * @param date  Date
     * @param weeks Number of weeks, negative for subtraction
     * @return Date after adding/subtracting weeks
     */
    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    /**
     * Add/subtract months to/from a date
     *
     * @param date   Date
     * @param months Number of months, negative for subtraction
     * @return Date after adding/subtracting months
     */
    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMonths(months).toDate();
    }

    /**
     * Add/subtract years to/from a date
     *
     * @param date  Date
     * @param years Number of years, negative for subtraction
     * @return Date after adding/subtracting years
     */
    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusYears(years).toDate();
    }
}