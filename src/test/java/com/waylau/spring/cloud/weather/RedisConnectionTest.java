package com.waylau.spring.cloud.weather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author smmit
 * @date 2018-05-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConnectionTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        stringRedisTemplate.opsForValue().set("foo", "bar");
        String test = stringRedisTemplate.opsForValue().get("foo");
        System.out.println(test);
    }
}
