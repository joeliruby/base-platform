package com.matariky.redis;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyListenerConfig {
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;

    private

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("__keyevent@0__:set");
    }

    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    private MessageListener messageListener;

    @PostConstruct
    public void init() {
        redisMessageListenerContainer.addMessageListener(messageListener, topic());
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter(new RedisKeyListener());
    }
    
    

	@Bean
	RedisMessageListenerContainer redisContainer() {
	    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	    container.setConnectionFactory(redisConnectionFactory);
	    return container;
	}


}
