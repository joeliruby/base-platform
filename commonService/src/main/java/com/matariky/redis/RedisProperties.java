package com.matariky.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: bo.chen
 * @create: 2023/9/6 19:26
 **/
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {

    /**
     * 地址
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
