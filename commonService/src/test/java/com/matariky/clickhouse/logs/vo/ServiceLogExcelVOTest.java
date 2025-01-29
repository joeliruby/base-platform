package com.matariky.clickhouse.logs.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceLogExcelVOTest {

    @InjectMocks
    private ServiceLogExcelVO servicelogexcelvo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetServiceName() {
        // Given
        String expectedServiceName = "Test Service";
        servicelogexcelvo.setServiceName(expectedServiceName);

        // When
        String actualServiceName = servicelogexcelvo.getServiceName();

        // Then
        assertEquals(expectedServiceName, actualServiceName);
    }

    @Test
    void testGetErrorMessage() {
        // Given
        String expectedErrorMessage = "Test Error";
        servicelogexcelvo.setErrorMessage(expectedErrorMessage);

        // When
        String actualErrorMessage = servicelogexcelvo.getErrorMessage();

        // Then
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void testGetDurationNano() {
        // Given
        BigDecimal expectedDuration = new BigDecimal("123.456");
        servicelogexcelvo.setDurationNano(expectedDuration);

        // When
        BigDecimal actualDuration = servicelogexcelvo.getDurationNano();

        // Then
        assertEquals(expectedDuration, actualDuration);
    }

    @Test
    void testHasError() {
        // Given
        String expectedHasError = "Yes";
        servicelogexcelvo.setHasError(expectedHasError);

        // When
        String actualHasError = servicelogexcelvo.getHasError();

        // Then
        assertEquals(expectedHasError, actualHasError);
    }

    @Test
    void testGetDbName() {
        // Given
        String expectedDbName = "TestDB";
        servicelogexcelvo.setDbName(expectedDbName);

        // When
        String actualDbName = servicelogexcelvo.getDbName();

        // Then
        assertEquals(expectedDbName, actualDbName);
    }

    // Add more test methods for other getters and setters in ServiceLogExcelVO
}
