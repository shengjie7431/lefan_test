package com.lefancrm.apicenter.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 分布式锁
 * @Author:lu.sl
 * @Date:2020-10-16 1:56 PM
 * @Version 1.0
 **/
@Component
public class ConcurrentLockUtils {

    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    /**
     * 加锁
     *
     * @param key
     * @param value
     * @return boolean
     * @method lock
     **/
    public boolean lock(String key, String value) {

        try {
            //检查是否锁已存在
            if (this.getLock(key, value, "300")) {

                //容器存储当前锁唯一序列
                HashMap<String, String> currentThreadMap = new HashMap<>(3);
                currentThreadMap.put(key, value);

                //本线程唯一标记添加容器
                threadLocal.set(currentThreadMap);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @return boolean
     * @method unlock
     **/
    public boolean unlock(String key) {

        try {
            //从ThreadLock获取当前线程信息
            Map<String, String> currentThread = threadLocal.get();
            if (null == currentThread) {
                return false;
            }
            //删除锁信息
            if (this.releaseLock(key, currentThread.get(key))) {
                //删除本地线程信息
                threadLocal.remove();
                return true;
            }
        } catch (Exception e) {
            //throw new BusinessException(SysCodeEnum.SYS_ERROR);
        }
        return false;
    }

    /**
     * 获取锁
     *
     * @param lockKey
     * @param value
     * @param expireTime：单位-秒
     * @return
     */
    public boolean getLock(String lockKey, String value, String expireTime) {
        try {
            //lua脚本
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end  end";

            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);

            if (null != result && StringUtils.equals("1", result.toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey
     * @param value
     * @return
     */
    private boolean releaseLock(String lockKey, String value) {
        try {
            //lua脚本
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
            if (null != result && StringUtils.equals("1", result.toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
