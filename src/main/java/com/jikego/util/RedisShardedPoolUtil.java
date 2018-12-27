package com.jikego.util;

import com.jikego.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author Geekerstar(jikewenku.com)
 * Date: 2018/7/23 9:26
 * Description:
 */
@Slf4j
public class RedisShardedPoolUtil {

    /**
     * description: 设置key的有效期，单位是秒
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key, exTime]
     * return: java.lang.Long
     */
    public static Long expire(String key, int exTime) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description: exTime的单位是秒
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key, value, exTime]
     * return: java.lang.String
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description:
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key, value]
     * return: java.lang.String
     */
    public static String set(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description:
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key, value]
     * return: java.lang.String
     */
    public static String getSet(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.getSet(key, value);
        } catch (Exception e) {
            log.error("getset key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description:
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key]
     * return: java.lang.String
     */
    public static String get(String key) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description:
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:52
     * param: [key]
     * return: java.lang.Long
     */
    public static Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }

    /**
     * description:
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 19:56
     * param: [key, value]
     * return: java.lang.Long
     */
    public static Long setnx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setnx key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }
        RedisShardedPool.returnResource(jedis);
        return result;
    }
}

