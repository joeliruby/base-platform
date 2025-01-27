package com.matariky.redis;

import com.github.pagehelper.util.StringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties({ RedisProperties.class })
public class RedisConfig {

	@Bean
	public RedisConnectionFactory redisConnectionFactory(RedisProperties redisProperties) {
		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setHostName(redisProperties.getHost());
		jcf.setPort(redisProperties.getPort());
		if (!StringUtil.isEmpty(redisProperties.getPassword())) {
			jcf.setPassword(redisProperties.getPassword());
		} else {
			jcf.setPassword("");
		}
		jcf.setDatabase(redisProperties.getDb());
		return jcf;
	}

	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
		// Configuration序列化Value的实例化 Object
		redisTemplate.setValueSerializer(serializer);
		redisTemplate.setHashValueSerializer(serializer);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();

		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
				.disableCachingNullValues();
		return RedisCacheManager.builder(factory).cacheDefaults(config).build();
	}
}
