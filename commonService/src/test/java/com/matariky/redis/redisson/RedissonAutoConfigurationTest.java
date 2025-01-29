package com.matariky.redis.redisson;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import com.matariky.redis.RedisProperties;
import org.mockito.Mock;
import org.redisson.api.RedissonClient;

@SpringBootTest
public class RedissonAutoConfigurationTest {

    @InjectMocks
    private RedissonAutoConfiguration redissonAutoConfiguration;

    @Mock
    private RedissonProperties redissonProperties;

    @Mock
    private RedisProperties redisProperties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRedissonClient() {
        // Given
        when(redissonProperties.getDatabase()).thenReturn(0);
        when(redisProperties.getHost()).thenReturn("localhost");
        when(redisProperties.getPort()).thenReturn(6379);
        when(redisProperties.getPassword()).thenReturn(null);

        // When
        RedissonClient redissonClient = redissonAutoConfiguration.getRedissonClient(redissonProperties,
                redisProperties);

        // Then
        assertThat(redissonClient).isNotNull();
        verify(redissonProperties).getDatabase();
        verify(redisProperties).getHost();
        verify(redisProperties).getPort();
        verify(redisProperties).getPassword();
    }

    @Test
    void testGetRedissonClientWithPassword() {
        // Given
        when(redissonProperties.getDatabase()).thenReturn(0);
        when(redisProperties.getHost()).thenReturn("localhost");
        when(redisProperties.getPort()).thenReturn(6379);
        when(redisProperties.getPassword()).thenReturn("password");

        // When
        RedissonClient redissonClient = redissonAutoConfiguration.getRedissonClient(redissonProperties,
                redisProperties);

        // Then
        assertThat(redissonClient).isNotNull();
        verify(redissonProperties).getDatabase();
        verify(redisProperties).getHost();
        verify(redisProperties).getPort();
        verify(redisProperties).getPassword();
    }

    // Add more test methods for other methods in RedissonAutoConfiguration
}
