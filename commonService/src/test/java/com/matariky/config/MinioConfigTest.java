package com.matariky.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import io.minio.MinioClient;

@SpringBootTest
public class MinioConfigTest {
    @Value("${minio.url}")
    private String url;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    @InjectMocks
    private MinioConfig minioConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMinioClient() {
        // Given

        // Set the values using reflection or any other method

        // When
        MinioClient minioClient = minioConfig.getMinioClient();

        // Then
        assertThat(minioClient).isNotNull();
        assertThat(minioClient.toString()).contains(url);
    }

    // Add more test methods for other methods in MinioConfig if any
}
