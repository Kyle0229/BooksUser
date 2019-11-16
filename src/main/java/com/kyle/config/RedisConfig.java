package com.kyle.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean(name="redisConnectionFactory")
    public JedisConnectionFactory getJedisConnetionFactory(){
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        return connectionFactory;
    }
    @Bean
    public RedisTemplate<String,Object> getRedisTemplate(
            @Qualifier("redisConnectionFactory")JedisConnectionFactory redisConnectionFactory

    ){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        使用StringRedisSerializer序列化只能传送string类型数据到redis中
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        传任意类型到redis中需要自定义序列化转换器
        redisTemplate.setKeySerializer(new RedisUtilsSerializer());
        redisTemplate.setValueSerializer(new RedisUtilsSerializer());

        return redisTemplate;
    }
}
