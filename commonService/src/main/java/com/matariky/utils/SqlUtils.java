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
     * 获取aop中的SQL语句
     * @param pjp
     * @param sqlSessionFactory
     * @return
     * @throws IllegalAccessException
     */
    public static String getMybatisSql(ProceedingJoinPoint pjp, SqlSessionFactory sqlSessionFactory) throws IllegalAccessException {
        Map<String,Object> map = new HashMap<>();
        //1.获取namespace+methdoName
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        
        String namespace = method.getDeclaringClass().getName();
        if (namespace.equals(EnhanceBaseMapper.class.getName()) || namespace.equals(BaseMapper.class.getName())) {
            return namespace + "." + method;
        }
        String methodName = method.getName();
        //2.根据namespace+methdoName获取相对应的MappedStatement
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace+"."+methodName);
//        //3.获取方法 Parameter 列表名
//        Parameter[] parameters = method.getParameters();
        //4.形参和实参的映射
        Object[] objects = pjp.getArgs(); //获取实参
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0;i<parameterAnnotations.length;i++){
            Object object = objects[i];
            if (parameterAnnotations[i].length == 0){ //说明该 Parameter 没有注解，此时该 Parameter 可能是实体类，也可能是Map，也可能只是单 Parameter 
                if (object !=null &&object.getClass().getClassLoader() == null && object instanceof Map){
                    map.putAll((Map<? extends String, ?>) object);
                    System.out.println("该对象为Map");
                }else{//形参为自定义实体类
                	if(object !=null)
                    map.putAll(objectToMap(object));
                    System.out.println("该对象为用户自定义的对象");
                }
            }else{//说明该 Parameter 有注解，且必须为@Param
                for (Annotation annotation : parameterAnnotations[i]){
                    if (annotation instanceof Param){
                        map.put(((Param) annotation).value(),object);
                    }
                }
            }
        }
        //过滤掉delet
        int delete = mappedStatement.getId().indexOf("delete");
        if(delete>=0){
        	return "delete";
        }
        
        //过滤掉创建 create
        int create = mappedStatement.getId().indexOf("create");
        if(create>=0){
        	return "create";
        }
        
        //5.获取boundSql 
        BoundSql boundSql = mappedStatement.getBoundSql(map);
        return showSql(configuration,boundSql);
    }
    

    /**
     * 解析BoundSql， Generation 不含占位符的SQL语句
     * @param configuration
     * @param boundSql
     * @return
     */
    private  static String showSql(Configuration configuration, BoundSql boundSql) {
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
     * 若为字符串或者日期Type ，则在 Parameter 两边添加''
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
     * 获取利用反射获取类里面的值和 Name
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map=BeanUtils.beanToMap(obj);
//        Class<?> clazz = obj.getClass();
//        System.out.println(clazz);
//        for (Field field : clazz.getDeclaredFields()) {
//            field.setAccessible(true);
//            String fieldName = field.getName();
//            Object value = field.get(obj);
//            map.put(fieldName, value);
//        }
        return map;
    }
}
