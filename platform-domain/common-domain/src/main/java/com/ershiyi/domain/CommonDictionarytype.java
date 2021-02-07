package com.ershiyi.domain;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表 COMMON_DICTIONARYTYPE
 *
 * @author zaz
 * @date 2020-03-25
 */
@ApiModel(value="com.ershiyi.domain.CommonDictionarytype")
@Table(name = "COMMON_DICTIONARYTYPE")
@Data
public class CommonDictionarytype extends AbstractBaseDomain implements Serializable {


    @ApiModelProperty(value="")
    private String typeid;

    @ApiModelProperty(value="")
    private String typename;

    @ApiModelProperty(value="")
    private String viewname;

    /**
    * 是否可编辑
    */
    @ApiModelProperty(value="是否可编辑")
    private BigDecimal ifedit;

    /**
    * 排序
    */
    @ApiModelProperty(value="排序")
    private BigDecimal sort;


}