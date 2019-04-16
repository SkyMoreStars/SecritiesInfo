package me.zhyx.securities.service.quartz.impl;

import me.zhyx.securities.common.enums.JobOperateEnum;
import me.zhyx.securities.common.model.ScheduleJob;
import me.zhyx.securities.factory.QuartzFactory;
import me.zhyx.securities.service.quartz.QuartzService;
import me.zhyx.securities.dao.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yssq
 */
@Service
@Transactional
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Override
    public void timingTask() {
        //查询数据库是否存在需要定时的任务
        List<ScheduleJob> scheduleJobs = scheduleJobService.taskList();
        if (null != scheduleJobs) {
            scheduleJobs.forEach(this::addJob);
        }
    }

    @Override
    public void addJob(ScheduleJob job) {
        try {
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(job.getJobName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .startNow()
                    .build();
            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class)
                    .withIdentity(job.getJobName())
                    .build();
            /**
             * 传入调度数据，在QuartzFactory中需要使用到
             */
            jobDetail.getJobDataMap().put("scheduleJob", job);
            //调度作业
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = new JobKey(scheduleJob.getJobName());
        JobDetail jobDetail= scheduler.getJobDetail(jobKey);
        if(null==jobDetail){
            //异常
            throw new RuntimeException();
        }
        switch (jobOperateEnum){
            case PAUSE:{
                scheduler.pauseJob(jobKey);
                break;
            }
            case START:{
                scheduler.resumeJob(jobKey);
                break;
            }
            case DELETE:{
                scheduler.deleteJob(jobKey);
            }
        }


    }

    @Override
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    @Override
    public void pauseALlJob() throws SchedulerException {
        scheduler.pauseAll();
    }
}
