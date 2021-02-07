package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 所有城市
 */
@Data
@Table(name = "COMMON_CITY")
@ApiModel(value="City", description = "城市")
public class City {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 城市名称
     */
    @Column(name = "CITYNAME")
    @ApiModelProperty(value="城市名称")
    private String cityname;
    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value="排序")
    private  String sort;
}
