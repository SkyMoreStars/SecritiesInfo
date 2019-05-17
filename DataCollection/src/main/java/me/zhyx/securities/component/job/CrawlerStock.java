package me.zhyx.securities.component.job;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.model.Stock;
import me.zhyx.securities.common.model.WebPage;
import me.zhyx.securities.config.ThreadTaskPool;
import org.jsoup.nodes.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class CrawlerStock extends AbstractCrawler {
    public CrawlerStock(String pageUrl,String jobId) {
        super(pageUrl,jobId);

    }

    @Override
    public void parsePage(WebPage webPage) {
        Document document = webPage.getDocument();
        Stock stock = null;
        try {
            DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
            String date= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String nowTime = LocalDateTime.now().format(dateTimeFormatter);
            log.info("start parse page!");
            String price = document.getElementById("price9").text();
            String change = document.getElementById("km1").text();
            String changePercent = document.getElementById("km2").text();
            String name = document.getElementById("name").text();
            String code = document.getElementById("code").text();
            stock = new Stock();
            stock.setChangePercent(changePercent);
            stock.setChangeVal(change);
            stock.setCurrentPrice(price);
            stock.setCurrentTime(nowTime);
            stock.setStockName(name);
            stock.setCode(code);
            stock.setDate(date);
            ThreadTaskPool.concurrentLinkedQueue.offer(stock);
            log.info("end parse page!");
        } catch (Exception e) {
            log.error("parse page exception {}", e.getMessage());
        }
    }
}
