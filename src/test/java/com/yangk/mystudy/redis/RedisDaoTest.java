package com.yangk.mystudy.redis;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisDaoTest {


    @Autowired
    JedisCluster jedisCluster;

/*    @Autowired
    RedisDao redisDao;

    @Test
    public void testRedis(){
        redisDao.setKey("name","forezp");
        redisDao.setKey("age","11");
        log.info(redisDao.getValue("name"));
        log.info(redisDao.getValue("age"));
    }*/

    @Test
    public void testJedisCluster() {
        jedisCluster.set("test", "test");
        System.out.println(jedisCluster.get("test"));
    }

    public static void main(String[] args) {
        long objectSize = ObjectSizeCalculator.getObjectSize(new Object());
        System.out.println(objectSize);
        long l = Runtime.getRuntime().maxMemory();
        System.out.println(l);


    }

}