package com.ershiyi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResult<T> extends AbstractBaseResult<T> {

    public SuccessResult(int code, String msg, T data) {
        super(code, msg, data);
    }
}
