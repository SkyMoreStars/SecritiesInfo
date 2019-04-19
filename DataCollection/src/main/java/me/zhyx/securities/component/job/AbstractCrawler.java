package me.zhyx.securities.component.job;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.enums.HttpMethod;
import me.zhyx.securities.common.model.WebPage;
import me.zhyx.securities.common.utils.HttpClientUtils;
import org.jsoup.Jsoup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhyx
 * @Date: 2019/4/19 14:27
 * @Description:
 */
@Slf4j
public abstract class AbstractCrawler implements ICrawler, Runnable {
    protected String pageUrl;
    protected WebPage webPage;
    protected HttpMethod httpMethod = HttpMethod.GET;
    protected Map<String,String> header=new HashMap<String, String>() {{
        put("Connection", "keep-alive");
        put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");
        put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        put("Accept-Encoding", "gzip, deflate, sdch");
        put("Accept-Language", "zh-CN,zh;q=0.9");
        put("Redis-Control", "max-age=0");
        put("Upgrade-Insecure-Requests", "1");
    }};

    @Override
    public void run() {
        getPage();
        parsePage(webPage);
    }

    @Override
    public WebPage getPage() {
        WebPage webPage=null;
        try{
            log.debug("start get page {}",pageUrl);
            header.put("Referer",pageUrl);
            String pageContent="";
            if(httpMethod==HttpMethod.GET){
                pageContent= HttpClientUtils.sendGet(pageUrl,header);
            }
            webPage=new WebPage();
            webPage.setCrawlTime(new Date());
            webPage.setPage(pageContent);
            webPage.setDocument(Jsoup.parse(pageContent));
            webPage.setHtml(Jsoup.parse(pageContent).html());
            this.webPage=webPage;
            log.debug("end get page:{}",pageUrl);
        }catch (Exception e){
            log.error("error get page:{}",pageUrl);
        }
        return webPage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
