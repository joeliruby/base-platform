package com.matariky.utils;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String getFullName(String name, String code) {
        return name + " (" + code + ")";
    }

    public static String joining(Object... objs) {
        return Arrays.stream(objs).filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(","));
    }

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
     * * Determine whether an object is not null
     *
     * @param object Object
     * @return true: not null, false: null
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * Determine whether an object is null
     *
     * @param object Object
     * @return true: null, false: not null
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
}
