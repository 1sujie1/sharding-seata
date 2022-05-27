package com.test.shardingstorage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.shardingstorage.entity.DsStorage;
import com.test.shardingstorage.mapper.DsStorageMapper;
import com.test.shardingstorage.service.DsStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DsStorageServiceImpl extends ServiceImpl<DsStorageMapper, DsStorage> implements DsStorageService {

    @Transactional
    @Override
    public boolean deduct(String commodityCode, int count) {
        UpdateWrapper wrapper = new UpdateWrapper<DsStorage>();
        wrapper.setSql("count=count-" + count);
        wrapper.eq("commodity_code", commodityCode);
        boolean flag = this.update(wrapper);
        return flag;
    }

}
