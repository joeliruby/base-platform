package com.matariky.clickhouse.logs.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.pagehelper.Page;
import com.matariky.clickhouse.logs.entity.Logs;

@SpringBootTest
public class LogsMapperTest {

    @InjectMocks
    private LogsMapper logsmapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAppTracesAll() {
        // Given
        Logs logs = new Logs();
        // Initialize your test data and mocks here

        // When
        Page<Logs> result = logsmapper.getAppTracesAll(logs);

        // Then
        // Assert the expected results
        assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    void testGetAppTracesCount() {
        // Given
        Logs logs = new Logs();
        // Initialize your test data and mocks here

        // When
        Long count = logsmapper.getAppTracesCount(logs);

        // Then
        // Assert the expected results
        assertNotNull(count);
        // Add more assertions as needed
    }

    // Add more test methods for other methods in LogsMapper
}
