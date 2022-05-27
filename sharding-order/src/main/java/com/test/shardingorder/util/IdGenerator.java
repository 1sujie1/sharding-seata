package com.test.shardingorder.util;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.test.shardingorder.config.IdGeneratorCommConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator implements IdentifierGenerator {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Number nextId(Object entity) {
        long id = Long.parseLong(redisUtil.lpop(IdGeneratorCommConfig.global_id_list_key).toString());

        return id;
    }

}
