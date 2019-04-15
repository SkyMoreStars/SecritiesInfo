package me.zhyx.securities.common.model;

/**
 * @Auther: yssq
 * @Date: 2019/4/15 11:52
 * @Description:
 */
public class ResponseModel {
    private String code;            //返回码
    private Object data;            //返回数据
    private String desc;            //返回说明

    public String getCode() {
        return code;
    }

    public ResponseModel setCode(String code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseModel setData(Object data) {
        this.data = data;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResponseModel setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
