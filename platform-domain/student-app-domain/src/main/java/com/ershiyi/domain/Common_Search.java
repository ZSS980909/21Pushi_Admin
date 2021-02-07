package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用搜索,传送一些常见的参数
 */
@Data
@ApiModel(value="common_search", description = "通用搜索,传送一些常见的参数")
public class Common_Search {
    @ApiModelProperty(value="主键id")
    private  Integer Id;
    @ApiModelProperty(value="学生编号")
    private  String studenterId;

}
