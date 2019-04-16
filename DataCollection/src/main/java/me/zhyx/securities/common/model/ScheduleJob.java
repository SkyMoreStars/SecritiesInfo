package me.zhyx.securities.common.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ScheduleJob implements Serializable {

    private Integer id;
    /**
     * 任务名称
     */
    private String jobName;
    /**
     * cron表达式
     */
    private String cronExpression;
    /**
     * 服务名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 状态 1.启动，2.暂停
     */
    private int status;
    /**
     * 是否删除 0.否 1.是
     */
    private boolean deleteFlag;
    /**
     * 创建人id
     */
    private Integer createId;
    /**
     * 创建人名称
     */
    private String createName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
