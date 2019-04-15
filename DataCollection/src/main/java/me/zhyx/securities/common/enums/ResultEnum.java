package me.zhyx.securities.common.enums;

/**
 * @Auther: yssq
 * @Date: 2019/4/15 11:59
 * @Description:
 */
public enum  ResultEnum {
    SUCCESS("0","成功"),
    NODATA("0001","未查到数据"),
    DATEISEXISTS("0002","数据已存在"),
    ERRORPARAMS("0003","参数错误"),
    FAIL("-1","失败"),
    SQLEXCEPTION("-2","SQL执行异常！"),
    NOTAUTHENTICATED("401","Not Authenticated!" ), TOKENINVALID("402","Token Invalid!" );
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
