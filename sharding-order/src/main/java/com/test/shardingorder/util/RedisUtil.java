package com.test.shardingorder.util;


import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource(name = "idRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate;


    // Key（键），简单的key-value操作

    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 实现命令：SET key value，设置一个key-value（将int值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, int value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value，设置一个key-value（将int值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, int value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public int getInteger(String key) {
        if (redisTemplate.opsForValue().get(key) == null) {
            return 0;
        } else {
            return Integer.parseInt(redisTemplate.opsForValue().get(key).toString());
        }
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public long getLong(String key) {
        if (redisTemplate.opsForValue().get(key) == null) {
            return 0;
        } else {
            return Long.parseLong(redisTemplate.opsForValue().get(key).toString());
        }
    }


    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        if (redisTemplate.opsForValue().get(key) == null) {
            return null;
        } else {
            return redisTemplate.opsForValue().get(key).toString();
        }
    }


    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }


    /**
     * @param key
     * @return
     */
    public void hsetAll(String key, HashMap<String, String> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public int hgetInteger(String key, String field) {
        Object obj = redisTemplate.opsForHash().get(key, field);
        if (obj != null) {
            return Integer.parseInt(redisTemplate.opsForHash().get(key, field).toString());
        } else {
            return 0;
        }
    }


    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public long hgetLong(String key, String field) {
        Object obj = redisTemplate.opsForHash().get(key, field);
        if (obj != null) {
            return Long.parseLong(redisTemplate.opsForHash().get(key, field).toString());
        } else {
            return 0;
        }
    }


    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpushAllJSON(String key, List<JSONObject> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    public long lpushAll(String key, List<Object> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public String lpopStr(String key) {
        return (String) redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public Object lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param list
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpushAllJSON(String key, List<String> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    public long rpushAll(String key, List<Object> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    /**
     * 实现命令：SADD
     *
     * @param key
     * @param item
     * @return
     */
    public long sadd(String key, String item) {
        return redisTemplate.opsForSet().add(key, item);
    }

    /**
     * 实现命令：SADD
     *
     * @param key
     * @param set
     * @return
     */
    public long saddAll(String key, Set<String> set) {
        return redisTemplate.opsForSet().add(key, set);
    }

    /**
     * 查询元素是否属于set
     *
     * @param key
     * @param item
     * @return
     */
    public boolean sIsMember(String key, String item) {
        return redisTemplate.opsForSet().isMember(key, item);
    }

    /**
     * 实现命令：读取list 前X 元素
     *
     * @param key
     */
    public List<Object> getList(String key, int index, int end) {
        List<Object> range = redisTemplate.opsForList().range(key, index, end);
        int size = range.size();
        redisTemplate.opsForList().trim(key, size, -1);
        return range;
    }

    /**
     * 删除数组
     *
     * @param key
     */
    public void deleteList(String key, int size) {
        redisTemplate.opsForList().trim(key, size, -1);
    }

    /**
     * 获取集合大小
     *
     * @return
     */
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

}
