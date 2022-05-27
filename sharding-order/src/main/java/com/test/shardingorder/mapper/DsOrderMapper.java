package com.test.shardingorder.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.shardingorder.entity.DsOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DsOrderMapper extends BaseMapper<DsOrder> {
}
