package com.test.shardingorder.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 */

@Configuration
public class IdGeneratorRedisTemplate {

    @Autowired
    RedisBaseConfig baseConfig;

    @Autowired
    RedisConfig config;

    @Bean("idRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisTemplate<String, Object> temple = new RedisTemplate<String, Object>();
        temple.setConnectionFactory(buildFactory());
        RedisSerializer stringSerializer = new StringRedisSerializer();

        temple.setKeySerializer(stringSerializer);//key序列化
        temple.setValueSerializer(jackson2JsonRedisSerializer);//value序列化
        temple.setHashKeySerializer(stringSerializer);//Hash key序列化
        temple.setHashValueSerializer(jackson2JsonRedisSerializer);//Hash value序列化
        return temple;
    }

    /**
     * 使用redis连接池构造连接工厂
     *
     * @return
     */
    public RedisConnectionFactory buildFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

        configuration.setHostName(config.getHostName());
        configuration.setPort(config.getPort());
        configuration.setPassword(config.getPassword());
        configuration.setDatabase(config.getIndex());

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder configurationBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        configurationBuilder.poolConfig(buildPoolCofig());

        JedisClientConfiguration jedisClientConfiguration = configurationBuilder.build();


        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);
//        factory.setTimeout(baseConfig.getTimeout());
        //初始化连接池
        factory.afterPropertiesSet();

        return factory;
    }

    //初始化连接池配置
    public JedisPoolConfig buildPoolCofig() {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxTotal(baseConfig.getMaxTotal());
        poolCofig.setMinIdle(baseConfig.getMinIdle());
        poolCofig.setMaxIdle(baseConfig.getMaxIdle());
        poolCofig.setMaxWaitMillis(baseConfig.getMaxWaitMillis());
        poolCofig.setTestOnBorrow(false);
        return poolCofig;
    }


}