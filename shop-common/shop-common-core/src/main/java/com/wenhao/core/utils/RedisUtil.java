package com.wenhao.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;
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
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

}