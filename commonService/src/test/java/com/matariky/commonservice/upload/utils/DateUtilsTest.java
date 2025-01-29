package com.matariky.commonservice.upload.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class DateUtilsTest {

    @InjectMocks
    private DateUtils dateutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFormat() throws ParseException {
        // Given
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        Date date = sdf.parse("2023-10-10");

        // When
        String formattedDate = DateUtils.format(date);

        // Then
        assertThat(formattedDate).isEqualTo("2023-10-10");
    }

    @Test
    void testParse() {
        // Given
        String dateStr = "2023-10-10";

        // When
        Date date = DateUtils.parse(dateStr, DateUtils.DATE_PATTERN);

        // Then
        assertThat(date).isNotNull();
        assertThat(new SimpleDateFormat(DateUtils.DATE_PATTERN).format(date)).isEqualTo(dateStr);
    }

    @Test
    void testStringToDate() {
        // Given
        String dateStr = "2023-10-10 10:10:10";

        // When
        Date date = DateUtils.stringToDate(dateStr, DateUtils.DATE_TIME_PATTERN);

        // Then
        assertThat(date).isNotNull();
        assertThat(new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN).format(date)).isEqualTo(dateStr);
    }

    @Test
    void testGetWeekStartAndEnd() {
        // Given
        int week = 0;

        // When
        Date[] weekStartAndEnd = DateUtils.getWeekStartAndEnd(week);

        // Then
        assertThat(weekStartAndEnd).hasSize(2);
        assertThat(weekStartAndEnd[0]).isBeforeOrEqualTo(weekStartAndEnd[1]);
    }

    @Test
    void testAddDateSeconds() {
        // Given
        Date date = new Date();
        int seconds = 60;

        // When
        Date newDate = DateUtils.addDateSeconds(date, seconds);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateMinutes() {
        // Given
        Date date = new Date();
        int minutes = 10;

        // When
        Date newDate = DateUtils.addDateMinutes(date, minutes);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateHours() {
        // Given
        Date date = new Date();
        int hours = 1;

        // When
        Date newDate = DateUtils.addDateHours(date, hours);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateDays() {
        // Given
        Date date = new Date();
        int days = 1;

        // When
        Date newDate = DateUtils.addDateDays(date, days);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateWeeks() {
        // Given
        Date date = new Date();
        int weeks = 1;

        // When
        Date newDate = DateUtils.addDateWeeks(date, weeks);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateMonths() {
        // Given
        Date date = new Date();
        int months = 1;

        // When
        Date newDate = DateUtils.addDateMonths(date, months);

        // Then
        assertThat(newDate).isAfter(date);
    }

    @Test
    void testAddDateYears() {
        // Given
        Date date = new Date();
        int years = 1;

        // When
        Date newDate = DateUtils.addDateYears(date, years);

        // Then
        assertThat(newDate).isAfter(date);
    }
}
