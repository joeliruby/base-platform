package com.matariky.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.matariky.redis.RedisUtils;

@SpringBootTest
public class NoUtilTest {

    @InjectMocks
    private NoUtil noutil;

    @Mock
    private RedisUtils redisUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateInoutSerialNo() {
        // Given
        when(redisUtils.increment(anyString())).thenReturn(1L);

        // When
        String result = NoUtil.generateInoutSerialNo(1);

        // Then
        assertNotNull(result);
        assertTrue(result.startsWith("TSI"));
        verify(redisUtils).increment(anyString());
        verify(redisUtils).expire(anyString(), eq(1L), eq(TimeUnit.DAYS));
    }

    @Test
    void testGenerateInventorySerialNo() {
        // Given
        when(redisUtils.increment(anyString())).thenReturn(1L);

        // When
        String result = NoUtil.generateInventorySerialNo();

        // Then
        assertNotNull(result);
        assertTrue(result.startsWith("TIN"));
        verify(redisUtils).increment(anyString());
        verify(redisUtils).expire(anyString(), eq(1L), eq(TimeUnit.DAYS));
    }

    @Test
    void testGenerateNoWithRandom() {
        // Given
        when(redisUtils.increment(anyString())).thenReturn(1L);

        // When
        String result = NoUtil.generateNo("TEST", "test_biz", 9, true);

        // Then
        assertNotNull(result);
        assertTrue(result.startsWith("TEST"));
        assertEquals(19, result.length()); // prefix (4) + date (8) + seq (9) + random (2)
        verify(redisUtils).increment(anyString());
        verify(redisUtils).expire(anyString(), eq(1L), eq(TimeUnit.DAYS));
    }

    @Test
    void testGenerateNoWithoutRandom() {
        // Given
        when(redisUtils.increment(anyString())).thenReturn(1L);

        // When
        String result = NoUtil.generateNo("TEST", "test_biz", 9, false);

        // Then
        assertNotNull(result);
        assertTrue(result.startsWith("TEST"));
        assertEquals(17, result.length()); // prefix (4) + date (8) + seq (9)
        verify(redisUtils).increment(anyString());
        verify(redisUtils).expire(anyString(), eq(1L), eq(TimeUnit.DAYS));
    }
}
