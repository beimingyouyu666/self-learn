package com.yangk.mystudy.idempotent;

/**
 * @Description TODO
 * @Author yangkun
 * @Date 2019/8/2
 * @Version 1.0
 */

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

//@Component
public class JedisClusterUtil {
    private static final Logger log = LoggerFactory.getLogger(JedisClusterUtil.class);
    private static final int DEFAULT_SINGLE_EXPIRE_TIME = 5;
    @Autowired
    private JedisCluster jedisCluster;
    public static final int ONE_DAY = 86400;
    public static final int ONE_WEEK = 604800;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SECOND_EXPIRE_TIME = "EX";

    public JedisClusterUtil() {
    }

    public void remove(String... keys) {
        String[] var2 = keys;
        int var3 = keys.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String key = var2[var4];
            this.remove(key);
        }

    }

    public boolean exists(String key) {
        return this.jedisCluster.exists(key);
    }

    public boolean cacheIfNotExists(String key, String value, Integer expireTime) {
        return !this.exists(key) ? this.set(key, value, expireTime) : true;
    }

    public static byte[] objectToByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException var17) {
            log.error("objectToByteArray failed, " + var17);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException var16) {
                    log.error("close objectOutputStream failed, " + var16);
                }
            }

            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException var15) {
                    log.error("close byteArrayOutputStream failed, " + var15);
                }
            }

        }

        return bytes;
    }

    public Long llen(String key) {
        return this.jedisCluster.llen(key);
    }

    public List<String> brpop(int timeout, String key) {
        return this.jedisCluster.brpop(timeout, key);
    }

    public Long lpush(String key, String... string) {
        return this.jedisCluster.lpush(key, string);
    }

    public boolean setnx(String key, int expireTime) {
        String result = this.jedisCluster.set(key, "", "NX", "EX", (long) expireTime);
        return "OK".equals(result);
    }

    public Long hset(String key, String field, String value) {
        return this.jedisCluster.hset(key, field, value);
    }

    public String hget(String key, String field) {
        return this.jedisCluster.hget(key, field);
    }

    public Long hdel(String key, String... field) {
        return this.jedisCluster.hdel(key, field);
    }

    public Long zadd(String key, double score, String member) {
        return this.jedisCluster.zadd(key, score, member);
    }

    public Long sadd(String key, String... members) {
        return this.jedisCluster.sadd(key, members);
    }

    public Set<String> spop(String key, long count) {
        return this.jedisCluster.spop(key, count);
    }

    public Long scard(String key) {
        return this.jedisCluster.scard(key);
    }

    public void removePattern(String pattern) {
        this.jedisCluster.del(pattern);
        log.debug("del key >" + pattern);
    }

    public void remove(String key) {
        if (this.exists(key)) {
            this.jedisCluster.del(key);
            log.debug("del key >" + key);
        } else {
            log.debug("del key >" + key + "not exist");
        }

    }

    /**
     * @deprecated
     */
    @Deprecated
    public TreeSet<String> keys(String pattern) {
        log.debug("Start getting keys...");
        TreeSet<String> keys = new TreeSet();
        Map<String, JedisPool> clusterNodes = this.jedisCluster.getClusterNodes();
        Iterator var4 = clusterNodes.keySet().iterator();

        while (var4.hasNext()) {
            String k = (String) var4.next();
            log.debug("Getting keys from: {}", k);
            JedisPool jp = (JedisPool) clusterNodes.get(k);
            Jedis connection = jp.getResource();

            try {
                keys.addAll(connection.keys(pattern));
            } catch (Exception var12) {
                log.error("Getting keys error: {}", var12);
            } finally {
                log.debug("Connection closed.");
                connection.close();
            }
        }

        log.debug("Keys gotten!");
        return keys;
    }

    public boolean set(String key, String value) {
        boolean result = false;

        try {
            this.jedisCluster.set(key, value);
            result = true;
        } catch (Exception var5) {
            log.error("jedis error:{}", var5);
        }

        return result;
    }

    public boolean set(String key, String value, int expireTime) {
        boolean result = false;

        try {
            this.jedisCluster.setex(key, expireTime, value);
            result = true;
        } catch (Exception var6) {
            log.error("jedis error:{}", var6);
        }

        return result;
    }

    public boolean setIdempotentCache(String key, String requestUUID, int expireTime) {
        String result = this.jedisCluster.set(key, requestUUID, "NX", "EX", (long) expireTime);
        return "OK".equals(result);
    }

    public boolean tryLock(String key, String requestUUID, Long tryLockTime, Integer expireTime) {
        try {
            return this.tryLockUntilTryLockTimeOut(key, requestUUID, tryLockTime, TimeUnit.SECONDS, expireTime);
        } catch (Exception var6) {
            log.warn("获取锁头失败 :{}", var6);
            return false;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public boolean tryLockUntilTrue(String lockKey, String requestId) throws InterruptedException {
        while (true) {
            String result = this.jedisCluster.set(lockKey, requestId, "NX", "EX", 5L);
            if ("OK".equals(result)) {
                return true;
            }

            Thread.sleep(100L);
        }
    }

    private boolean tryLockUntilTryLockTimeOut(String key, String requestUUID, long tryLockTime, TimeUnit unit,
                                               int expireTime) throws InterruptedException {
        return this.getLock(key, requestUUID, tryLockTime, unit, expireTime);
    }

    private Boolean getLock(String key, String requestUUID, long tryLockTime, TimeUnit unit, int expireTime) throws InterruptedException {
        long begin = System.nanoTime();

        do {
            String result = this.jedisCluster.set(key, requestUUID, "NX", "EX", (long) expireTime);
            if ("OK".equals(result)) {
                return true;
            }

            if (tryLockTime == 0L) {
                break;
            }

            Thread.sleep(100L);
        } while (System.nanoTime() - begin < unit.toNanos(tryLockTime));

        return false;
    }

    public boolean unLock(String key, String requestUUID) {
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else " +
                    "return 0 end";
            Object result = this.jedisCluster.eval(script, Collections.singletonList(key),
                    Collections.singletonList(requestUUID));
            return result.equals(1L);
        } catch (Exception var5) {
            log.error("redis 释放锁失败{}", var5);
            return false;
        }
    }

    public static Object byteArrayToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        } catch (Exception var17) {
            log.error("byteArrayToObject failed, " + var17);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException var16) {
                    log.error("close byteArrayInputStream failed, " + var16);
                }
            }

            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException var15) {
                    log.error("close objectInputStream failed, " + var15);
                }
            }

        }

        return obj;
    }

    public String get(String key) {
        return this.jedisCluster.get(key);
    }

    public boolean tryLockNotWait(String key, String requestUUID, String unit, int expireTime) {
        String result = this.jedisCluster.set(key, requestUUID, "NX", unit, (long) expireTime);
        return "OK".equals(result);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Object getObject(String key) {
        byte[] keyByte = objectToByteArray(key);
        return this.jedisCluster.get(keyByte);
    }

    public <T> String setT(String key, T t) {
        return this.jedisCluster.set(key, JSONObject.toJSONString(t));
    }

    public <T> String setexT(String key, int seconds, T t) {
        return this.jedisCluster.setex(key, seconds, JSONObject.toJSONString(t));
    }

    public Long expire(String key, int seconds) {
        return this.jedisCluster.expire(key, seconds);
    }

    public String setex(String key, int seconds, String val) {
        return this.jedisCluster.setex(key, seconds, val);
    }

    public <T> T getT(String key, Class<T> clazz) {
        String val = this.jedisCluster.get(key);
        if (!StringUtils.isEmpty(val)) {
            T t = JSONObject.parseObject(val, clazz);
            return t;
        } else {
            return null;
        }
    }

    public <T> T hgetT(String key, String field, Class<T> clazz) {
        String val = this.jedisCluster.hget(key, field);
        if (!StringUtils.isEmpty(val)) {
            T t = JSONObject.parseObject(val, clazz);
            return t;
        } else {
            return null;
        }
    }

    public <T> List<T> hvalsT(String key, Class<T> clazz) {
        List<String> vals = this.jedisCluster.hvals(key);
        if (vals != null) {
            List<T> result = new ArrayList(vals.size());
            Iterator var5 = vals.iterator();

            while (var5.hasNext()) {
                String val = (String) var5.next();
                if (!StringUtils.isEmpty(val)) {
                    T t = JSONObject.parseObject(val, clazz);
                    result.add(t);
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public Long del(String key) {
        return this.jedisCluster.del(key);
    }

    public <T> String hmsetT(String key, List<T> list, Function<T, String> keyFunction) throws Exception {
        Map<String, String> hMap = new HashMap(list.size());
        Iterator var5 = list.iterator();

        while (var5.hasNext()) {
            T t = (T) var5.next();
            if (t != null) {
                String field = (String) keyFunction.apply(t);
                hMap.put(field, JSONObject.toJSONString(t));
            }
        }

        if (!hMap.isEmpty()) {
            return this.jedisCluster.hmset(key, hMap);
        } else {
            return null;
        }
    }

    public Long incrBy(String key, Long increment) {
        return this.jedisCluster.incrBy(key, increment);
    }

    public Long rpush(String key, String... vals) {
        return this.jedisCluster.rpush(key, vals);
    }

    public String lpop(String key) {
        return this.jedisCluster.lpop(key);
    }

    public Long setIfNotExist(String key, String value) {
        return this.jedisCluster.setnx(key, value);
    }

    public String rpoplpush(String srcKey, String destKey) {
        return this.jedisCluster.rpoplpush(srcKey, destKey);
    }

    public String brpoplpush(String srcKey, String destKey, int time) {
        return this.jedisCluster.brpoplpush(srcKey, destKey, time);
    }

    public Long lrem(String key, long count, String value) {
        return this.jedisCluster.lrem(key, count, value);
    }

    public List<String> lrange(String key, long start, long end) {
        return this.jedisCluster.lrange(key, start, end);
    }
}

