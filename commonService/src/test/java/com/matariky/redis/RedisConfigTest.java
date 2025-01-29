package com.matariky.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisConfigTest {

    @InjectMocks
    private RedisConfig redisConfig;

    @Mock
    private RedisProperties redisProperties;

    @Mock
    private RedisConnectionFactory redisConnectionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRedisConnectionFactory() {
        // Given
        when(redisProperties.getHost()).thenReturn("localhost");
        when(redisProperties.getPort()).thenReturn(6379);
        when(redisProperties.getPassword()).thenReturn("");
        when(redisProperties.getDb()).thenReturn(0);

        // When
        RedisConnectionFactory factory = redisConfig.redisConnectionFactory(redisProperties);

        // Then
        assertThat(factory).isNotNull();
    }

    @Test
    void testRedisTemplate() {
        // Given
        RedisConnectionFactory factory = mock(RedisConnectionFactory.class);

        // When
        RedisTemplate<String, Object> redisTemplate = redisConfig.<String, Object>redisTemplate(factory);

        // Then
        assertThat(redisTemplate).isNotNull();
        assertThat(redisTemplate.getConnectionFactory()).isEqualTo(factory);
    }

    @Test
    void testCacheManager() {
        // Given
        RedisConnectionFactory factory = mock(RedisConnectionFactory.class);

        // When
        CacheManager cacheManager = redisConfig.cacheManager(factory);

        // Then
        assertThat(cacheManager).isNotNull();
    }

    // Add more test methods for other methods in RedisConfig
}
