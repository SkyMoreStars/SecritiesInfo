package me.zhyx.securities.dao;

import me.zhyx.securities.common.model.ScheduleJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yssq
 */
@Repository
public interface ScheduleJobDao {
    /**
     * 获取所有的定时任务
     * @return
     */
    List<ScheduleJob> taskList();

    List<ScheduleJob> selectOne(Integer id);

    void add(ScheduleJob scheduleJob);

    void updateJob(@Param("job") ScheduleJob job);

    void startAllJob();

    void pauseAllJob();

}
