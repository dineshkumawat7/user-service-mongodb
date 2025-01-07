package com.spring.app.utils;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisHealthChecker {

    private static final Logger logger = LoggerFactory.getLogger(RedisHealthChecker.class);
    private final RedisConnectionFactory redisConnectionFactory;

    public RedisHealthChecker(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @PostConstruct
    public void checkRedisConnection() {
        try {
            String result = redisConnectionFactory.getConnection().ping();
            if ("PONG".equals(result)) {
                logger.info("Redis is connected successfully!");
            } else {
                logger.warn("Redis is connected, but response is unexpected: {}", result);
            }
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
    }
}
