package com.matariky.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@SpringBootTest
public class RedisKeyListenerConfigTest {

    @InjectMocks
    private RedisKeyListenerConfig redisKeyListenerConfig;

    @Mock
    private RedisConnectionFactory redisConnectionFactory;

    @Mock
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Mock
    private MessageListener messageListener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTopic() {
        // Given
        ChannelTopic expectedTopic = new ChannelTopic("__keyevent@0__:set");

        // When
        ChannelTopic actualTopic = redisKeyListenerConfig.topic();

        // Then
        assertThat(actualTopic.getTopic()).isEqualTo(expectedTopic.getTopic());
    }

    @Test
    void testMessageListenerAdapter() {
        // When
        MessageListenerAdapter messageListenerAdapter = redisKeyListenerConfig.messageListenerAdapter();

        // Then
        assertThat(messageListenerAdapter).isNotNull();
    }

    @Test
    void testRedisContainer() {
        // When
        RedisMessageListenerContainer container = redisKeyListenerConfig.redisContainer();

        // Then
        assertThat(container).isNotNull();
        assertThat(container.getConnectionFactory()).isEqualTo(redisConnectionFactory);
    }

    @Test
    void testInit() {
        // Given
        ChannelTopic topic = redisKeyListenerConfig.topic();

        // When
        redisKeyListenerConfig.init();

        // Then
        verify(redisMessageListenerContainer, times(1)).addMessageListener(messageListener, topic);
    }

    // Add more test methods for other methods in RedisKeyListenerConfig
}
