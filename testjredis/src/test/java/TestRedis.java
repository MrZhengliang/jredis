import com.test.redis.MyRedisTemplate;
import com.test.redis.RedisConstant;
import com.test.redis.pojo.UserDO;
import com.test.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xxx on 2017/6/7.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestRedis {


//    @Autowired
//    private MyRedisTemplate myRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisService redisService;

    private static final String KEY_SPLIT = ":"; //用于隔开缓存前缀与缓存键值

    @Test
    public void createUser(){
        UserDO user = new UserDO();
        user.setCreateDate(new Date());
        user.setLevel(1);
        user.setName("韩梅梅");
        user.setTelephone("1709212211");
        user.setPassword("pwd");

        ValueOperations<String, Serializable> valueOper = redisTemplate.opsForValue();
        valueOper.set(user.getObjectKey(), user,30, TimeUnit.SECONDS);
        UserDO userDO = (UserDO) valueOper.get(user.getObjectKey());
        System.out.println("获取缓存的数据-->user info : "+userDO.toString());
//        redisTemplate.delete(user.getObjectKey());



//        System.out.println("获取缓存的数据1:"+myRedisTemplate.get(RedisConstant.USER_FORWARD_CACHE_PREFIXFIX,"user1"));
    }
}
