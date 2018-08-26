package com.boldseas.porscheshop.user.service;

import com.boldseas.porscheshop.common.constant.PrefixConstant;
import com.boldseas.porscheshop.user.RedisService;
import com.boldseas.porscheshop.user.beans.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final StringRedisTemplate stringRedisTemplate;
    private final String KEY_FORMAT = "%s_%s";

    @Autowired
    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean isOverMax(String prefix, String key, int max) {
        String redisKey = String.format(KEY_FORMAT, prefix, key);
        BoundValueOperations<String, String> operations = stringRedisTemplate.boundValueOps(redisKey);
        return operations.size() > 0 && Integer.parseInt(operations.get()) >= max;
    }

    @Override
    @Async
    public void save(String prefix, String key, long expire) {
        String redisKey = String.format(KEY_FORMAT, prefix, key);
        BoundValueOperations<String, String> operations = stringRedisTemplate.boundValueOps(redisKey);
        long value = operations.increment(1);
        if (value <= 1) {
            operations.set(String.valueOf(value));
            operations.expire(expire, TimeUnit.MINUTES);
        } else {
            expire = operations.getExpire();
            operations.set(String.valueOf(value));
            operations.expire(expire,TimeUnit.SECONDS);
        }
    }

    @Override
    public void save(String keyPrefix, String key, String value, long expire) {
        String redisKey = String.format(KEY_FORMAT, keyPrefix, key);
        BoundValueOperations<String, String> operations = stringRedisTemplate.boundValueOps(redisKey);
        operations.set(value);
        operations.expire(expire, TimeUnit.SECONDS);
    }

    @Override
    @Async
    public void saveToken(Token token) {
        String redisKey = String.format(KEY_FORMAT, PrefixConstant.LOGIN_AUTH_TOKEN, token.getAuthToken());
        BoundHashOperations<String, String, String> operations = stringRedisTemplate.boundHashOps(redisKey);
        operations.putAll(token.getTokenEntries());
        operations.expire(token.getExpire(), TimeUnit.SECONDS);
    }

    @Override
    public Map<String, String> getMapAndResetExpire(String prefix, String key, long expire) {
        String redisKey = String.format(KEY_FORMAT, prefix, key);
        BoundHashOperations<String, String, String> operations = stringRedisTemplate.boundHashOps(redisKey);
        if (operations.size() > 0) {
            operations.expire(expire, TimeUnit.SECONDS);
            return operations.entries();
        }
        return new HashMap<>(0);
    }

    @Override
    public void deleteKey(String prefix, String key) {
        stringRedisTemplate.delete(String.format(KEY_FORMAT, prefix, key));
    }

    @Override
    public String get(String keyPrefix, String key) {
        String redisKey = String.format(KEY_FORMAT, keyPrefix, key);
        return stringRedisTemplate.boundValueOps(redisKey).get();
    }
}
