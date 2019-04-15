package me.zhyx.securities.component.quartz;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.service.quartz.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class JobSchedule implements CommandLineRunner {
    @Autowired
    private QuartzService quartzService;
    /**
     * 任务调度开始
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("任务调度开始");
        quartzService.timingTask();
        log.info("任务调度结束");
    }
}
