package com.matariky.redis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LuaScriptTest {

    @InjectMocks
    private LuaScript luascript;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveReaderTask() {
        // Given
        String expectedScript = "if ARGV[2] ~= '0' then local array = {} for v in string.gmatch(ARGV[2], '%w+') do redis.call('SADD', KEYS[1], '\"'..v..'\"') end redis.call('expire', KEYS[1], ARGV[1]) end for i = 2, #(KEYS) do redis.call('SETEX', KEYS[i], ARGV[1], ARGV[i + 1]) end return 1";

        // When
        String actualScript = LuaScript.SAVE_READER_TASK;

        // Then
        assertEquals(expectedScript, actualScript);
    }

    @Test
    void testSaveReaderResult1() {
        // Given
        String expectedScript = "redis.call('SADD', KEYS[1], ARGV[1]) redis.call('expire', KEYS[1], ARGV[3]) for v in string.gmatch(ARGV[1], '%w+') do redis.call('HSET', KEYS[2], v, ARGV[2]) end  redis.call('expire', KEYS[2], ARGV[3]) redis.call('expire', KEYS[3], ARGV[3]) return 1";

        // When
        String actualScript = LuaScript.SAVE_READER_RESULT_1;

        // Then
        assertEquals(expectedScript, actualScript);
    }

    @Test
    void testSaveReaderResult2() {
        // Given
        String expectedScript = "for v in string.gmatch(ARGV[1], '%w+') do redis.call('HSET', KEYS[1], v, ARGV[2]) end redis.call('expire', KEYS[1], ARGV[3]) return 1";

        // When
        String actualScript = LuaScript.SAVE_READER_RESULT_2;

        // Then
        assertEquals(expectedScript, actualScript);
    }

    // Add more test methods for other methods in LuaScript if needed
}
