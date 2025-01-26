package com.matariky.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String keyCreated = new String(message.getBody());
        System.out.println("Key created: " + keyCreated);
        // 处理key的逻辑
    }
}
