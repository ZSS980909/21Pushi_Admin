package com.ershiyi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 抽象认证DTO
 *
 * @author Liy
 * @since 2020/05/29
 */
@Data
public abstract class AbstractAuthDTO implements Serializable {

    @ApiModelProperty(value = "系统")
    private String sys;

    @ApiModelProperty(value = "设备")
    private String device;


}
