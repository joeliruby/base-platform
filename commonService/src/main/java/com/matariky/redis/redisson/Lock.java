package com.matariky.redis.redisson;

import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.Serializable;

@AllArgsConstructor
public class Lock implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Lock.class);

    /**
     * redisson Lock Object
     */
    private RLock rLock;
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
                            logger.error("Exception releasing lock！lockName={}", rLock.getName(), e);
                        }
                    }
                });
            } else {
                if (rLock.isLocked() && rLock.isHeldByCurrentThread()) {
                    rLock.unlock();
                }
            }
        } catch (Exception e) {
            logger.error("Exception releasing lock！lockName={}", rLock.getName(), e);
        }
    }
}
