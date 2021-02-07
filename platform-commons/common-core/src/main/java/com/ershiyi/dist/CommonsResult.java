package com.ershiyi.dist;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 接口返回信息
 * @author: zss98
 * @date: 2020-11-07 10:48
 * @version: 1.0
 */
@Data
public class CommonsResult<T> implements Serializable {
    private Integer code;
    private Integer status;
    private String size;
    private Double version = 1.0d;
    private String msg;
    private T data;

    public CommonsResult(){

    }

    public CommonsResult(Integer code,String msg){
        this.code = code;
        this.status = 0;
        this.msg = msg;
        this.data = null;
    }

    public CommonsResult(Integer code,String msg, T data){
        this.code = code;
        this.status = 0;
        this.msg = msg;
        this.data = data;
    }
    public CommonsResult(Integer code, Integer status,Double version, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.version = version;
    }
    public CommonsResult(Integer code,Integer status,String size,Double version,String msg,T data){
        this.code = code;
        this.status = status;
        this.size = size;
        this.msg = msg;
        this.version = version;
        this.data = data;
    }

    public CommonsResult(Integer code,Integer status,Double version,String msg,T data){
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.version = version;
        this.data = data;
    }

}
