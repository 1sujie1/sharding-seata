package com.test.shardingorder.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.test.shardingorder.entity.DsOrder;

public interface DsOrderService extends IService<DsOrder> {

    JSONObject create(DsOrder order);

}
