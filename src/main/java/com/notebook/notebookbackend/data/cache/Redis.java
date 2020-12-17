package com.notebook.notebookbackend.data.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class Redis {
    private final StringRedisTemplate redis;

    @Autowired
    public Redis(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }

    public void set(String key, String value) {
        redis.opsForValue().set(key, value);
    }

    public void set(String key, String value, Duration expire) {
        redis.opsForValue().set(key, value, expire);
    }
}
