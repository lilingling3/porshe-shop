package com.boldseas.porscheshop.interceptor.limit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author fei.ye
 * @version 2018/5/9.
 */
@Service
public class LimitService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public LimitService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean limit(Limit limit, long times) {
        if (!redisTemplate.hasKey(limit.getKey())) {
            BoundHashOperations operation = redisTemplate.boundHashOps(limit.getKey());
            operation.putAll(limit.getMap());
            operation.expire(limit.getSeconds(), TimeUnit.SECONDS);
        } else {
            redisTemplate.boundHashOps(limit.getKey()).expire(limit.getSeconds(), TimeUnit.SECONDS);
            return redisTemplate.boundHashOps(limit.getKey()).increment("times", 1) <= times;
        }
        return true;
    }
}
