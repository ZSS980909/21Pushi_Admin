package com.ershiyi.exception;

import com.ershiyi.dist.RespEnum;
import lombok.Getter;

public class PlatformServiceException extends RuntimeException {

    @Getter
    private RespEnum respEnum;

    public RespEnum getRespEnum() {
        return respEnum;
    }

    public void setRespEnum(RespEnum respEnum) {
        this.respEnum = respEnum;
    }

    public PlatformServiceException(RespEnum respEnum){
        super(respEnum.getMessage());
        this.respEnum = respEnum;
    }

    public PlatformServiceException(RespEnum respEnum, String msg){
        super(msg);
        this.respEnum = respEnum;
    }

    public PlatformServiceException(RespEnum respEnum, Throwable cause){
        super(respEnum.getMessage(), cause);
        this.respEnum = respEnum;
    }

}
