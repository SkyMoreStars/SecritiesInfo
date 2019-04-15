package me.zhyx.securities.service.quartz;

import me.zhyx.securities.common.enums.JobOperateEnum;
import me.zhyx.securities.common.model.ScheduleJob;
import org.quartz.SchedulerException;

public interface QuartzService {
    void timingTask();
    void addJob(ScheduleJob job);
    void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob scheduleJob) throws SchedulerException;
    void startAllJob() throws SchedulerException;
    void pauseALlJob() throws SchedulerException;
}
