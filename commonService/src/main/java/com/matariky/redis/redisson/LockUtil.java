package com.matariky.redis.redisson;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.exception.QslException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @description: 锁工具类
 * @author: bo.chen
 * @create: 2023/3/25 13:34
 **/
public class LockUtil {

    private static final Logger logger = LoggerFactory.getLogger(LockUtil.class);

    private static final RedissonClient redissonClient = SpringContextUtils.getBean(RedissonClient.class);

    /**
     * @Description: Retrieve锁
     * @Author: bo.chen
     * @Date: 2023/4/17 15:26
     * @param waitSeconds Retrieve锁等待秒 Data
     * @param key
     * @return org.redisson.api.RLock
     **/
    public static Lock lock(long waitSeconds, String key) {
        RLock rLock = redissonClient.getLock(key);
        Boolean success = Boolean.FALSE;
        try {
            success = rLock.tryLock(waitSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("  Retrieve锁异常！lockName={}", key, e);
        }
        if (!success) {
            throw new QslException("  System繁忙  ,请稍后再试！");
        }
        return new Lock(rLock);
    }

    /**
     * @Description: Retrieve锁
     * @Author: bo.chen
     * @Date: 2023/4/17 15:43
     * @param key
     * @return org.redisson.api.RLock
     **/
    public static Lock lock(String key) {
        return LockUtil.lock(30, key);
    }

    private LockUtil() {

    }
}
