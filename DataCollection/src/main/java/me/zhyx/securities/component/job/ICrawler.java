package me.zhyx.securities.component.job;


import me.zhyx.securities.common.model.WebPage;

/**
 * @auther zhyx
 * @Date: 2019/4/19 14:23
 * @Description:
 */
public interface ICrawler{
    WebPage getPage();
    void parsePage(WebPage webPage);

}
