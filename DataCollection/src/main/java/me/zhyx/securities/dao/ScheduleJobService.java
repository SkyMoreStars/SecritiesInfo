package me.zhyx.securities.dao;

import me.zhyx.securities.common.model.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yssq
 */
@Repository
public interface ScheduleJobService {
    /**
     * 获取所有的定时任务
     * @return
     */
    List<ScheduleJob> taskList();
}
