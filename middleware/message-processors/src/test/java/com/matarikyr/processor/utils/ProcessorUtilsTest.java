package com.matarikyr.processor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.processor.utils.ProcessorUtils;

@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class ProcessorUtilsTest {

    @InjectMocks
    private ProcessorUtils processorutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProperty() {
        // Given
        String env = "test";
        String key = "someKey";
        String expectedValue = "someValue";
        InputStream inputStream = new ByteArrayInputStream(
                (key + "=" + expectedValue).getBytes(StandardCharsets.UTF_8));
        when(processorutils.getClass().getClassLoader().getResourceAsStream(env + ".properties"))
                .thenReturn(inputStream);

        // When
        String actualValue = processorutils.getProperty(env, key);

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testGetPropertyWithNonExistentKey() {
        // Given
        String env = "test";
        String key = "nonExistentKey";
        InputStream inputStream = new ByteArrayInputStream("someKey=someValue".getBytes(StandardCharsets.UTF_8));
        when(processorutils.getClass().getClassLoader().getResourceAsStream(env + ".properties"))
                .thenReturn(inputStream);

        // When
        String actualValue = processorutils.getProperty(env, key);

        // Then
        assertNull(actualValue);
    }

    @Test
    void testGetPropertyWithInvalidFile() {
        // Given
        String env = "invalid";
        String key = "someKey";
        when(processorutils.getClass().getClassLoader().getResourceAsStream(env + ".properties")).thenReturn(null);

        // When
        String actualValue = processorutils.getProperty(env, key);

        // Then
        assertNull(actualValue);
    }

    // Add more test methods for other scenarios in ProcessorUtils
}
