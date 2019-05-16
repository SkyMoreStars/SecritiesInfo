package me.zhyx.securities.config;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 * @auther yssq
 * @Date 2019/5/16 11:13
 * @Description
 */
public class ThreadTaskPool {
    /**
     * futureMap 任务id，future
     */
    public static ConcurrentHashMap<String, Future> futureMap = new ConcurrentHashMap<>();
    public static ConcurrentLinkedQueue<Object> concurrentLinkedQueue =new ConcurrentLinkedQueue<>();
    public static ConcurrentHashMap<String, Object> runningTask = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Object> stopTask = new ConcurrentHashMap<>();
}
