package com.matariky.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Bean utility class
 * 
 * 
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /** Index of the property name start in Bean method name */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /** * Pattern to match getter methods */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /** * Pattern to match setter methods */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Retrieve setter methods of the object.
     * 
     * @param obj Object
     * @return List of setter methods of the object
     */
    public static List<Method> getSetterMethods(Object obj) {
        // List of setter methods
        List<Method> setterMethods = new ArrayList<Method>();

        // Retrieve all methods
        Method[] methods = obj.getClass().getMethods();

        // Find setter methods
        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // Return list of setter methods
        return setterMethods;
    }

    /**
     * Retrieve getter methods of the object.
     * 
     * @param obj Object
     * @return List of getter methods of the object
     */

    public static List<Method> getGetterMethods(Object obj) {
        // List of getter methods
        List<Method> getterMethods = new ArrayList<Method>();
        // Retrieve all methods
        Method[] methods = obj.getClass().getMethods();
        // Find getter methods
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // Return list of getter methods
        return getterMethods;
    }

    /**
     * Check if the property names in Bean method names are equal.<br>
     * For example, getName() and setName() have the same property name, but
     * getName() and setAge() do not.
     * 
     * @param m1 Method name 1
     * @param m2 Method name 2
     * @return true if the property names are the same, otherwise false
     */
    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> copyProperties(Collection<?> sources, Class<T> targetClass) {
        return (List<T>) sources.stream().map(source -> copyProperties(source, targetClass))
                .collect(Collectors.toList());
    }

    public static Map<String, String> beanToMap(Object bean) {
        try {
            return org.apache.commons.beanutils.BeanUtils.describe(bean);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;

    }
}
