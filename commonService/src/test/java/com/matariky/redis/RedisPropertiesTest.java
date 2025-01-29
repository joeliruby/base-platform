package com.matariky.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisPropertiesTest {

    @InjectMocks
    private RedisProperties redisProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHost() {
        // Given
        redisProperties.setHost("localhost");

        // When
        String host = redisProperties.getHost();

        // Then
        assertThat(host).isEqualTo("localhost");
    }

    @Test
    void testGetPassword() {
        // Given
        redisProperties.setPassword("password123");

        // When
        String password = redisProperties.getPassword();

        // Then
        assertThat(password).isEqualTo("password123");
    }

    @Test
    void testGetPort() {
        // Given
        redisProperties.setPort(6379);

        // When
        int port = redisProperties.getPort();

        // Then
        assertThat(port).isEqualTo(6379);
    }

    @Test
    void testGetDb() {
        // Given
        redisProperties.setDb(1);

        // When
        int db = redisProperties.getDb();

        // Then
        assertThat(db).isEqualTo(1);
    }

    // Add more test methods for other methods in RedisProperties if needed
}
