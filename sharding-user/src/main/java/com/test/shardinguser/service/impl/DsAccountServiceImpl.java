package com.test.shardinguser.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.shardinguser.entity.DsAccount;
import com.test.shardinguser.mapper.DsAccountMapper;
import com.test.shardinguser.service.DsAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DsAccountServiceImpl extends ServiceImpl<DsAccountMapper, DsAccount> implements DsAccountService {

    @Transactional
    @Override
    public int reduceMoney(int userId, int money) {
        UpdateWrapper wrapper = new UpdateWrapper<DsAccount>();
        wrapper.setSql("money=money-" + money);
        wrapper.eq("id", userId);
        int result = this.update(wrapper) ? 1 : 0;
        return result;
    }

}
