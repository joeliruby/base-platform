package com.matariky.redis;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.Message;

@SpringBootTest
public class RedisKeyListenerTest {

    @InjectMocks
    private RedisKeyListener rediskeylistener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOnMessage() {
        // Given
        Message message = mock(Message.class);
        byte[] pattern = "testPattern".getBytes();
        when(message.getBody()).thenReturn("testKey".getBytes());

        // When
        rediskeylistener.onMessage(message, pattern);

        // Then
        // Since the method prints to console, you can verify the behavior by checking
        // the console output manually
        // or use a logging framework to capture the output for assertions.
    }

    // Add more test methods for other methods in RedisKeyListener if any
}
