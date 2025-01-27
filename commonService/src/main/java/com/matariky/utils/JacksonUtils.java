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

public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private static ObjectMapper objectMapper;

    /** Format **/
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
        /** Include all fields of the object **/
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        /** Disable default conversion to timestamps **/
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        /** Ignore errors when converting empty beans to JSON **/
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        /** Unify all date formats to the following style: yyyy-MM-dd HH:mm:ss **/
        objectMapper.setDateFormat(new SimpleDateFormat(STANDARD_FORMAT));
        /**
         * Ignore properties that exist in the JSON string but not in the Java object to
         * prevent errors
         **/
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        /** Case insensitive **/
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }

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

    public static JsonNode toJsonNode(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            logger.error("Parse String to toJsonNode  error : jsonString={}", jsonString, e);
        }
        return null;
    }

    private JacksonUtils() {

    }
}
