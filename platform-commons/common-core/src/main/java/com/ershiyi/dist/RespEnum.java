package com.ershiyi.dist;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.common.dto.BaseResultFactory;
import com.ershiyi.exception.PlatformServiceException;

public enum RespEnum {
    OK(200, "成功"),
    CREATED(201,"创建成功"),
    NOT_FOUND(404, "未找到相关数据"),
    AUTH(401, "认证错误"),
    UPLOAD_ERROR(500, "上传错误"),
    DOWNLOAD_ERROR(500, "下载错误"),
    EXCEL_OUT_ERROR(500, "EXCEL创建错误"),
    TOKEN_ERROR(500, "令牌错误！"),
    CREATE_DATA_ERROR(400, "创建数据失败！"),
    CHECK_FAILED(413,"校验失败"),
    SYS_ERROR(500, "系统繁忙！"),
    INTEGRAL_ERROR(410,"积分不足"),
    REBUY(411,"重复购买"),
    BUY_ERROR(412,"购买失败!"),
    PASS_ERROR(402,"密码错误"),
    STUDY_COMPLETE(202,"当前课程已经学完！"),
    ERROR(500, "系统未知错误！");

    private int code;

    private String message;

    RespEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public <T> AbstractBaseResult<T> result(T t){
        return BaseResultFactory.getInstance().build(this, t);
    }

    public PlatformServiceException throwException(){
        return new PlatformServiceException(this);
    }

    public PlatformServiceException throwException(String msg){
        return new PlatformServiceException(this,msg);
    }

    public PlatformServiceException throwException(Throwable cause){
        return new PlatformServiceException(this, cause);
    }
}
