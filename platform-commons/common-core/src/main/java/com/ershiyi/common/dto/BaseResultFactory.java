package com.ershiyi.common.dto;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.ershiyi.dist.RespEnum;

public class BaseResultFactory {

    private static BaseResultFactory baseResultFactory;

    private BaseResultFactory() { }

    public static BaseResultFactory getInstance() {
        if (baseResultFactory == null) {
            synchronized (BaseResultFactory.class) {
                if (baseResultFactory == null) {
                    baseResultFactory = new BaseResultFactory();
                }
            }
        }
        return baseResultFactory;
    }

    public AbstractBaseResult build(RespEnum respEnum, Object data){
        return build(respEnum.getCode(), respEnum.getMessage(), data);
    }

    public AbstractBaseResult build(RespEnum respEnum, Throwable cause){
        return build(respEnum, ExceptionUtil.getMessage(cause));
    }

    public AbstractBaseResult build(RespEnum respEnum, String detail){
        return build(respEnum.getCode(), respEnum.getMessage(), detail);
    }

    public AbstractBaseResult build(int code, String msg, Object data) {
        return new SuccessResult(code, msg, data);
    }

    public AbstractBaseResult build(int code, String msg, String detail) {
        return new ErrorResult(code, msg, detail);
    }
}
