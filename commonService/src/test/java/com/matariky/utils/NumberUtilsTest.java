package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;

@SpringBootTest
public class NumberUtilsTest {

    @InjectMocks
    private NumberUtils numberutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDivisible() {
        // Given
        BigDecimal dividend = new BigDecimal("10");
        BigDecimal divisor = new BigDecimal("2");

        // When
        boolean result = NumberUtils.divisible(dividend, divisor);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testDivide() {
        // Given
        BigDecimal dividend = new BigDecimal("10");
        BigDecimal divisor = new BigDecimal("2");
        int scale = 2;

        // When
        BigDecimal result = NumberUtils.divide(dividend, divisor, scale);

        // Then
        assertThat(result).isEqualTo(new BigDecimal("5.00"));
    }

    @Test
    void testAdd() {
        // Given
        BigDecimal num1 = new BigDecimal("10");
        BigDecimal num2 = new BigDecimal("5");

        // When
        BigDecimal result = NumberUtils.add(num1, num2);

        // Then
        assertThat(result).isEqualTo(new BigDecimal("15"));
    }

    @Test
    void testIfNullToZeroBigDecimal() {
        // Given
        BigDecimal num = null;

        // When
        BigDecimal result = NumberUtils.ifNullToZero(num);

        // Then
        assertThat(result).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void testIfNullToZeroInteger() {
        // Given
        Integer num = null;

        // When
        int result = NumberUtils.ifNullToZero(num);

        // Then
        assertThat(result).isEqualTo(NumberUtils.INTEGER_ZERO);
    }

    @Test
    void testIfNullToZeroLong() {
        // Given
        Long num = null;

        // When
        long result = NumberUtils.ifNullToZero(num);

        // Then
        assertThat(result).isEqualTo(NumberUtils.LONG_ZERO);
    }

    @Test
    void testToBigDecimal() {
        // Given
        String value = "10.5";
        BigDecimal defaultValue = BigDecimal.ZERO;

        // When
        BigDecimal result = NumberUtils.toBigDecimal(value, defaultValue);

        // Then
        assertThat(result).isEqualTo(new BigDecimal("10.5"));
    }

    @Test
    void testPercentage() {
        // Given
        BigDecimal dividend = new BigDecimal("50");
        BigDecimal divisor = new BigDecimal("200");

        // When
        BigDecimal result = NumberUtils.percentage(dividend, divisor);

        // Then
        assertThat(result).isEqualTo(new BigDecimal("25.00"));
    }

    @Test
    void testRate() {
        // Given
        BigDecimal percentage = new BigDecimal("25");

        // When
        BigDecimal result = NumberUtils.rate(percentage);

        // Then
        assertThat(result).isEqualTo(new BigDecimal("0.2500"));
    }
}
