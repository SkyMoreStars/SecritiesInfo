package me.zhyx.securities.component.quartz.subtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("testJob02")
@Transactional
@Slf4j
public class TaskJob02 {
    public void execute(){
        log.info("TaskJob02---->开始执行");
        log.info("do some thing");
        log.info("TaskJob02---->执行结束");
    }
}
