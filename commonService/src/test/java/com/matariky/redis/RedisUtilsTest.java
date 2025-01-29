package com.matariky.redis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisUtilsTest {

    @InjectMocks
    private RedisUtils redisUtils;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
    }

    @Test
    void testSetWithExpire() {
        // Given
        String key = "testKey";
        String value = "testValue";
        long expire = 60L;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        // When
        redisUtils.set(key, value, expire, timeUnit);

        // Then
        verify(valueOperations).set(key, value, expire, timeUnit);
    }

    @Test
    void testSetWithoutExpire() {
        // Given
        String key = "testKey";
        String value = "testValue";

        // When
        redisUtils.set(key, value);

        // Then
        verify(valueOperations).set(key, value);
        verify(redisTemplate).expire(key, RedisUtils.DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    @Test
    void testGetWithExpire() {
        // Given
        String key = "testKey";
        String expectedValue = "testValue";
        long expire = 60L;
        when(valueOperations.get(key)).thenReturn(expectedValue);

        // When
        String actualValue = redisUtils.get(key, expire);

        // Then
        assertEquals(expectedValue, actualValue);
        verify(redisTemplate).expire(key, expire, TimeUnit.SECONDS);
    }

    @Test
    void testGetWithoutExpire() {
        // Given
        String key = "testKey";
        String expectedValue = "testValue";
        when(valueOperations.get(key)).thenReturn(expectedValue);

        // When
        String actualValue = redisUtils.get(key);

        // Then
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testHasKey() {
        // Given
        String key = "testKey";
        when(redisTemplate.hasKey(key)).thenReturn(true);

        // When
        boolean result = redisUtils.hasKey(key);

        // Then
        assertTrue(result);
    }

    @Test
    void testDelete() {
        // Given
        String key = "testKey";

        // When
        redisUtils.delete(key);

        // Then
        verify(redisTemplate).delete(key);
    }

    // Add more test methods for other methods in RedisUtils
}
