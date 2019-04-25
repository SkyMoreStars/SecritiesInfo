package me.zhyx.securities.common.enums;

public enum  CrawlerType {
    FUNDS("资金"),
    SECURITIES("证券"),
    STOCK("股票"),
    DAYEND("日终");
    String desc;

    CrawlerType(String desc) {
        this.desc = desc;
    }

}
