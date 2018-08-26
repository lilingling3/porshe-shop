package com.boldseas.porscheshop.user;

import com.boldseas.porscheshop.user.beans.Token;

import java.util.Map;

/**
 * @author Chen Jingxuan
 * @version 2018/5/11
 */
public interface RedisService {
    /**
     * Redis中某一key的值是否已经超过最大限制
     * @param prefix
     * @param key
     * @param max
     * @return
     */
    boolean isOverMax(String prefix, String key, int max);

    /**
     * 保存记录到Redis
     * @param prefix
     * @param key
     * @param expire
     */
    void save(String prefix, String key, long expire);

    /**
     * 保存key-value到redis
     * @param keyPrefix
     * @param key
     * @param value
     * @param expire
     */
    void save(String keyPrefix, String key, String value, long expire);

    /**
     * 保存用户登录时生成的token
     * @param token
     */
    void saveToken(Token token);

    /**
     * 根据Key获取Map，如果可以获取到，重新设置过期时间
     * @param prefix
     * @param key
     * @param expire
     * @return
     */
    Map<String, String> getMapAndResetExpire(String prefix, String key, long expire);

    /**
     * 删除一个key
     * @param prefix
     * @param key
     */
    void deleteKey(String prefix, String key);

    /**
     * 获取key的value
     * @param keyPrefix
     * @param key
     * @return value （String类型）
     */
    String get(String keyPrefix, String key);
}
