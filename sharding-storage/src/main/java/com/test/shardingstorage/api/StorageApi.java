package com.test.shardingstorage.api;

import com.alibaba.fastjson.JSONObject;
import com.test.shardingstorage.service.DsStorageService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/storage/")
public class StorageApi {

    @Autowired
    DsStorageService storageService;

    @PostMapping("deduct")
    public JSONObject deduct(@RequestBody JSONObject params) {

        log.info("全局事务id ：" + RootContext.getXID());

        JSONObject result = new JSONObject(true);
        result.put("code", -1);
        result.put("msg", "");
        String commodityCode = params.getString("commodity_code");
        int count = params.getInteger("count");

        boolean flag = storageService.deduct(commodityCode, count);
        if (flag) {
            log.info("扣库存成功");
            result.put("code", 0);
            result.put("msg", "操作成功");
            return result;
        }
        log.info("扣库存失败");
        result.put("msg", "扣库存失败");
        return result;
    }

}
