package me.zhyx.securities.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author yssq
 * @date 2019/5/13
 *
 */
@Configuration
public class TimerPool {

    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1000);
        return service;
    }
}
