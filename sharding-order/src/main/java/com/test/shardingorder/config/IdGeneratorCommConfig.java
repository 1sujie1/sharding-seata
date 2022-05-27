package com.test.shardingorder.config;

/**
 * ID生成器公共配置
 */
public class IdGeneratorCommConfig {

    //redis分布式idkey
    public static String global_id_list_key = "global_id_list";

    //id生成器默认每次生成大小
    public static int default_size = 10000;

    //size临界值  队列中低于临界值需补充新id
    public static int min_size = 2000;

}
