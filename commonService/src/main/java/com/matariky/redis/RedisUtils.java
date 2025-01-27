package com.matariky.redis;

import cn.hutool.core.util.BooleanUtil;

import com.matariky.constant.CacheConstants;
import com.matariky.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 */
@Component
public class RedisUtils {

    private static final LongRedisSerializer longRedisSerializer = new LongRedisSerializer();

    @Autowired
    private RedisTemplate redisTemplate;

    /** 默认过期时长为24小时 ,单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;

    /** 过期时长为1小时 ,单位：秒 */
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L;

    /** 过期时长为6小时 ,单位：秒 */
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /** 不 Configuration过期时长 */
    public final static long NOT_EXPIRE = -1L;

    /**
     * @Description: Add 缓存 Data
     * @Author: bo.chen
     * @Date: 2023/9/7 15:41
     * @param key
     * @param value
     * @param expire
     * @param timeUnit
     **/
    public <T> void set(String key, T value, long expire, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expire, timeUnit);
    }

    public <T> void set(String key, T value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public <T> void set(String key, T value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, long expire) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        T value = operation.get(key);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
        return value;
    }

    public <T> T get(String key) {
        return get(key, NOT_EXPIRE);
    }

    /**
     * @Description: 判断key Wether 存在
     * @Author: bo.chen
     * @Date: 2023/11/13 15:58
     * @param key
     * @return boolean
     **/
    public boolean hasKey(String key) {
        return BooleanUtil.isTrue(redisTemplate.hasKey(key));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    public <T> Map<String, T> hGetAll(String key) {
        HashOperations<String, String, T> hashOperations = redisTemplate.opsForHash();
        Map<String, T> resultMap = hashOperations.entries(key);
        return Objects.isNull(resultMap) ? Collections.emptyMap() : resultMap;
    }

    public void hMSet(String key, Map<String, Object> map) {
        hMSet(key, map, DEFAULT_EXPIRE);
    }

    public void hMSet(String key, Map<String, Object> map, long expire) {
        redisTemplate.opsForHash().putAll(key, map);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void hSet(String key, String field, Object value) {
        hSet(key, field, value, DEFAULT_EXPIRE);
    }

    public void hSet(String key, String field, Object value, long expire) {
        redisTemplate.opsForHash().put(key, field, value);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void hDel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    public void leftPush(String key, Object value) {
        leftPush(key, value, DEFAULT_EXPIRE);
    }

    public void leftPush(String key, Object value, long expire) {
        redisTemplate.opsForList().leftPush(key, value);

        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * @Description: Retrieveset集合 Quantity
     * @Author: bo.chen
     * @Date: 2023/9/7 15:54
     * @param key
     **/
    public long getSetSize(String key) {
        Long size = redisTemplate.opsForSet().size(key);
        return Objects.isNull(size) ? NumberUtils.LONG_ZERO : size;
    }

    /**
     * @Description: Retrieveset差值
     * @Author: bo.chen
     * @Date: 2023/9/19 14:24
     * @param key1
     * @param key2
     * @return java.util.Set<T>
     **/
    public <T> Set<T> getSetDifference(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }

    /**
     * @Description: Wether 存在set集合中
     * @Author: bo.chen
     * @Date: 2023/9/22 15:56
     * @param key
     * @param value
     * @return boolean
     **/
    public <T> boolean isSetMember(String key, T value) {
        Boolean result = redisTemplate.opsForSet().isMember(key, value);
        return Objects.isNull(result) ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * @Description: Retrieveset集合 Data
     * @Author: bo.chen
     * @Date: 2023/9/7 17:14
     * @param key
     * @return java.util.Set<java.lang.Object>
     **/
    public Set<Object> getSetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * @Description: Retrieveset增加元素
     * @Date: 2023/9/7 17:14
     * @return java.util.Set<java.lang.Object>
     **/
    public void addToSet(String setName, Object value) {
        redisTemplate.opsForSet().add(setName, value);
    }

    /**
     * @Description: Cache Value Incremental Method
     * @Author: bo.chen
     * @Date: 2023/9/5 16:28
     * @param key
     * @return java.lang.Long
     **/
    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * @Description: Set expiration time
     * @Author: bo.chen
     * @Date: 2023/9/5 16:31
     * @param key     cache key
     * @param timeout Expiration time
     * @param unit    Time Unit
     * @return boolean
     **/
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * @Description: 执行lua 表达式
     * @Author: bo.chen
     * @Date: 2023/5/11 16:18
     * @param lua
     * @param keys
     * @param args
     * @return java.lang.Long
     **/
    public <T> Long executeLua(String lua, List<String> keys, T... args) {
        return (Long) redisTemplate.execute(new DefaultRedisScript<>(lua, Long.class),
                redisTemplate.getHashValueSerializer(), longRedisSerializer, keys, args);
    }

    /**
     * @Description: Retrieve过期 Time
     * @Author: bo.chen
     * @Date: 2023/9/20 16:06
     * @param key
     * @param timeUnit
     * @return long
     **/
    public Long getExpire(String key, TimeUnit timeUnit) {
        return NumberUtils.ifNullToZero(redisTemplate.getExpire(key, timeUnit));
    }

    /**
     * @Description: Retrievemap集合大小
     * @Author: bo.chen
     * @Date: 2023/10/10 10:39
     * @param key
     * @return long
     **/
    public long getHashSize(String key) {
        return NumberUtils.ifNullToZero(redisTemplate.opsForHash().size(key));
    }

    public void cacheKeycloakToken(Long id, String keycloakToken) {
        set(CacheConstants.KEYCLOAK_TOKEN_PREFIX + id, keycloakToken, DEFAULT_EXPIRE);
    }

    public void unCacheKeycloakToken(Long id) {
        delete(CacheConstants.KEYCLOAK_TOKEN_PREFIX + id);

    }

    public String getKeycloakToken(String userId) {
        return get(CacheConstants.KEYCLOAK_TOKEN_PREFIX + userId);
    }

}
