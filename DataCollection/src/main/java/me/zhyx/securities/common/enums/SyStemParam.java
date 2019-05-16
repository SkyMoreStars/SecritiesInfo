package me.zhyx.securities.common.enums;

/**
 * @auther zhyx
 * @Date 2019/5/16 16:34
 * @Description
 */
public enum SyStemParam {
    RUNNING(0,"任务运行中"),
    STOP(1,"任务停止");
    String desc;
    Integer code;

    SyStemParam(Integer code,String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }}
