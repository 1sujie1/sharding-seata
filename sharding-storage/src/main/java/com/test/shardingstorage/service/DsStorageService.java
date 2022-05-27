package com.test.shardingstorage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.shardingstorage.entity.DsStorage;

public interface DsStorageService extends IService<DsStorage> {

    boolean deduct(String commodityCode, int count);

}
