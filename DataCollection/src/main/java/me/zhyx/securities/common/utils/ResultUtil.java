package me.zhyx.securities.common.utils;

import me.zhyx.securities.common.enums.ResultEnum;
import me.zhyx.securities.common.model.ResponseModel;

/**
 * @Auther: yssq
 * @Date: 2019/4/15 11:56
 * @Description:
 */
public class ResultUtil {
    public static ResponseModel success(Object object) {
        return new ResponseModel().setCode(ResultEnum.SUCCESS.getCode()).setData(object).setDesc(ResultEnum.SUCCESS.getMessage());
    }

    public static ResponseModel error(ResultEnum resultEnum) {
        return new ResponseModel().setDesc(resultEnum.getMessage()).setCode(resultEnum.getCode());
    }

    public static ResponseModel error(String code, String message) {
        return new ResponseModel().setDesc(message).setCode(code);
    }

    public static ResponseModel error(String code, String message,Object object) {
        return new ResponseModel().setDesc(message).setCode(code).setData(object);
    }

    public static ResponseModel error(ResultEnum resultEnum, Object object) {
        return new ResponseModel().setDesc(resultEnum.getMessage()).setCode(resultEnum.getCode()).setData(object);
    }


//    public static ResponseModel error(CoreBusinessException coreBusinessException) {
//        return new ResponseModel().setDesc(coreBusinessException.getMessage()).setCode(coreBusinessException.getCode()).setData(coreBusinessException.getObject());
//    }
    public static ResponseModel success() {
        return success("");
    }
}
