package com.matariky.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bean 工具类
 * 
 * 
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /** Bean Method Name 中属性 Name Start 的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter Method 的正则表达式 */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * 匹配setter Method 的正则表达式 */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Retrieve Object 的setter Method 。
     * 
     * @param obj Object
     * @return Object 的setter Method Pagination
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter Method Pagination
        List<Method> setterMethods = new ArrayList<Method>();

        // Retrieve所有 Method
        Method[] methods = obj.getClass().getMethods();

        // 查找setter Method

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter Method Pagination
        return setterMethods;
    }

    /**
     * Retrieve Object 的getter Method 。
     * 
     * @param obj Object
     * @return Object 的getter Method Pagination
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter Method Pagination
        List<Method> getterMethods = new ArrayList<Method>();
        // Retrieve所有 Method
        Method[] methods = obj.getClass().getMethods();
        // 查找getter Method
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter Method Pagination
        return getterMethods;
    }

    /**
     * 检查Bean Method Name 中的属性 Name Wether 相等。<br>
     * 如getName()和setName()属性 Name 一样 ,getName()和setAge()属性 Name 不一样。
     * 
     * @param m1 Method Name 1
     * @param m2 Method Name 2
     * @return 属性 Name 一样返回true ,否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * @Description: 拷贝属性
     * @Author: bo.chen
     * @Date: 2022/7/15 10:27
     * @param source      拷贝属性 Object
     * @param targetClass 目标类class
     * @return T
     **/
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 拷贝属性
     * @Author: bo.chen
     * @Date: 2022/7/15 10:35
     * @param sources     拷贝属性 Object 集合
     * @param targetClass 目标类class
     * @return java.util.Collection<T>
     **/
    public static <T> List<T> copyProperties(Collection sources, Class<T> targetClass) {
        return (List<T>) sources.stream().map(source -> copyProperties(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * @Description: bean 转map
     * @Author: bo.chen
     * @Date: 2023/2/16 21:14
     * @param bean
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    public static Map<String, String> beanToMap(Object bean) {
        // try {
        // Map<String, Object> map = new HashMap<>();
        // Field[] fields = bean.getClass().getDeclaredFields();
        // for (Field field : fields) {
        // field.setAccessible(true);
        // map.put(field.getName(), field.get(bean));
        // }
        // return map;
        // } catch (Exception e) {
        // throw new RuntimeException("bean to map error!", e);
        // }
        try {
            return org.apache.commons.beanutils.BeanUtils.describe(bean);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }
}
