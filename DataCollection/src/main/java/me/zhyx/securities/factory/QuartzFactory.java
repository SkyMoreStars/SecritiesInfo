package me.zhyx.securities.factory;

import me.zhyx.securities.common.model.ScheduleJob;
import me.zhyx.securities.common.utils.SpringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

public class QuartzFactory implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取调度数据
        ScheduleJob  schedulejob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        //获取对应的bean
        Object bean = SpringUtil.getBean(schedulejob.getBeanName());
        try {
            Method method = bean.getClass().getMethod(schedulejob.getMethodName());
            method.invoke(bean);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
