package com.matariky.automation.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class StringTemplateUtilsTest {

    @InjectMocks
    private StringTemplateUtils stringTemplateUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRenderWithDefaultRegex() {
        // Given
        String template = "Hello, [name]!";
        Map<String, String> data = new HashMap<>();
        data.put("name", "World");

        // When
        String result = stringTemplateUtils.render(template, data);

        // Then
        assertThat(result).isEqualTo("Hello, World!");
    }

    @Test
    void testRenderWithCustomRegex() {
        // Given
        String template = "Hello, {{name}}!";
        Map<String, String> data = new HashMap<>();
        data.put("name", "World");
        String customRegex = "\\{\\{(.+?)\\}\\}";

        // When
        String result = stringTemplateUtils.render(template, data, customRegex);

        // Then
        assertThat(result).isEqualTo("Hello, World!");
    }

    @Test
    void testRenderWithNullTemplate() {
        // Given
        String template = null;
        Map<String, String> data = new HashMap<>();
        data.put("name", "World");

        // When
        String result = stringTemplateUtils.render(template, data);

        // Then
        assertThat(result).isEqualTo("");
    }

    @Test
    void testRenderWithNullRegex() {
        // Given
        String template = "Hello, [name]!";
        Map<String, String> data = new HashMap<>();
        data.put("name", "World");

        // When
        String result = stringTemplateUtils.render(template, data, null);

        // Then
        assertThat(result).isEqualTo("Hello, [name]!");
    }

    @Test
    void testRenderWithEmptyData() {
        // Given
        String template = "Hello, [name]!";
        Map<String, String> data = new HashMap<>();

        // When
        String result = stringTemplateUtils.render(template, data);

        // Then
        assertThat(result).isEqualTo("Hello, [name]!");
    }

    @Test
    void testRenderWithMissingData() {
        // Given
        String template = "Hello, [name]!";
        Map<String, String> data = new HashMap<>();

        // When
        String result = stringTemplateUtils.render(template, data);

        // Then
        assertThat(result).isEqualTo("Hello, [name]!");
    }
}
