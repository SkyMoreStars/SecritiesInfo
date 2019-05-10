package me.zhyx.securities.common.utils;

import me.zhyx.securities.common.enums.CrawlerType;
import me.zhyx.securities.common.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther zhyx
 * @Date 2019/4/25 16:38
 * @Description
 */
@Component
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
}
