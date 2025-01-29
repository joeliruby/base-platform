package com.matariky.clickhouse.logs.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class LogsTest {

    @InjectMocks
    private Logs logs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTenantName() {
        // Given
        logs.setTenantName("Tenant1");

        // When
        String tenantName = logs.getTenantName();

        // Then
        assertThat(tenantName).isEqualTo("Tenant1");
    }

    @Test
    void testSetTenantId() {
        // Given
        String tenantId = "12345";

        // When
        logs.setTenantId(tenantId);

        // Then
        assertThat(logs.getTenantId()).isEqualTo(tenantId);
    }

    @Test
    void testGetAppName() {
        // Given
        logs.setAppName("App1");

        // When
        String appName = logs.getAppName();

        // Then
        assertThat(appName).isEqualTo("App1");
    }

    @Test
    void testSetOperationName() {
        // Given
        String operationName = "Operation1";

        // When
        logs.setOperationName(operationName);

        // Then
        assertThat(logs.getOperationName()).isEqualTo(operationName);
    }

    @Test
    void testGetMessage() {
        // Given
        logs.setMessage("This is a log message");

        // When
        String message = logs.getMessage();

        // Then
        assertThat(message).isEqualTo("This is a log message");
    }

    @Test
    void testSetLogType() {
        // Given
        String logType = "ERROR";

        // When
        logs.setLogType(logType);

        // Then
        assertThat(logs.getLogType()).isEqualTo(logType);
    }

    @Test
    void testGetTimestamp() {
        // Given
        logs.setTimestamp("2023-10-01T12:00:00Z");

        // When
        String timestamp = logs.getTimestamp();

        // Then
        assertThat(timestamp).isEqualTo("2023-10-01T12:00:00Z");
    }

    @Test
    void testSetTimeStartLong() {
        // Given
        Long timeStartLong = 1609459200000L;

        // When
        logs.setTimeStartLong(timeStartLong);

        // Then
        assertThat(logs.getTimeStartLong()).isEqualTo(timeStartLong);
    }

    @Test
    void testSetPerPage() {
        // Given
        Integer perPage = 10;

        // When
        logs.setPerPage(perPage);

        // Then
        assertThat(logs.getPerPage()).isEqualTo(perPage);
    }

    // Add more test methods for other fields and methods in Logs
}
