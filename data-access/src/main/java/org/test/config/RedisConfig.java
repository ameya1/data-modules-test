package org.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.model.params.RedisParams;
import redis.clients.jedis.JedisPooled;

@Configuration
public class RedisConfig {

    @Autowired
    RedisParams redisParams;

    @Bean
    public JedisPooled jedisClient() {
        return new JedisPooled(redisParams.getUrl(), redisParams.getPort());
    }
}
