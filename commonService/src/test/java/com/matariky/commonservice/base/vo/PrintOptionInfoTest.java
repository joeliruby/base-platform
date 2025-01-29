package com.matariky.commonservice.base.vo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PrintOptionInfoTest {

    @InjectMocks
    private PrintOptionInfo printOptionInfo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetId() {
        // Given
        Long expectedId = 1L;
        printOptionInfo.setId(expectedId);

        // When
        Long actualId = printOptionInfo.getId();

        // Then
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    void testGetTypeName() {
        // Given
        String expectedTypeName = "Printer";
        printOptionInfo.setTypeName(expectedTypeName);

        // When
        String actualTypeName = printOptionInfo.getTypeName();

        // Then
        assertThat(actualTypeName).isEqualTo(expectedTypeName);
    }

    @Test
    void testGetDeviceCode() {
        // Given
        String expectedDeviceCode = "PRN123";
        printOptionInfo.setDeviceCode(expectedDeviceCode);

        // When
        String actualDeviceCode = printOptionInfo.getDeviceCode();

        // Then
        assertThat(actualDeviceCode).isEqualTo(expectedDeviceCode);
    }

    @Test
    void testGetLabel() {
        // Given
        String expectedLabel = "Print Label";
        printOptionInfo.setLabel(expectedLabel);

        // When
        String actualLabel = printOptionInfo.getLabel();

        // Then
        assertThat(actualLabel).isEqualTo(expectedLabel);
    }

    // Add more test methods for other methods in PrintOptionInfo if needed
}
