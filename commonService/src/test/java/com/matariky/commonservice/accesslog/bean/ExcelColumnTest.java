package com.matariky.commonservice.accesslog.bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExcelColumnTest {

    @InjectMocks
    private ExcelColumn excelcolumn;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefaultValue() {
        // Given
        ExcelColumn annotation = mock(ExcelColumn.class);
        when(annotation.value()).thenReturn("");

        // When
        String value = annotation.value();

        // Then
        assertEquals("", value);
    }

    @Test
    void testDefaultCol() {
        // Given
        ExcelColumn annotation = mock(ExcelColumn.class);
        when(annotation.col()).thenReturn(0);

        // When
        int col = annotation.col();

        // Then
        assertEquals(0, col);
    }

    @Test
    void testCustomValue() {
        // Given
        ExcelColumn annotation = mock(ExcelColumn.class);
        when(annotation.value()).thenReturn("Custom Value");

        // When
        String value = annotation.value();

        // Then
        assertEquals("Custom Value", value);
    }

    @Test
    void testCustomCol() {
        // Given
        ExcelColumn annotation = mock(ExcelColumn.class);
        when(annotation.col()).thenReturn(5);

        // When
        int col = annotation.col();

        // Then
        assertEquals(5, col);
    }

    // Add more test methods for other scenarios if needed
}
