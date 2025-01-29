package com.matariky.id;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

@SpringBootTest
public class SnowflakeIdWorkerTest {

    @InjectMocks
    private SnowflakeIdWorker snowflakeIdWorker;

    @Mock
    private IdentifierGenerator generator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        long expectedId = 123456789L;
        when(generator.nextId(null)).thenReturn(expectedId);

        // When
        long actualId = SnowflakeIdWorker.getId();

        // Then
        assertEquals(expectedId, actualId);
    }

    @Test
    void testGetIdWithDifferentValue() {
        // Given
        long expectedId = 987654321L;
        when(generator.nextId(null)).thenReturn(expectedId);

        // When
        long actualId = SnowflakeIdWorker.getId();

        // Then
        assertEquals(expectedId, actualId);
    }

    // Add more test methods for other scenarios if needed
}
