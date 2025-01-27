package com.matariky.redis.redisson;

import com.matariky.redis.RedisProperties;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ RedissonProperties.class, RedisProperties.class })
@ConditionalOnProperty(value = { "redis.redisson.enable" }, havingValue = "true")
public class RedissonAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    public RedissonClient getRedissonClient(RedissonProperties redissonProperties, RedisProperties redisProperties) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setDatabase(redissonProperties.getDatabase());
        singleServerConfig.setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());
        if (StringUtils.isNotEmpty(redisProperties.getPassword())) {
            singleServerConfig.setPassword(redisProperties.getPassword());
        }
        return Redisson.create(config);
    }

}
