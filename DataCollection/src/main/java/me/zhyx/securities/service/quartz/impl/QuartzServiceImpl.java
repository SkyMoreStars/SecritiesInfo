package me.zhyx.securities.service.quartz.impl;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.enums.JobOperateEnum;
import me.zhyx.securities.common.model.ScheduleJob;
import me.zhyx.securities.factory.QuartzFactory;
import me.zhyx.securities.service.quartz.QuartzService;
import me.zhyx.securities.dao.ScheduleJobDao;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yssq
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobDao scheduleJobDao;

    @Override
    public void timingTask() {
        //查询数据库是否存在需要启动的定时任务
        List<ScheduleJob> scheduleJobs = scheduleJobDao.taskList();
        if (null != scheduleJobs) {
            scheduleJobs.forEach(this::init);
        }
    }


    @Override
    public void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob scheduleJob) throws SchedulerException {
        JobKey jobKey = new JobKey(scheduleJob.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (null == jobDetail) {
            //异常
            throw new RuntimeException();
        }
        switch (jobOperateEnum) {
            case PAUSE: {
                scheduler.pauseJob(jobKey);
                break;
            }
            case START: {
                scheduler.resumeJob(jobKey);
                break;
            }
            case DELETE: {
                scheduler.deleteJob(jobKey);
                break;
            }
            default: {
                return;
            }
        }


    }

    public List<ScheduleJob> taskList() {
        return scheduleJobDao.taskList();
    }
    public void init(ScheduleJob job) {
        if (job.getId() != null) {
            ScheduleJob scheduleJobs = findOneScheduleJob(job.getId());
            if (scheduleJobs==null) {
                scheduleJobDao.add(job);
            }

        }
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
    public void add(int id, ScheduleJob job) {
        ScheduleJob scheduleJob = findOneScheduleJob(id);
        BeanUtils.copyProperties(job,scheduleJob);
        if(scheduleJob!=null){
            scheduleJob.setDeleteFlag(false);
            scheduleJobDao.updateJob(scheduleJob);
            this.init(scheduleJob);
        }

    }

    private ScheduleJob findOneScheduleJob(Integer id) {
        List<ScheduleJob> scheduleJobs = scheduleJobDao.selectOne(id);
        if(scheduleJobs!=null&&scheduleJobs.size()>0){
            return scheduleJobs.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void start(int id) {
        ScheduleJob scheduleJob = findOneScheduleJob(id);

        scheduleJob.setStatus(JobOperateEnum.START.getValue());
        scheduleJobDao.updateJob(scheduleJob);
        try {
            operateJob(JobOperateEnum.START, scheduleJob);
        } catch (SchedulerException e) {
            log.error("Start job exception,the job name is {}", scheduleJob.getJobName());
        }
    }

    @Override
    public void pause(int id) {
        ScheduleJob scheduleJob = findOneScheduleJob(id);
        scheduleJob.setStatus(JobOperateEnum.PAUSE.getValue());
        scheduleJobDao.updateJob(scheduleJob);
        try {
            operateJob(JobOperateEnum.PAUSE, scheduleJob);
        } catch (SchedulerException e) {
            log.error("Pause job exception,the job name is {}", scheduleJob.getJobName());
        }

    }

    @Override
    public void delete(int id) {
        ScheduleJob scheduleJob = findOneScheduleJob(id);
        scheduleJob.setDeleteFlag(true);
        scheduleJobDao.updateJob(scheduleJob);
        try {
            operateJob(JobOperateEnum.DELETE, scheduleJob);
        } catch (SchedulerException e) {
            log.error("Delete job exception,the job name is {}", scheduleJob.getJobName());
        }
    }

    @Override
    public void startAllJob() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("start all job exception {}", e.getMessage());
        }
    }

    @Override
    public void pauseAllJob() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("pause all job exception {}", e.getMessage());
        }
    }
}
