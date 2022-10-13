package com.indocyber.usermgmt.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RedisRepositoryImpl implements RedisRepository{

    @Resource(name="redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void put(String group, String key, Object value) {
        redisTemplate.opsForHash().put(group, key, value);
    }

    @Override
    public Object get(String group, String key) {
        return redisTemplate.opsForHash().get(group, key);
    }

    @Override
    public void delete(String group, String key) {
        redisTemplate.opsForHash().delete(group, key);
    }


    @Override
    public void deleteAll(String group) {
        redisTemplate.delete(group);
    }

}
