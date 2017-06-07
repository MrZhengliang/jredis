package com.test.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by xxx on 2017/6/7.
 */
@Service
public class RedisService {
    @Autowired
    RedisTemplate<?, ?> redisTemplate;
    /**获得客户端列表 */
    public List<?> getClients(){
        return redisTemplate.getClientList();
    }
    /**设置有超时时间的KV */
    public Long set(String key, String value, long seconds) {
        return redisTemplate.execute((RedisCallback<Long>) c -> {
            c.set(key.getBytes(), value.getBytes());
            c.expire(key.getBytes(), seconds);
            return 1L;
        });
    }
    /**
     *存入不会超时的KV
     */
    public Long set(String key, String value) {
        return redisTemplate.execute((RedisCallback<Long>)c -> {
            c.set(key.getBytes(), value.getBytes());
            return 1L;
        });
    }
    /**
     * redis数据库条数
     */
    public Long dbSize() {
        return redisTemplate.execute((RedisCallback<Long>)c -> c.dbSize());
    }

    public String ping() {
        return redisTemplate.execute((RedisCallback<String>)c -> c.ping());
    }
    /**
     * 删除所有指定数据库的数据
     */
    public long flushDB() {
        return redisTemplate.execute((RedisCallback<Long>)c -> {
            c.flushDb();
            return 1L;
        });
    }
    /**判断redis数据库是否有对应的key*/
    public boolean exist(String key){
        return redisTemplate.execute((RedisCallback<Boolean>)c->c.exists(key.getBytes()));
    }
    /**获得redis数据库所有的key*/
    public Set<String> keys(String pattern){
        return redisTemplate.execute((RedisCallback<Set>) c -> c.keys(pattern.getBytes()).stream().map(this::getUTF).collect(Collectors.toSet()));
    }
    private String getUTF(byte[] data){
        try {
            return new String(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
