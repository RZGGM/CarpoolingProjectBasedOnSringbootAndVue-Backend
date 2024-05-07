package com.gzasc.onlinecarhailing.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

// 解决直接查看redis中文不显示（没解决2024-04-20 20:03:27）
// 应该是只有在使用redisTemplate才会生效，（2024-04-30 19:31:54），具体看RedisConnectionFactory
@Configuration
public class RedisConfig {
    //     下面是基于API的缓存数据类型的格式化
    // 将方法的返回值声明为一个名字为redisTemplate的bean。
    // 为bean命名是因为也许其它的一些缓存的key和value的类型不是String，String。
    @Bean(name = "redisTemplate")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
//        System.out.println("\n Redis开启序列化");
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // json序列化配置
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        template.setConnectionFactory(factory);
        // key序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // value序列化
        template.setValueSerializer(genericFastJsonRedisSerializer);
        // value hashmap序列化
        template.setHashValueSerializer(genericFastJsonRedisSerializer);
        // key haspmap序列化
        template.setHashKeySerializer(genericFastJsonRedisSerializer);
        //
        return template;
    }


    //    下面是基于注解的缓存数据格式化
    @Bean(name = "cacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {

//默认的redisCahce配置，此处我们没用，而是自己配置
        //RedisCacheConfiguration.defaultCacheConfig();
        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // json序列化配置
        GenericFastJsonRedisSerializer genericFastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
 /*
         定制"缓存数据序列化方式"及"时效"
         */
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        genericFastJsonRedisSerializer))
                .disableCachingNullValues();
        //根据redis缓存配置和reid连接工厂生成redis缓存管理器
        return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();

    }


}
