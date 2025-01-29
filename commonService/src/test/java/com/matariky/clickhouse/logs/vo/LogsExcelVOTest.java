package com.matariky.clickhouse.logs.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LogsExcelVOTest {

    @InjectMocks
    private LogsExcelVO logsexcelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTenantId() {
        // Given
        String expectedTenantId = "tenant123";
        logsexcelvo.setTenantId(expectedTenantId);

        // When
        String actualTenantId = logsexcelvo.getTenantId();

        // Then
        assertThat(actualTenantId).isEqualTo(expectedTenantId);
    }

    @Test
    void testGetAppName() {
        // Given
        String expectedAppName = "appName";
        logsexcelvo.setAppName(expectedAppName);

        // When
        String actualAppName = logsexcelvo.getAppName();

        // Then
        assertThat(actualAppName).isEqualTo(expectedAppName);
    }

    @Test
    void testGetOperationName() {
        // Given
        String expectedOperationName = "operationName";
        logsexcelvo.setOperationName(expectedOperationName);

        // When
        String actualOperationName = logsexcelvo.getOperationName();

        // Then
        assertThat(actualOperationName).isEqualTo(expectedOperationName);
    }

    @Test
    void testGetMessage() {
        // Given
        String expectedMessage = "message";
        logsexcelvo.setMessage(expectedMessage);

        // When
        String actualMessage = logsexcelvo.getMessage();

        // Then
        assertThat(actualMessage).isEqualTo(expectedMessage);
    }

    @Test
    void testGetLogType() {
        // Given
        String expectedLogType = "logType";
        logsexcelvo.setLogType(expectedLogType);

        // When
        String actualLogType = logsexcelvo.getLogType();

        // Then
        assertThat(actualLogType).isEqualTo(expectedLogType);
    }

    @Test
    void testGetTidEpc() {
        // Given
        String expectedTidEpc = "tidEpc";
        logsexcelvo.setTidEpc(expectedTidEpc);

        // When
        String actualTidEpc = logsexcelvo.getTidEpc();

        // Then
        assertThat(actualTidEpc).isEqualTo(expectedTidEpc);
    }

    @Test
    void testGetOperator() {
        // Given
        String expectedOperator = "operator";
        logsexcelvo.setOperator(expectedOperator);

        // When
        String actualOperator = logsexcelvo.getOperator();

        // Then
        assertThat(actualOperator).isEqualTo(expectedOperator);
    }

    @Test
    void testGetHasError() {
        // Given
        String expectedHasError = "true";
        logsexcelvo.setHasError(expectedHasError);

        // When
        String actualHasError = logsexcelvo.getHasError();

        // Then
        assertThat(actualHasError).isEqualTo(expectedHasError);
    }

    @Test
    void testGetIp() {
        // Given
        String expectedIp = "127.0.0.1";
        logsexcelvo.setIp(expectedIp);

        // When
        String actualIp = logsexcelvo.getIp();

        // Then
        assertThat(actualIp).isEqualTo(expectedIp);
    }

    @Test
    void testGetMac() {
        // Given
        String expectedMac = "00:0a:95:9d:68:16";
        logsexcelvo.setMac(expectedMac);

        // When
        String actualMac = logsexcelvo.getMac();

        // Then
        assertThat(actualMac).isEqualTo(expectedMac);
    }

    @Test
    void testGetTimestamp() {
        // Given
        String expectedTimestamp = "2023-10-01T12:00:00Z";
        logsexcelvo.setTimestamp(expectedTimestamp);

        // When
        String actualTimestamp = logsexcelvo.getTimestamp();

        // Then
        assertThat(actualTimestamp).isEqualTo(expectedTimestamp);
    }
}
