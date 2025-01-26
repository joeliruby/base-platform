package com.matariky.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 字符串工具类
 * @author: bo.chen
 * @create: 2023/9/13 14:36
 **/
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    /**
     * @Description: 获取全称
     * @Author: bo.chen
     * @Date: 2023/9/13 14:38
     * @param name
     * @param code
     * @return java.lang.String
     **/
    public static String getFullName(String name, String code) {
        return name + " (" + code + ")";
    }

    /**
     * @Description: 连接合并
     * @Author: bo.chen
     * @Date: 2023/9/22 15:51
     * @param objs
     * @return java.lang.String
     **/
    public static String joining(Object ...objs) {
        return Arrays.stream(objs).filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(","));
    }

    /**
     * @Description: 首字母转小写
     * @Author: bo.chen
     * @Date: 2023/11/13 9:59
     * @param str
     * @return java.lang.String
     **/
    public static String lowerFirst(String str) {
        if (StringUtils.isNotEmpty(str)) {
            char[] chars = str.toCharArray();
            chars[NumberUtils.INTEGER_ZERO] += 32;
            return String.valueOf(chars);
        }
        return str;
    }


    private StringUtils() {

    }

    /**
     * * 判断一个对象 Wether 非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * * 判断一个对象 Wether 为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object)
    {
        return object == null;
    }
}
