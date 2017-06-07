package com.test.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by xxx on 2017/6/7.
 */
@Controller
public class RedisController {

//    @Autowired
    private MyRedisTemplate myRedisTemplate;


    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    @RequestMapping("/testJedisCluster")
    public String testJedisCluster(@RequestParam("username") String username,Map<String,Object> map){
        System.out.println("测试redis...");
        myRedisTemplate.set(RedisConstant.USER_FORWARD_CACHE_PREFIX,"user1",username);
        System.out.println("获取缓存的数据1:"+myRedisTemplate.get(RedisConstant.USER_FORWARD_CACHE_PREFIX,"user1"));


        myRedisTemplate.setWithExpireTime(RedisConstant.USER_FORWARD_CACHE_PREFIX,"user2",username);
        System.out.println("获取缓存的数据2:"+myRedisTemplate.get(RedisConstant.USER_FORWARD_CACHE_PREFIX,"user2"));

        map.put("username",myRedisTemplate.get(RedisConstant.USER_FORWARD_CACHE_PREFIX,"user2"));
        return "index";
    }
}
