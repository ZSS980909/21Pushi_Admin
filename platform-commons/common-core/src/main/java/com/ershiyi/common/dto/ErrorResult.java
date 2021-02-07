package com.ershiyi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResult extends AbstractBaseResult{

    @Getter
    private String data;

    public ErrorResult(int code, String msg, String data){
        super(code, msg, null);
        this.data = data;
    }
}
