package com.ershiyi.domain;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表 COMMON_DICTIONARY
 *
 * @author zaz
 * @date 2020-03-18
 */
@ApiModel(value="com.ershiyi.domain.CommonDictionary")
@Table(name = "COMMON_DICTIONARY")
@Data
public class CommonDictionary  extends AbstractBaseDomain implements Serializable {

    @ApiModelProperty(value="")
    private String typeid;

    @ApiModelProperty(value="")
    private String codeid;

    @ApiModelProperty(value="")
    private String codename;

    @ApiModelProperty(value="")
    private String pyname;

    @ApiModelProperty(value="")
    private BigDecimal ifuse;

    @ApiModelProperty(value="")
    private BigDecimal sort;






}