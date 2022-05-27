package com.test.shardinguser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.shardinguser.entity.DsAccount;

public interface DsAccountService extends IService<DsAccount> {

    int reduceMoney(int userId, int money);

}
