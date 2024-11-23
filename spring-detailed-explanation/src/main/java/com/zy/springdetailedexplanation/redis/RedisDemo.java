package com.zy.springdetailedexplanation.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDemo {

    //日志
    private final static Log log = LogFactory.getLog(RedisDemo.class);

    private Jedis jedis;

    public RedisDemo(Jedis jedis) {
        this.jedis = jedis;
    }
    //redis获取所
    public synchronized boolean getLock(String key) {
        long setnx = jedis.setnx(key, System.currentTimeMillis() + "");

        if (setnx ==0 ) {
            //说明有人使用锁
            return false;
        }else {
            return true;
        }
    }

    //释放锁
    public synchronized boolean releaseLock(String key) {
        String s = jedis.get(key);
        if (s == null){
            //没人使用锁
            return false;
        }else {
            jedis.del(key);
            return true;
        }
    }

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.92.128", 6379);
        Jedis jedis1 = new Jedis();

        Jedis jedis = jedisPool.getResource();
        RedisDemo redisDemo = new RedisDemo(jedis);
        String key = "123";
        try {
            if (redisDemo.getLock(key)) {
                System.out.println("枷锁");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            redisDemo.releaseLock(key);
        }

    }
}
