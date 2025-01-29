package com.matariky.redis.redisson;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LockKeyTest {

    @InjectMocks
    private LockKey lockkey;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTapeLibraryLockKey() {
        // Given
        String expectedValue = "tape_library_lock:";

        // When
        String actualValue = LockKey.TAPE_LIBRARY_LOCK_KEY;

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testTapeInventoryTaskLockKey() {
        // Given
        String expectedValue = "tape_inventory_task_lock:";

        // When
        String actualValue = LockKey.TAPE_INVENTORY_TASK_LOCK_KEY;

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testTapeInventoryTaskImmediateLockKey() {
        // Given
        String expectedValue = "tape_inventory_task_immediate_lock:";

        // When
        String actualValue = LockKey.TAPE_INVENTORY_TASK_IMMEDIATE_LOCK_KEY;

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testTapeRfidCreateTaskImmediateLockKey() {
        // Given
        String expectedValue = "tape_rfidcreate_task_immediate_lock:";

        // When
        String actualValue = LockKey.TAPE_RFIDCREATE_TASK_IMMEDIATE_LOCK_KEY;

        // Then
        assertEquals(expectedValue, actualValue);
    }
}
