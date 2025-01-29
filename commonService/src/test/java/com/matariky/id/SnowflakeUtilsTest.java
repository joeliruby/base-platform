package com.matariky.id;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SnowflakeUtilsTest {

    @InjectMocks
    private SnowflakeUtils snowflakeutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        snowflakeutils = new SnowflakeUtils(1, 1);
    }

    @Test
    void testNextId() {
        // Given
        // Initialize your test data and mocks here

        // When
        long id = snowflakeutils.nextId();

        // Then
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    void testGetId() {
        // Given
        // Initialize your test data and mocks here

        // When
        long id = SnowflakeUtils.getId();

        // Then
        assertNotNull(id);
        assertTrue(id > 0);
    }

    @Test
    void testClockMovedBackwards() {
        // Given
        SnowflakeUtils spyUtils = spy(new SnowflakeUtils(1, 1));
        doReturn(System.currentTimeMillis() - 1000).when(spyUtils).getNewstmp();

        // When & Then
        assertThrows(RuntimeException.class, spyUtils::nextId);
    }

    @Test
    void testMaxSequenceReached() {
        // Given
        SnowflakeUtils spyUtils = spy(new SnowflakeUtils(1, 1));
        doReturn(System.currentTimeMillis()).when(spyUtils).getNewstmp();
        spyUtils.nextId(); // Initialize lastStmp

        // When
        for (int i = 0; i < 4096; i++) { // 2^12 = 4096
            spyUtils.nextId();
        }

        // Then
        assertDoesNotThrow(spyUtils::nextId);
    }

    // Add more test methods for other methods in SnowflakeUtils
}
