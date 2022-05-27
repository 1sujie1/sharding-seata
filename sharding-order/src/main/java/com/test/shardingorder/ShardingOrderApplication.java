package com.test.shardingorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan({"com.test.shardingorder.mapper"})
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ShardingOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingOrderApplication.class, args);
    }

}
