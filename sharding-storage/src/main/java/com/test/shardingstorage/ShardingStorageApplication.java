package com.test.shardingstorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan({"com.test.shardingstorage.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ShardingStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingStorageApplication.class, args);
    }

}
