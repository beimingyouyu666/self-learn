//package com.yangk.selflearn.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Repository;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description TODO
// * @Author yangkun
// * @Date 2019/8/2
// * @Version 1.0
// */
//@Repository
//public class RedisDao {
//
//    @Autowired
//    private StringRedisTemplate template;
//
//    public  void setKey(String key,String value){
//        ValueOperations<String, String> ops = template.opsForValue();
//        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
//    }
//
//    public String getValue(String key){
//        ValueOperations<String, String> ops = this.template.opsForValue();
//        return ops.get(key);
//    }
//}
//
//
