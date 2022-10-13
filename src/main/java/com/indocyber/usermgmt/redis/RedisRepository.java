package com.indocyber.usermgmt.redis;

public interface RedisRepository {

    void put(String group, String key, Object value);
    Object get(String group, String key);
    void delete(String group, String key);
    void deleteAll(String group);

}
