package me.zhyx.securities.component.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author yssq
 * @date 2019/5/13
 *
 */
@Component
public class TimerPool {

    @Bean
    @Scope
    public ScheduledExecutorService scheduledExecutorService(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1000);
        return service;
    }
}
