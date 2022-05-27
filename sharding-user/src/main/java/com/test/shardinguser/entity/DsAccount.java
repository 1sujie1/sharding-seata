package com.test.shardinguser.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("ds_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DsAccount {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userId;

    private int money;

}
