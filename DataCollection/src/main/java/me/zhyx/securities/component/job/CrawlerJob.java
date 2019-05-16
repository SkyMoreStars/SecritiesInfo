package me.zhyx.securities.component.job;


import lombok.Data;


/**
 * @auther zhyx
 * @Date 2019/5/10 11:53
 * @Description
 */
@Data
public class CrawlerJob extends Thread {
    private String jobId;
    public CrawlerJob(String jobId) {
        super(jobId);
        this.jobId=jobId;
    }

    @Override
    public void run()  {

        System.out.println("i am "+ this.jobId);
    }
}
