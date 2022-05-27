package com.test.shardingorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.shardingorder.entity.DsOrder;
import com.test.shardingorder.mapper.DsOrderMapper;
import com.test.shardingorder.service.AccountService;
import com.test.shardingorder.service.DsOrderService;
import com.test.shardingorder.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DsOrderServiceImpl extends ServiceImpl<DsOrderMapper, DsOrder> implements DsOrderService {

    @Autowired
    AccountService accountService;

    @Autowired
    StorageService storageService;

    @Transactional
    @ShardingTransactionType(value = TransactionType.BASE)
//    @GlobalTransactional(timeoutMills = 300000, name = "buy-tx")
    @Override
    public JSONObject create(DsOrder order) {
//        TransactionTypeHolder.set(TransactionType.BASE);

        log.info("全局事务id ：" + RootContext.getXID());

        this.save(order);
        log.info("保存订单完成");

        //扣款
        JSONObject accountParam = new JSONObject();
        accountParam.put("user_id", 1);
        accountParam.put("money", order.getOrderMoney().intValue());
        accountService.consumer(accountParam);

        //扣库存
        JSONObject storageParam = new JSONObject();
        storageParam.put("commodity_code", "goods1");
        storageParam.put("count", 1);
        storageService.deduct(storageParam);

//        System.out.println(9 / 0);

        JSONObject result = new JSONObject();
        result.put("code", 0);
        result.put("msg", "success");
        return result;
    }

}
