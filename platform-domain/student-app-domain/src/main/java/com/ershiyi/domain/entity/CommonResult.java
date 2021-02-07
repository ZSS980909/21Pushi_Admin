package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-09-09 17:50
 * @version: 1.0
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
}
