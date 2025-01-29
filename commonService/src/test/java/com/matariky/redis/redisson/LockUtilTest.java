package com.matariky.redis.redisson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.exception.QslException;

@SpringBootTest
public class LockUtilTest {

    @InjectMocks
    private LockUtil lockUtil;

    @Mock
    private RedissonClient redissonClient;

    @Mock
    private RLock rLock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLockWithWait() throws Exception {
        // Given
        String key = "testKey";
        long waitSeconds = 10;
        when(redissonClient.getLock(key)).thenReturn(rLock);
        when(rLock.tryLock(waitSeconds, TimeUnit.SECONDS)).thenReturn(true);

        // When
        Lock lock = LockUtil.lock(waitSeconds, key);

        // Then
        assertNotNull(lock);
        verify(rLock).tryLock(waitSeconds, TimeUnit.SECONDS);
    }

    @Test
    void testLockWithoutWait() throws Exception {
        // Given
        String key = "testKey";
        when(redissonClient.getLock(key)).thenReturn(rLock);
        when(rLock.tryLock(30, TimeUnit.SECONDS)).thenReturn(true);

        // When
        Lock lock = LockUtil.lock(key);

        // Then
        assertNotNull(lock);
        verify(rLock).tryLock(30, TimeUnit.SECONDS);
    }

    @Test
    void testLockWithWaitFails() throws Exception {
        // Given
        String key = "testKey";
        long waitSeconds = 10;
        when(redissonClient.getLock(key)).thenReturn(rLock);
        when(rLock.tryLock(waitSeconds, TimeUnit.SECONDS)).thenReturn(false);

        // When & Then
        assertThrows(QslException.class, () -> LockUtil.lock(waitSeconds, key));
        verify(rLock).tryLock(waitSeconds, TimeUnit.SECONDS);
    }

    @Test
    void testLockWithoutWaitFails() throws Exception {
        // Given
        String key = "testKey";
        when(redissonClient.getLock(key)).thenReturn(rLock);
        when(rLock.tryLock(30, TimeUnit.SECONDS)).thenReturn(false);

        // When & Then
        assertThrows(QslException.class, () -> LockUtil.lock(key));
        verify(rLock).tryLock(30, TimeUnit.SECONDS);
    }

    // Add more test methods for other methods in LockUtil if needed
}
