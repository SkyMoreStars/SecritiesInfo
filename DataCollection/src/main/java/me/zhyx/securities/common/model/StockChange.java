package me.zhyx.securities.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前实时股价
 */
@Data
public class StockChange implements Serializable {
    private String currentPrice;
    private String changeVal;
    private String changePercent;
    private Date currentTime;
    private Date date;

}
