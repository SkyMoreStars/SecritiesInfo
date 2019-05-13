package me.zhyx.securities.component.job;


import lombok.Data;

/**
 * @auther zhyx
 * @Date 2019/5/10 11:53
 * @Description
 */
@Data
public class CrawlerJob extends Thread {
    private String threadName;
    public CrawlerJob(String name) {
        super(name);
        this.threadName=name;
    }

    @Override
    public void run()  {
        System.out.println("Task thread"+ this.threadName+":"+System.currentTimeMillis());
    }
}
