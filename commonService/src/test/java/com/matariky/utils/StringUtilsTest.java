package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class StringUtilsTest {

    @InjectMocks
    private StringUtils stringutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFullName() {
        // Given
        String name = "John Doe";
        String code = "123";

        // When
        String result = StringUtils.getFullName(name, code);

        // Then
        assertThat(result).isEqualTo("John Doe (123)");
    }

    @Test
    void testJoining() {
        // Given
        Object[] objs = { "John", null, "Doe", 123 };

        // When
        String result = StringUtils.joining(objs);

        // Then
        assertThat(result).isEqualTo("John,Doe,123");
    }

    @Test
    void testLowerFirst() {
        // Given
        String str = "Hello";

        // When
        String result = StringUtils.lowerFirst(str);

        // Then
        assertThat(result).isEqualTo("hello");
    }

    @Test
    void testIsNotNull() {
        // Given
        Object obj = new Object();

        // When
        boolean result = StringUtils.isNotNull(obj);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    void testIsNull() {
        // Given
        Object obj = null;

        // When
        boolean result = StringUtils.isNull(obj);

        // Then
        assertThat(result).isTrue();
    }

    // Add more test methods for other methods in StringUtils if needed
}
