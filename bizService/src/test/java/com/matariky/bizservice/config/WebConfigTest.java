package com.matariky.bizservice.config;

import static org.assertj.core.api.Assertions.assertThat;

import javax.servlet.MultipartConfigElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.unit.DataSize;

@SpringBootTest
public class WebConfigTest {

    @InjectMocks
    private WebConfig webconfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMultipartConfigElement() {
        // Given
        // Initialize your test data and mocks here

        // When
        MultipartConfigElement result = webconfig.multipartConfigElement();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getMaxFileSize()).isEqualTo(DataSize.ofBytes(2000000000).toBytes());
        assertThat(result.getMaxRequestSize()).isEqualTo(DataSize.ofBytes(2000000000).toBytes());
    }

    // Add more test methods for other methods in WebConfig
}
