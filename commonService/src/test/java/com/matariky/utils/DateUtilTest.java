package com.matariky.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DateUtilTest {

    @InjectMocks
    private DateUtil dateutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLastDayOfMonth() {
        // Given
        int year = 2019;
        int month = 10;

        // When
        String result = DateUtil.getLastDayOfMonth(year, month);

        // Then
        assertThat(result).isEqualTo("2019-10-31");
    }

    @Test
    void testFormatStringDate() {
        // Given
        String date = "20211015";

        // When
        String result = DateUtil.formatStringDate(date);

        // Then
        assertThat(result).isEqualTo("2021-20211015");
    }

    @Test
    void testGetCurrentDateStr() {
        // When
        String result = DateUtil.getCurrentDateStr();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCurrentDateFormatStr() {
        // Given
        String format = "yyyyMMdd";

        // When
        String result = DateUtil.getCurrentDateFormatStr(format);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetStringDate() {
        // When
        String result = DateUtil.getStringDate();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCurrentDate() {
        // When
        Date result = DateUtil.getCurrentDate();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCurrentDateAndTime() {
        // When
        Date result = DateUtil.getCurrentDateAndTime();

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testDate2String() {
        // Given
        Date date = new Date();

        // When
        String result = DateUtil.date2String(date);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testString2date() {
        // Given
        String dateStr = "2021-10-15";

        // When
        Date result = DateUtil.string2date(dateStr);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testString2Datetime() throws ParseException {
        // Given
        String dateStr = "2021-10-15 12:00:00";

        // When
        Date result = DateUtil.string2Datetime(dateStr);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testAddDay() {
        // Given
        Date date = new Date();
        int value = 5;

        // When
        Date result = DateUtil.addDay(date, value);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testAddHours() {
        // Given
        Date date = new Date();
        int value = 5;

        // When
        Date result = DateUtil.addHours(date, value);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testAddMinutes() {
        // Given
        Date date = new Date();
        int value = 5;

        // When
        Date result = DateUtil.addMinutes(date, value);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testAddSecond() {
        // Given
        Date date = new Date();
        int value = 5;

        // When
        Date result = DateUtil.addSecond(date, value);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testAddMonth() {
        // Given
        Date date = new Date();
        int value = 1;

        // When
        Date result = DateUtil.addMonth(date, value);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetInterval() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 1000);

        // When
        long result = DateUtil.getInterval(startDate, endDate);

        // Then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void testGetIntervalSeconds() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 1000);

        // When
        int result = DateUtil.getIntervalSeconds(startDate, endDate);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void testCaculate2Days() {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + DateUtil.C_ONE_DAY * 2);

        // When
        int result = DateUtil.caculate2Days(startDate, endDate);

        // Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testDaysBetween() throws ParseException {
        // Given
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + DateUtil.C_ONE_DAY * 2);

        // When
        int result = DateUtil.daysBetween(startDate, endDate);

        // Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void testIsBetweenDate() {
        // Given
        Date destDate = new Date();
        Date startDate = new Date(destDate.getTime() - 1000);
        Date endDate = new Date(destDate.getTime() + 1000);

        // When
        boolean result = DateUtil.isBetweenDate(destDate, startDate, endDate);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testIsInvalid() {
        // Given
        Date date = new Date(new Date().getTime() - 1000);

        // When
        boolean result = DateUtil.isInvalid(date);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testGetStartTimeOfDay() {
        // Given
        Date date = new Date();

        // When
        Date result = DateUtil.getStartTimeOfDay(date);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetEndTimeOfDay() {
        // Given
        Date date = new Date();

        // When
        Date result = DateUtil.getEndTimeOfDay(date);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testValidateDate() {
        // Given
        String dateStr = "2021-10-15";
        String pattern = "yyyy-MM-dd";

        // When
        boolean result = DateUtil.validateDate(dateStr, pattern);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testGetFirstDayOfMonth() throws ParseException {
        // Given
        Date date = new Date();

        // When
        Date result = DateUtil.getFirstDayOfMonth(date);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testToLocalDateTime() {
        // Given
        long timestamp = System.currentTimeMillis();

        // When
        LocalDateTime result = DateUtil.toLocalDateTime(timestamp);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCron() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now();

        // When
        String result = DateUtil.getCron(localDateTime);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCronForHour() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now();
        int hour = 1;

        // When
        String result = DateUtil.getCronForHour(localDateTime, hour);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCronForDay() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now();
        int day = 1;

        // When
        String result = DateUtil.getCronForDay(localDateTime, day);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCronForWeek() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now();
        int week = 1;

        // When
        String result = DateUtil.getCronForWeek(localDateTime, week);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void testGetCronForMonth() {
        // Given
        LocalDateTime localDateTime = LocalDateTime.now();
        int month = 1;

        // When
        String result = DateUtil.getCronForMonth(localDateTime, month);

        // Then
        assertThat(result).isNotNull();
    }
}
