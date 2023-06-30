package com.kestrel.weddingbookkeeper.common.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfiguration {

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.maxHeap}")
    private String maxHeap;

    private RedisServer redisServer;

    @PostConstruct
    public void start() {
        final String configLine = "maxmemory " + maxHeap;
        redisServer = RedisServer.builder()
                .port(port)
                .setting(configLine)
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stop() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
