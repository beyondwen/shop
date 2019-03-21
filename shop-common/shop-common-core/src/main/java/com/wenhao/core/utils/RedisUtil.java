package com.wenhao.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author WangFan
 * @version 1.1 (GitHub文档: https://github.com/whvcse/RedisUtil )
 * @date 2018-02-24 下午03:09:50
 */
@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //设置stirng 有过期时间
    public void setString(String key, String data, Long exTime) {
        stringRedisTemplate.opsForValue().set(key, data);
        if (exTime != null) {
            stringRedisTemplate.expire(key, exTime, TimeUnit.SECONDS);
        }
    }

    //设置stirng 无过期时间
    public void setString(String key, String data) {
        setString(key, data, null);
    }

    //根据key查询
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    //删除key
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 开启事务
     */
    public void begin() {
        //开启redis 事务权限
        stringRedisTemplate.setEnableTransactionSupport(true);
        //开启事务
        stringRedisTemplate.multi();
    }

    /**
     * 提交事务
     */
    public void exec() {
        //提交事务
        stringRedisTemplate.exec();
    }

    /**
     * 回滚事务
     */
    public void discard() {
        //回滚事务
        stringRedisTemplate.discard();
    }

}