package me.zhyx.securities.service.quartz;

import me.zhyx.securities.common.enums.JobOperateEnum;
import me.zhyx.securities.common.model.ScheduleJob;
import org.quartz.SchedulerException;

public interface QuartzService{
    void timingTask();
    void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob scheduleJob) throws SchedulerException;
    void add(int id);
    void start(int id);
    void pause(int id);
    void delete(int id);
    void startAllJob();
    void pauseAllJob();
}
