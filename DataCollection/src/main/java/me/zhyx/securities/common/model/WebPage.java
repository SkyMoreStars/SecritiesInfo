package me.zhyx.securities.common.model;

import lombok.Data;
import lombok.ToString;
import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: yssq
 * @Date: 2019/4/19 14:25
 * @Description:
 */
@Data
@ToString
public class WebPage implements Serializable {
    private Date crawlTime;
    private String page;
    private Document document;
    private String html;
}
