package com.matariky.redis.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.redisson")
@Data
public class RedissonProperties{

    /**
     *  Wether start
     */
    private boolean enable = false;

    /**
     *  Database
     */
    private int database = 0;
}
