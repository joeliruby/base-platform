package com.matariky.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @description: 数值工具类
 * @author: bo.chen
 * @create: 2023/4/9 15:14
 **/
public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils {
    public static final Integer INTEGER_TREE = 3;

    /**
     * @Description:  Wether 能整除
     * @Author: bo.chen
     * @Date: 2023/8/5 21:10
     * @param dividend
     * @param divisor
     * @return boolean
     **/
    public static boolean divisible(BigDecimal dividend, BigDecimal divisor) {
        try {
            BigDecimal result = dividend.divide(divisor);
            BigDecimal number = result.setScale(0, BigDecimal.ROUND_DOWN);
            return result.compareTo(number) == INTEGER_ZERO;
        } catch (ArithmeticException exception) {
            return Boolean.FALSE;
        }
    }


    /**
     * @Description: 除法
     * @Author: bo.chen
     * @Date: 2023/8/4 14:26
     * @param dividend
     * @param divisor
     * @param scale
     * @return java.math.BigDecimal
     **/
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor, int scale)  {
        dividend = NumberUtils.ifNullToZero(dividend);
        divisor = NumberUtils.ifNullToZero(divisor);
        if (dividend.compareTo(BigDecimal.ZERO) == INTEGER_ZERO || divisor.compareTo(BigDecimal.ZERO) == INTEGER_ZERO) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * @Description: 添加
     * @Author: bo.chen
     * @Date: 2023/7/26 9:18
     * @param num1
     * @param num2
     * @return java.math.BigDecimal
     **/
    public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return NumberUtils.ifNullToZero(num1).add(NumberUtils.ifNullToZero(num2));
    }


    /**
     * @Description: null转成0
     * @Author: bo.chen
     * @Date: 2023/4/9 15:16
     * @param num
     * @return java.math.BigDecimal
     **/
    public static BigDecimal ifNullToZero(BigDecimal num) {
        return Objects.isNull(num) ? BigDecimal.ZERO : num;
    }

    /**
     * @Description: null转成0
     * @Author: bo.chen
     * @Date: 2023/7/21 10:36
     * @param num
     * @return int
     **/
    public static int ifNullToZero(Integer num) {
        return Objects.isNull(num) ? NumberUtils.INTEGER_ZERO : num;
    }

    /**
     * @Description: null转成0
     * @Author: bo.chen
     * @Date: 2023/7/21 10:36
     * @param num
     * @return int
     **/
    public static long ifNullToZero(Long num) {
        return Objects.isNull(num) ? NumberUtils.LONG_ZERO : num;
    }

    /**
     * @Description: 转换 BigDecimal
     * @Author: bo.chen
     * @Date: 2023/5/23 9:28
     * @param value
     * @param defaultValue
     * @return java.math.BigDecimal
     **/
    public static BigDecimal toBigDecimal(String value, BigDecimal defaultValue) {
        return StringUtils.isNotEmpty(value)  ? createBigDecimal(value) : defaultValue;
    }

    /**
     * @Description: 求百分比 （超过100%则返回100%）
     * @Author: bo.chen
     * @Date: 2023/4/9 15:25
     * @param dividend
     * @param divisor
     * @return java.math.BigDecimal
     **/
    public static BigDecimal percentage(BigDecimal dividend, BigDecimal divisor) {
        dividend = ifNullToZero(dividend);
        divisor = ifNullToZero(divisor);
        if (dividend.compareTo(BigDecimal.ZERO) == NumberUtils.INTEGER_ONE) {
            BigDecimal quotient = dividend.divide(divisor, 5, BigDecimal.ROUND_HALF_UP);
            if (quotient.compareTo(BigDecimal.ONE) < NumberUtils.INTEGER_ZERO) {
                return quotient.multiply(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return BigDecimal.valueOf(100);
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * @Description: 百分比转比率
     * @Author: bo.chen
     * @Date: 2023/5/7 17:53
     * @param percentage
     * @return java.math.BigDecimal
     **/
    public static BigDecimal rate(BigDecimal percentage) {
        percentage  = ifNullToZero(percentage);
        BigDecimal hundred = BigDecimal.valueOf(100);
        return percentage.divide(hundred, 4, BigDecimal.ROUND_HALF_UP);
    }

    private NumberUtils() {

    }
}
