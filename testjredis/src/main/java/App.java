import com.test.redis.MyRedisTemplate;
import com.test.redis.RedisConstant;
import com.test.redis.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.JedisCluster;

/**
 * Created by xxx on 2017/6/6.
 */
@ComponentScan(basePackages={"com.test.redis"})
@EnableAutoConfiguration
@SpringBootApplication
public class App {


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }




}
