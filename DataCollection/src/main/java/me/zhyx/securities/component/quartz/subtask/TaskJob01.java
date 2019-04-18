package me.zhyx.securities.component.quartz.subtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("taskJob01")
@Transactional
@Slf4j
public class TaskJob01 {
    public synchronized void execute(){
        log.info("TaskJob01---->开始执行");
        log.info("do some thing");
        log.info("TaskJob01---->执行结束");
    }
}
