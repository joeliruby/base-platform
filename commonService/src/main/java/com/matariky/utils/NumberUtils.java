package com.matariky.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
    public static final Integer INTEGER_TREE = 3;

    public static boolean divisible(BigDecimal dividend, BigDecimal divisor) {
        try {
            BigDecimal result = dividend.divide(divisor);
            BigDecimal number = result.setScale(0, RoundingMode.DOWN);
            return result.compareTo(number) == INTEGER_ZERO;
        } catch (ArithmeticException exception) {
            return Boolean.FALSE;
        }
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale) {
        dividend = NumberUtils.ifNullToZero(dividend);
        divisor = NumberUtils.ifNullToZero(divisor);
        if (dividend.compareTo(BigDecimal.ZERO) == INTEGER_ZERO || divisor.compareTo(BigDecimal.ZERO) == INTEGER_ZERO) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return NumberUtils.ifNullToZero(num1).add(NumberUtils.ifNullToZero(num2));
    }

    public static BigDecimal ifNullToZero(BigDecimal num) {
        return Objects.isNull(num) ? BigDecimal.ZERO : num;
    }

    public static int ifNullToZero(Integer num) {
        return Objects.isNull(num) ? NumberUtils.INTEGER_ZERO : num;
    }

    public static long ifNullToZero(Long num) {
        return Objects.isNull(num) ? NumberUtils.LONG_ZERO : num;
    }

    public static BigDecimal toBigDecimal(String value, BigDecimal defaultValue) {
        return StringUtils.isNotEmpty(value) ? createBigDecimal(value) : defaultValue;
    }

    public static BigDecimal percentage(BigDecimal dividend, BigDecimal divisor) {
        dividend = ifNullToZero(dividend);
        divisor = ifNullToZero(divisor);
        if (dividend.compareTo(BigDecimal.ZERO) == NumberUtils.INTEGER_ONE) {
            BigDecimal quotient = dividend.divide(divisor, 5, RoundingMode.HALF_UP);
            if (quotient.compareTo(BigDecimal.ONE) < NumberUtils.INTEGER_ZERO) {
                return quotient.multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
            } else {
                return BigDecimal.valueOf(100);
            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal rate(BigDecimal percentage) {
        percentage = ifNullToZero(percentage);
        BigDecimal hundred = BigDecimal.valueOf(100);
        return percentage.divide(hundred, 4, RoundingMode.HALF_UP);
    }

    private NumberUtils() {

    }
}
