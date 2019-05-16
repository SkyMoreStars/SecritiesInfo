package me.zhyx.securities.component.quartz.subtask;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.enums.SyStemParam;
import me.zhyx.securities.common.model.CollectTask;
import me.zhyx.securities.common.model.Stock;
import me.zhyx.securities.common.utils.RedisUtil;
import me.zhyx.securities.component.job.CrawlerJob;
import me.zhyx.securities.component.job.CrawlerStock;
import me.zhyx.securities.config.ThreadTaskPool;
import me.zhyx.securities.dao.CollectTaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 */
@Component("taskJob01")
@Transactional
@Slf4j
public class TaskJob01 {

    @Autowired
    ScheduledExecutorService scheduledExecutorService;

    @Autowired
    CollectTaskDao collectTaskDao;
    @Autowired
    RedisUtil redisUtil;
    public synchronized void execute() {
        log.info("加载所有需要采集的证券");
        refreshTaskStatus();
        refreshTaskThreadPool();
        CrawlerStock crawlerStock  = new CrawlerStock("", stockCode);
        Future future = scheduledExecutorService.scheduleAtFixedRate(crawlerStock, 1, 1, TimeUnit.SECONDS);
        ThreadTaskPool.futureMap.put(stockCode,future);
        log.info("TaskJob01---->执行结束");
    }

    private void refreshTaskThreadPool() {
        ThreadTaskPool.runningTask
    }

    private void refreshTaskStatus() {
        List<CollectTask> collectTaskList= collectTaskDao.findAll();
        for (CollectTask collectTask : collectTaskList) {
            if(collectTask.getIsDelete().equals( SyStemParam.RUNNING.getCode())){
                ThreadTaskPool.runningTask.put(collectTask.getMainCode(),collectTask);
            }
            if (collectTask.getIsDelete().equals(SyStemParam.STOP.getCode())){
                ThreadTaskPool.stopTask.put(collectTask.getMainCode(),collectTask);
            }
        }
        redisUtil.saveMap(SyStemParam.RUNNING.name(), ThreadTaskPool.runningTask);
        redisUtil.saveMap(SyStemParam.STOP.name(),ThreadTaskPool.stopTask);
    }
}
