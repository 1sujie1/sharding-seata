package com.test.shardingorder.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(value = "sharding-storage-service")
public interface StorageService {

    @LoadBalanced
    @PostMapping("/api/storage/deduct")
    JSONObject deduct(@RequestBody JSONObject params);

}
