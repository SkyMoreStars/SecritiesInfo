package me.zhyx.securities.common.utils;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.enums.CrawlerType;
import me.zhyx.securities.common.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @auther zhyx
 * @Date 2019/4/25 16:38
 * @Description
 */
@Component
@Slf4j
public class RedisUtil {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    public boolean save(CrawlerType crawlerType,Object o){
        if(o==null){
            return false;
        }
        if(o instanceof Stock){
            Stock stock= (Stock) o;
            redisTemplate.opsForList().leftPush(CrawlerType.STOCK.name()+stock.getCode(),stock);
            return true;
        }
        return false;
    }
    public boolean expire(String key, Long time, TimeUnit timeUnit){
        redisTemplate.expire(key,time,timeUnit);
        return true;
    }
    public Object getObject(String key, CrawlerType crawlerType){
        Object o = redisTemplate.opsForList().rightPop(key+crawlerType.name());
        return o;
    }
    public List<Object> getObjects(String stockCode,CrawlerType crawlerType){
        List<Object> range = redisTemplate.opsForList().range(CrawlerType.STOCK.name()+stockCode, 0, -1);
        return range;
    }
    public boolean saveMap(String key, Map<String,Object> map){
        if(key==null||"".equals(key)){
            log.error("Save Map key is null!");
            return false;
        }
        if(map==null||map.size()==0){
            log.error("SaveMap map empty or map is null!");
            return false;
        }
        redisTemplate.opsForHash().putAll(key,map);
        return true;
    }
    public Map<String,Object> getAllMap(String key){
        if(key==null||"".equals(key)){
            log.error("Get Map the param key is null");
            return new HashMap<>(0);
        }
        return (Map<String, Object>) redisTemplate.opsForHash().scan(key, ScanOptions.NONE);
    }

}
