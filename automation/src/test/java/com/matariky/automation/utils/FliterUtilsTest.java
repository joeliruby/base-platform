package com.matariky.automation.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FliterUtilsTest {

    @InjectMocks
    private FliterUtils fliterutils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFilterWithNullContent() {
        // Given
        String content = null;

        // When
        String result = fliterutils.filter(content);

        // Then
        assertThat(result).isEqualTo("");
    }

    @Test
    void testFilterWithEmptyContent() {
        // Given
        String content = "";

        // When
        String result = fliterutils.filter(content);

        // Then
        assertThat(result).isEqualTo("");
    }

    @Test
    void testFilterWithValidContent() {
        // Given
        String content = "Hello World!";

        // When
        String result = fliterutils.filter(content);

        // Then
        assertThat(result).isEqualTo("Hello World!");
    }

    @Test
    void testFilterWithControlCharacters() {
        // Given
        String content = "Hello\u0000World!";

        // When
        String result = fliterutils.filter(content);

        // Then
        assertThat(result).isEqualTo("Hello World!");
    }

    @Test
    void testFilterWithDeleteCharacter() {
        // Given
        String content = "Hello\u007FWorld!";

        // When
        String result = fliterutils.filter(content);

        // Then
        assertThat(result).isEqualTo("Hello World!");
    }
}
