package com.matariky.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

;

/**
  * @Description: jackson工具类
  * @Author: bo.chen
  * @Date: 2022/7/14 17:39
  **/
public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper objectMapper;

    /** 日起格式化 **/
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(STANDARD_FORMAT);

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    static {
        objectMapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        /** 对象的所有字段全部列入 **/
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        /** 取消默认转换timestamps形式 **/
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        /** 忽略空Bean转json的错误 **/
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        /** 所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss **/
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        /** 忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误 **/
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        /** 不区分大小写 **/
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

    /**
      * @Description: 将对象转成json字符串
      * @Author: bo.chen
      * @Date: 2020/9/17 15:10
      * @param obj:
      * @return: java.lang.String
      **/
    public static String toJsonString(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof String) {
                    return (String) obj;
                } else {
                    return objectMapper.writeValueAsString(obj);
                }
            } catch (Exception e) {
                logger.error("toJsonString error!", e);
            }
        }
        return null;
    }

    /**
      * @Description: json字符串转对象
      * @Author: bo.chen
      * @Date: 2020/9/17 15:15
      * @param jsonString:
      * @param clazz:
      * @return: T
      **/
    public static <T> T toBean(String jsonString, Class<T> clazz) {
        if (StringUtils.isNotEmpty(jsonString) && clazz != null) {
            try {
                return objectMapper.readValue(jsonString, clazz);
            } catch (Exception e) {
                logger.error("toBean error! jsonString={}", jsonString, e);
            }
        }
        return null;
    }


    /**
      * @Description: json字符串转对象
      * @Author: bo.chen
      * @Date: 2020/9/17 15:22
      * @param jsonString:
      * @param typeReference:
      * @return: T
      **/
    public static <T> T toObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isNotEmpty(jsonString) && typeReference != null) {
            try {
                return objectMapper.readValue(jsonString, typeReference);
            } catch (Exception e) {
                logger.error("Parse String to Object error!jsonString={}", jsonString, e);
            }
        }
        return null;
    }

    /**
      * @Description: 字符串转集合对象
      * @Author: bo.chen
      * @Date: 2020/9/17 15:21
      * @param jsonString:
      * @param parentClazz:
      * @param elementClazz:
      * @return: T
      **/
    public static <T> T toBean(String jsonString, Class<?> parentClazz, Class<?>... elementClazz) {
        if (StringUtils.isNotEmpty(jsonString) && parentClazz != null && elementClazz != null) {
            try {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(parentClazz, elementClazz);
                return objectMapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                logger.error("Parse String to Collection Object error : jsonString={}", jsonString, e);
            }
        }
        return null;
    }

    /**
      * @Description: 嵌套json字符串转jsonNode
      * @Author: bo.chen
      * @Date: 2021/1/11 11:45
      * @param jsonString:
      * @return: com.fasterxml.jackson.databind.JsonNode
      **/
    public static JsonNode toJsonNode(String jsonString) {
        try {
            return  objectMapper.readTree(jsonString);
        } catch (Exception e) {
            logger.error("Parse String to toJsonNode  error : jsonString={}", jsonString, e);
        }
        return null;
    }

    private JacksonUtils() {

    }
}
