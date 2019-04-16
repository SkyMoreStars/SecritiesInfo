package me.zhyx.securities.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //覆盖已经存在的定时任务
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        //延时60秒启动定时任务，避免系统未完成任务
        schedulerFactoryBean.setStartupDelay(60);
        return schedulerFactoryBean;
    }
    @Bean(name = "scheduler")
    public Scheduler scheduler(){
        return schedulerFactoryBean().getScheduler();
    }
}
