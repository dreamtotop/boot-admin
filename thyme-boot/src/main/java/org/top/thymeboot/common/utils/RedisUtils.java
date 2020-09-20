package org.top.thymeboot.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key  缓存key
     * @param value 缓存value
     * @return
     */
    public boolean set(String key, Object value){
        ValueOperations<Serializable, Object> operation = redisTemplate.opsForValue();
        operation.set(key,value);
        return true;
    }

    /**
     * 写缓存
     * @param key  缓存key
     * @param value  缓存value
     * @param expireTime  缓存失效时间
     * @param timeUnit  时间单位
     * @return
     */
    public boolean set(String key, String value, Long expireTime, TimeUnit timeUnit){

        ValueOperations operations = redisTemplate.opsForValue();
        operations.set(key,value,expireTime,timeUnit);
        return true;
    }

    /**
     * 读缓存
     * @param key  缓存key
     * @return
     */
    public Object get(String key){
        ValueOperations operations = redisTemplate.opsForValue();
        Object value = operations.get(key);
        return value;
    }

    /**
     * 缓存key是否存在
     * @param key
     * @return
     */
    public boolean exist(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存key
     * @param key
     * @return
     */
    public void delete(String key){
        if(exist(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * 模糊查询key
     * @param key
     * @return
     */
    public Set<String> keys(String key){
        return redisTemplate.keys(key);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpireTime(String key, TimeUnit unit){
        return redisTemplate.getExpire(key,unit);
    }

    /**
     * 批量删除key
     * @param keys
     */
    public void batchDelete(Set<String> keys){
        for(String key : keys){
            redisTemplate.delete(key);
        }
    }

    /**
     * 批量删除
     * @param keys
     */
    public void batchDelete(String... keys){
        for(String key: keys){
            redisTemplate.delete(key);
        }
    }

    //=======================set========================

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     */
    @SuppressWarnings("unchecked")
    public Set<Object> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询，是否存在
     * @param key 键
     * @param value 值
     */
    @SuppressWarnings("unchecked")
    public boolean sHasKey(String key,Object value){
        return redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     */
    @SuppressWarnings("unchecked")
    public long sSet(String key,Object...values){
        return redisTemplate.opsForSet().add(key,values);
    }

    /**
     * 将set数据放入缓存 设置缓存时间
     * @param key 键
     * @param time 时间
     * @param values 值可以多个
     */
    @SuppressWarnings("unchecked")
    public long sSetAndTime(String key,long time,Object...values){
        long count = redisTemplate.opsForSet().add(key, values);
        if (time> 0){
            redisTemplate.expire(key,time,TimeUnit.SECONDS);
        }
        return count;
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return 长度
     */
    @SuppressWarnings("unchecked")
    public long sGetSetSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    @SuppressWarnings("unchecked")
    public long setRemove(String key,Object...values){
        return redisTemplate.opsForSet().remove(key, values);
    }
}
