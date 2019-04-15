package me.zhyx.securities.dao;

import me.zhyx.securities.common.model.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleJobService {
    List<ScheduleJob> list();
}
