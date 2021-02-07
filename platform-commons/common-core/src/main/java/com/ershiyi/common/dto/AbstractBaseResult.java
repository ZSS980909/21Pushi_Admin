package com.ershiyi.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public abstract class AbstractBaseResult<T> implements Serializable {
    private int code;
    private String msg;
    private T data;
}
