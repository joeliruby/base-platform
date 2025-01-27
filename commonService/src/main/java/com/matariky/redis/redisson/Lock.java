package com.matariky.redis.redisson;

import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.Serializable;

/**
 * @description: 锁
 * @author: bo.chen
 * @create: 2023/4/17 15:53
 **/
@AllArgsConstructor
public class Lock implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Lock.class);

    /**
     * redisson 锁 Object
     */
    private RLock rLock;

    /**
     * @Description: 释放锁
     * @Author: bo.chen
     * @Date: 2023/4/17 15:55
     **/
    public void unlock() {
        try {
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                    @Override
                    public void afterCompletion(int status) {
                        try {
                            if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                                rLock.unlock();
                            }
                        } catch (Exception e) {
                            logger.error("释放锁异常！lockName={}", rLock.getName(), e);
                        }
                    }
                });
            } else {
                if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                    rLock.unlock();
                }
            }
        } catch (Exception e) {
            logger.error("释放锁异常！lockName={}", rLock.getName(), e);
        }
    }
}
