package com.test.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.util.Pool;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 *  JedisClusterConfig
 * Created by xx on 2017/6/6.
 */
//@Configuration
public class JedisClusterConfig {

    private Logger logger = LoggerFactory.getLogger(JedisClusterConfig.class);

//    @Autowired
//    private RedisProperties redisProperties;

//    @Bean
//    public JedisCluster getJedisCluster(){
//        String[] serverArray = redisProperties.getClusterNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
//
//        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
//
//        for (String ipPort : serverArray) {
//            String[] ipPortPair = ipPort.split(":");
//            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
//        }
//
//        return new JedisCluster(nodes,redisProperties.getCommandTimeout());
//    }

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisCluster jedisClusterFactory() {
        logger.info("JedisCluster创建！！");
        logger.info("redis地址：" + host + ":" + port);
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisClusterNodes.add(new HostAndPort(host, port));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, jedisPoolConfig);
        return jedisCluster;
    }


}
