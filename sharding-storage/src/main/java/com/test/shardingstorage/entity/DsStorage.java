package com.test.shardingstorage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("ds_storage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsStorage implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String commodityCode;

    private int count;

}
