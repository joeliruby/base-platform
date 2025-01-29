package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CodeUtilsTest {

    @InjectMocks
    private CodeUtils codeutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNum() {
        // Given
        int digit = 5;

        // When
        String result = CodeUtils.getNum(digit);

        // Then
        assertNotNull(result);
        assertEquals(digit, result.length());
        assertTrue(result.matches("\\d+"));
    }

    @Test
    void testCreateNo() {
        // Given
        String datetime = "20231010"; // Mocked datetime
        mockStatic(DateUtil.class);
        when(DateUtil.getCurrentTime()).thenReturn(datetime);

        // When
        String result = CodeUtils.CreateNo();

        // Then
        assertNotNull(result);
        assertEquals(datetime.length() + 6, result.length());
        verify(DateUtil.class, times(1));
        DateUtil.getCurrentTime();
    }

    @Test
    void testCreateBatchCode() {
        // Given
        String datetime = "20231010"; // Mocked datetime
        mockStatic(DateUtil.class);
        when(DateUtil.getCurrentTime()).thenReturn(datetime);

        // When
        String result = CodeUtils.CreateBatchCode();

        // Then
        assertNotNull(result);
        assertEquals(datetime.length() + 4, result.length());
        verify(DateUtil.class, times(1));
        DateUtil.getCurrentTime();
    }

    @Test
    void testGenerateRandomString() {
        // Given
        int length = 10;

        // When
        String result = CodeUtils.generateRandomString(length);

        // Then
        assertNotNull(result);
        assertEquals(length, result.length());
        assertTrue(result.matches("[A-Z0-9]+"));
    }

    @Test
    void testRandomHexGenerator() {
        // When
        String result = CodeUtils.randomHexGenerator();

        // Then
        assertNotNull(result);
        assertEquals(8, result.length());
        assertTrue(result.matches("[0-9a-f]+"));
    }

    @Test
    void testGenerateEpc() {
        // Given
        int length = 12;

        // When
        String result = CodeUtils.generateEpc(length);

        // Then
        assertNotNull(result);
        assertEquals(length, result.length());
        assertTrue(result.matches("[0-9A-F]+"));
    }
}
