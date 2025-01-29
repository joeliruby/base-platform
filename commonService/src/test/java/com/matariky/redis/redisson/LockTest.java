package com.matariky.redis.redisson;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.redisson.api.RLock;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
public class LockTest {

    @InjectMocks
    private Lock lock;

    @Mock
    private RLock rLock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUnlockWhenTransactionSynchronizationIsActive() {
        // Given
        when(TransactionSynchronizationManager.isSynchronizationActive()).thenReturn(true);
        when(rLock.isLocked()).thenReturn(true);
        when(rLock.isHeldByCurrentThread()).thenReturn(true);

        // When
        lock.unlock();

        // Then
        verify(rLock, never()).unlock();
    }

    @Test
    void testUnlockWhenTransactionSynchronizationIsNotActive() {
        // Given
        when(TransactionSynchronizationManager.isSynchronizationActive()).thenReturn(false);
        when(rLock.isLocked()).thenReturn(true);
        when(rLock.isHeldByCurrentThread()).thenReturn(true);

        // When
        lock.unlock();

        // Then
        verify(rLock, times(1)).unlock();
    }

    @Test
    void testUnlockWhenLockIsNotHeldByCurrentThread() {
        // Given
        when(TransactionSynchronizationManager.isSynchronizationActive()).thenReturn(false);
        when(rLock.isLocked()).thenReturn(true);
        when(rLock.isHeldByCurrentThread()).thenReturn(false);

        // When
        lock.unlock();

        // Then
        verify(rLock, never()).unlock();
    }

    @Test
    void testUnlockWhenLockIsNotLocked() {
        // Given
        when(TransactionSynchronizationManager.isSynchronizationActive()).thenReturn(false);
        when(rLock.isLocked()).thenReturn(false);

        // When
        lock.unlock();

        // Then
        verify(rLock, never()).unlock();
    }
}
