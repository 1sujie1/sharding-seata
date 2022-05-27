package com.test.shardingorder.api;

import com.alibaba.fastjson.JSONObject;
import com.test.shardingorder.entity.DsOrder;
import com.test.shardingorder.service.DsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/api/order/")
public class OrderController {

    @Autowired
    DsOrderService orderService;

    @PostMapping("add")
    public JSONObject add(@RequestBody JSONObject params) {
        Long userId = params.getLong("user_id");
        String orderNo = params.getString("order_no");
        BigDecimal money = params.getBigDecimal("money");
        String description = params.getString("description");
        Date now = new Date();
        DsOrder order = new DsOrder(null, userId, orderNo, 1, 1, 1,
                3, "", now, money, description, 0, now, now);

        return orderService.create(order);
    }

}
