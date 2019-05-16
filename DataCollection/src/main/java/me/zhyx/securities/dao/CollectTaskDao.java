package me.zhyx.securities.dao;

import me.zhyx.securities.common.model.CollectTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther zhyx
 * @Date 2019/5/16 15:50
 * @Description
 */
@Repository
public interface CollectTaskDao {
    List<CollectTask> findAll();

}
