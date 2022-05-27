package com.test.shardingorder.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.test.shardingorder.entity.DsOrder;
import com.test.shardingorder.service.DsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/api/ds/order/")
public class DsOrderCongroller {

    @Autowired
    DsOrderService dsOrderService;

    @GetMapping("get")
    public JSONObject get(Long userId) {
        DsOrder order = dsOrderService.getOne(new QueryWrapper<DsOrder>()
                .eq("user_id", userId).last(" limit 1"));
        if (null == order) {
            return null;
        }
        return JSONObject.parseObject(JSON.toJSONString(order));
    }

    @RequestMapping("add")
    public String add(@RequestBody JSONObject params) {
        Long userId = params.getLong("user_id");
        String orderNo = params.getString("order_no");
        BigDecimal money = params.getBigDecimal("money");
        String description = params.getString("description");
        Date now = new Date();
        DsOrder order = new DsOrder(null, userId, orderNo, 1, 1, 1,
                3, "", now, money, description, 0, now, now);
        if (!dsOrderService.save(order)) {
            return "fail";
        }
        return "success";
    }

    //更新不能更新分片键
    @RequestMapping(path = "update")
    public String update(@RequestBody JSONObject params) {
        Long userId = params.getLong("user_id");
        int state = params.getInteger("state");
        DsOrder order = dsOrderService.getOne(new QueryWrapper<DsOrder>()
                .eq("user_id", userId).last(" limit 1")
        );
        if (null == order) {
            return "fail";
        }
        UpdateWrapper<DsOrder> wrapper = new UpdateWrapper<>();
        wrapper.set("state", state);
        wrapper.eq("user_id", userId);
        if (!dsOrderService.update(wrapper)) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping("delete")
    public String delete(@RequestBody JSONObject params) {
        Long userId = params.getLong("user_id");
        QueryWrapper wrapper = new QueryWrapper<DsOrder>();
        wrapper.eq("user_id", userId);
        if (!dsOrderService.remove(wrapper)) {
            return "fail";
        }
        return "success";
    }

    @GetMapping("getOrderList")
    public JSONArray getList(@RequestParam(name = "user_id") Long userId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", userId);
        return JSONArray.parseArray(JSONArray.toJSONString(dsOrderService.list(wrapper)));
    }

}
