package com.matariky.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {

    /**
     *  Address 
     */
    private String host;

    /**
     *  Password 
     */
    private String password;

    /**
     * 端口
     */
    private int port;

    /**
     * 库号
     */
    private int db;
}
