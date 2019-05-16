package me.zhyx.securities.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @auther zhyx
 * @Date 2019/5/16 15:51
 * @Description
 */
@Data
public class CollectTask implements Serializable {
    private Integer id;
    private String mainCode;
    private String collectType;
    private Integer isDelete;
}
