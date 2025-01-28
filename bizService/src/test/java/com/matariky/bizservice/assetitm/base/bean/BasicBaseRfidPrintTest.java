package com.matariky.bizservice.assetitm.base.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BasicBaseRfidPrintTest {

    @InjectMocks
    private BasicBaseRfidPrint basicBaseRfidPrint;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        basicBaseRfidPrint.setId(expectedId);

        // When
        Long actualId = basicBaseRfidPrint.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTaskName() {
        // Given
        String expectedTaskName = "Task1";
        basicBaseRfidPrint.setTaskName(expectedTaskName);

        // When
        String actualTaskName = basicBaseRfidPrint.getTaskName();

        // Then
        assertThat(actualTaskName).isEqualTo(expectedTaskName);
    }

    @Test
    void testGetPrintType() {
        // Given
        String expectedPrintType = "1";
        basicBaseRfidPrint.setPrintType(expectedPrintType);

        // When
        String actualPrintType = basicBaseRfidPrint.getPrintType();

        // Then
        assertThat(actualPrintType).isEqualTo(expectedPrintType);
    }

    @Test
    void testGetTaskTime() {
        // Given
        Long expectedTaskTime = 123456789L;
        basicBaseRfidPrint.setTaskTime(expectedTaskTime);

        // When
        Long actualTaskTime = basicBaseRfidPrint.getTaskTime();

        // Then
        assertThat(actualTaskTime).isEqualTo(expectedTaskTime);
    }

    @Test
    void testGetDeviceId() {
        // Given
        Long expectedDeviceId = 2L;
        basicBaseRfidPrint.setDeviceId(expectedDeviceId);

        // When
        Long actualDeviceId = basicBaseRfidPrint.getDeviceId();

        // Then
        assertThat(actualDeviceId).isEqualTo(expectedDeviceId);
    }

    @Test
    void testGetPrintCode() {
        // Given
        String expectedPrintCode = "P123";
        basicBaseRfidPrint.setPrintCode(expectedPrintCode);

        // When
        String actualPrintCode = basicBaseRfidPrint.getPrintCode();

        // Then
        assertThat(actualPrintCode).isEqualTo(expectedPrintCode);
    }

    @Test
    void testGetPrintName() {
        // Given
        String expectedPrintName = "Printer1";
        basicBaseRfidPrint.setPrintName(expectedPrintName);

        // When
        String actualPrintName = basicBaseRfidPrint.getPrintName();

        // Then
        assertThat(actualPrintName).isEqualTo(expectedPrintName);
    }

    // Add more test methods for other getters and setters in BasicBaseRfidPrint
}
