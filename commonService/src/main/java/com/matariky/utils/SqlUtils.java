package com.matariky.utils;

//import com.sun.deploy.util.ArrayUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.matariky.mybatis.EnhanceBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.*;

public class SqlUtils {

    /**
     * Retrieve SQL statements in AOP
     * 
     * @param pjp
     * @param sqlSessionFactory
     * @return
     * @throws IllegalAccessException
     */
    public static String getMybatisSql(ProceedingJoinPoint pjp, SqlSessionFactory sqlSessionFactory)
            throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        // 1. Retrieve namespace + methodName
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        String namespace = method.getDeclaringClass().getName();
        if (namespace.equals(EnhanceBaseMapper.class.getName()) || namespace.equals(BaseMapper.class.getName())) {
            return namespace + "." + method;
        }
        String methodName = method.getName();
        // 2. Retrieve the corresponding MappedStatement based on namespace + methodName
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace + "." + methodName);
        // //3. Retrieve method parameter names
        // Parameter[] parameters = method.getParameters();
        // 4. Map formal parameters to actual parameters
        Object[] objects = pjp.getArgs(); // Retrieve actual parameters
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Object object = objects[i];
            if (parameterAnnotations[i].length == 0) { // Indicates that the parameter has no annotations, it may be an
                                                       // entity class, a Map, or just a single parameter
                if (object != null && object.getClass().getClassLoader() == null && object instanceof Map) {
                    map.putAll((Map<? extends String, ?>) object);
                    System.out.println("The object is a Map");
                } else { // The parameter is a custom entity class
                    if (object != null)
                        map.putAll(objectToMap(object));
                    System.out.println("The object is a user-defined object");
                }
            } else { // Indicates that the parameter has annotations and must be @Param
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof Param) {
                        map.put(((Param) annotation).value(), object);
                    }
                }
            }
        }
        // Filter out delete
        int delete = mappedStatement.getId().indexOf("delete");
        if (delete >= 0) {
            return "delete";
        }

        // Filter out create
        int create = mappedStatement.getId().indexOf("create");
        if (create >= 0) {
            return "create";
        }

        // 5. Retrieve boundSql
        BoundSql boundSql = mappedStatement.getBoundSql(map);
        return showSql(configuration, boundSql);
    }

    /**
     * Parse BoundSql and generate SQL statements without placeholders
     * 
     * @param configuration
     * @param boundSql
     * @return
     */
    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    /**
     * If it is a string or date type, add '' around the parameter
     * 
     * @param obj
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    /**
     * Retrieve values and names in the class using reflection
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map = BeanUtils.beanToMap(obj);
        return map;
    }
}
