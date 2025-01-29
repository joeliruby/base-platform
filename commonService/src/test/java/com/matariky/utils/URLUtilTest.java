package com.matariky.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class URLUtilTest {

    @InjectMocks
    private URLUtil urlutil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNormalizeWithDefaultParams() {
        // Given
        String url = "http://example.com//path//to//resource";

        // When
        String result = URLUtil.normalize(url);

        // Then
        assertThat(result).isEqualTo("http://example.com/path/to/resource");
    }

    @Test
    void testNormalizeWithEncodeBody() {
        // Given
        String url = "http://example.com//path//to//资源";

        // When
        String result = URLUtil.normalize(url, true, false);

        // Then
        assertThat(result).isEqualTo("http://example.com/path/to/%E8%B5%84%E6%BA%90");
    }

    @Test
    void testNormalizeWithEncodeParam() {
        // Given
        String url = "http://example.com//path//to//resource?param=值";

        // When
        String result = URLUtil.normalize(url, false, true);

        // Then
        assertThat(result).isEqualTo("http://example.com/path/to/resource?param=%E5%80%BC");
    }

    @Test
    void testNormalizeWithBothEncodings() {
        // Given
        String url = "http://example.com//path//to//资源?param=值";

        // When
        String result = URLUtil.normalize(url, true, true);

        // Then
        assertThat(result).isEqualTo("http://example.com/path/to/%E8%B5%84%E6%BA%90?param=%E5%80%BC");
    }

    @Test
    void testNormalizeWithNoProtocol() {
        // Given
        String url = "//path//to//resource";

        // When
        String result = URLUtil.normalize(url);

        // Then
        assertThat(result).isEqualTo("http://path/to/resource");
    }

    @Test
    void testNormalizeWithEmptyUrl() {
        // Given
        String url = "";

        // When
        String result = URLUtil.normalize(url);

        // Then
        assertThat(result).isEqualTo("");
    }

    // Add more test methods for other edge cases and scenarios
}
