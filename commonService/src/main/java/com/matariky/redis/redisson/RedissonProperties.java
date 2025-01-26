package com.matariky.redis.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: bo.chen
 * @create: 2023/2/13 17:08
 **/
@ConfigurationProperties(prefix = "redis.redisson")
@Data
public class RedissonProperties{

    /**
     *  Wether 启动
     */
    private boolean enable = false;

    /**
     *  Data 库
     */
    private int database = 0;
}
