package com.matariky.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;


/**
 * @description: Long 字符序列化
 * @author: bo.chen
 * @create: 2023/5/11 16:14
 **/
public class LongRedisSerializer implements RedisSerializer<Long> {

    @Override
    public byte[] serialize(Long l) throws SerializationException {
        if (Objects.isNull(l))
            return null;
        return String.valueOf(l).getBytes();
    }

    @Override
    public Long deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes))
            return null;
        return Long.parseLong(new String(bytes));
    }
}
