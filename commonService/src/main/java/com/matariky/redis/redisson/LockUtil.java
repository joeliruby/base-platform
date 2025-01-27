package com.matariky.redis.redisson;

import com.matariky.commonservice.upload.utils.SpringContextUtils;
import com.matariky.exception.QslException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class LockUtil {

    private static final Logger logger = LoggerFactory.getLogger(LockUtil.class);

    private static final RedissonClient redissonClient = SpringContextUtils.getBean(RedissonClient.class);

    public static Lock lock(long waitSeconds, String key) {
        RLock rLock = redissonClient.getLock(key);
        Boolean success = Boolean.FALSE;
        try {
            success = rLock.tryLock(waitSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.error("  Retrieve Lock Exception！lockName={}", key, e);
        }
        if (!success) {
            throw new QslException("  System Busy  , Try again later！");
        }
        return new Lock(rLock);
    }

    public static Lock lock(String key) {
        return LockUtil.lock(30, key);
    }

    private LockUtil() {

    }
}
