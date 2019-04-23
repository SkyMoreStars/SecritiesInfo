package me.zhyx.securities.component.job;

import lombok.extern.slf4j.Slf4j;
import me.zhyx.securities.common.model.WebPage;
@Slf4j
public class CrawlerStock extends AbstractCrawler{
    @Override
    public void parsePage(WebPage webPage) {
        try {
            log.info("start parse page!");
            String price = webPage.getDocument().getElementById("price9").text();
            String change = webPage.getDocument().getElementById("km1").text();
            String changePercent =webPage.getDocument().getElementById("km2").text();

            log.info("end parse page!");
        }catch (Exception e){
            log.error("parse page exception {}",e.getMessage());
        }
    }
}
