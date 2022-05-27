package com.test.shardingorder.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("ds_order")
public class DsOrder implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long userId;

    private String orderNo;

    private int orderOrigin;

    private int orderType;

    private int bizType;

    private int payWay;

    private String outTradeNo;

    private Date payTime;

    private BigDecimal orderMoney;

    private String description;

    private int state;

    private Date createDate;

    private Date createTime;

}
