package com.notebook.notebookbackend.data.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author 22454
 */

public class Redis {
    private final StringRedisTemplate redis;

    public Redis(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }

    public void put(String key, String value) {
        redis.opsForValue().set(key, value);
    }

    public void put(String key, String value, Duration expire) {
        redis.opsForValue().set(key, value, expire);
    }

    public void put(String key, String value, long timeout) {
        redis.opsForValue().set(key, value, timeout);
    }

    public void remove(String key) {
        redis.delete(key);
    }

    public void setExpire(String key, long timeout) {
        redis.expire(key, timeout, TimeUnit.SECONDS);
    }

    public boolean exists(String key) {
        String value = get(key);
        return value != null;
    }
}
