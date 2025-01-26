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
public class BeanUtils extends org.springframework.beans.BeanUtils
{
    /** Bean方法名中属性名开始的下标 */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * 匹配getter方法的正则表达式 */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * 匹配setter方法的正则表达式 */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");


    /**
     * 获取对象的setter方法。
     * 
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj)
    {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods)
        {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1))
            {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     * 
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj)
    {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods)
        {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0))
            {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名 Wether 相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     * 
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2)
    {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * @Description: 拷贝属性
     * @Author: bo.chen
     * @Date: 2022/7/15 10:27
     * @param source 拷贝属性对象
     * @param targetClass 目标类class
     * @return T
     **/
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            T  target = targetClass.newInstance();
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
     * @param sources 拷贝属性对象集合
     * @param targetClass 目标类class
     * @return java.util.Collection<T>
     **/
    public static <T> List<T> copyProperties(Collection sources, Class<T> targetClass) {
        return (List<T>) sources.stream().map(source -> copyProperties(source, targetClass)).collect(Collectors.toList());
    }

    /**
     * @Description:  bean 转map
     * @Author: bo.chen
     * @Date: 2023/2/16 21:14
     * @param bean
     * @return java.util.Map<java.lang.String, java.lang.Object>
     **/
    public static Map<String, String> beanToMap(Object bean)  {
//        try {
//            Map<String, Object> map = new HashMap<>();
//            Field[] fields = bean.getClass().getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true);
//                map.put(field.getName(), field.get(bean));
//            }
//            return map;
//        } catch (Exception e) {
//            throw new RuntimeException("bean to map error!", e);
//        }
    	try {
			return org.apache.commons.beanutils.BeanUtils.describe(bean);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
    	return null;

    }
}
