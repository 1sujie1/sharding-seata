package com.test.shardingorder.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * eth配置
 */

@Configuration
@Getter
@Setter
public class RedisConfig {

    @Value("${redis.id-generator.host}")
    private String hostName;
    @Value("${redis.id-generator.port}")
    private int port;
    @Value("${redis.id-generator.password}")
    private String password;
    @Value("${redis.id-generator.database}")
    private int index;

}