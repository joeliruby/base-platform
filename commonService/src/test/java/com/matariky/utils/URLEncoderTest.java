package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class URLEncoderTest {

    @InjectMocks
    private URLEncoder urlencoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEncodeWithSafeCharacters() {
        // Given
        String input = "abcABC123-._~";
        String expected = "abcABC123-._~";

        // When
        String result = urlencoder.encode(input, StandardCharsets.UTF_8);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testEncodeWithSpace() {
        // Given
        urlencoder.setEncodeSpaceAsPlus(true);
        String input = "a b c";
        String expected = "a+b+c";

        // When
        String result = urlencoder.encode(input, StandardCharsets.UTF_8);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testEncodeWithSpecialCharacters() {
        // Given
        String input = "a!$&'()*+,;=:@";
        String expected = "a!$&'()*+,;=:@"; // These characters should not be encoded

        // When
        String result = urlencoder.encode(input, StandardCharsets.UTF_8);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testEncodeWithUnsafeCharacters() {
        // Given
        String input = "a b/c?d#e";
        String expected = "a%20b%2Fc%3Fd%23e"; // Space, '/', '?', and '#' should be encoded

        // When
        String result = urlencoder.encode(input, StandardCharsets.UTF_8);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testEncodeWithNonAsciiCharacters() {
        // Given
        String input = "你好";
        String expected = "%E4%BD%A0%E5%A5%BD"; // Non-ASCII characters should be encoded

        // When
        String result = urlencoder.encode(input, StandardCharsets.UTF_8);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
