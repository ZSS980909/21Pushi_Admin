package com.ershiyi.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public abstract class AbstractBaseDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    private String guid;

    /**
     *
     */
    @ApiModelProperty(value="")
    private BigDecimal deleted;

    /**
     *
     */
    @ApiModelProperty(value="")
    private Date createDate;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String creatorId;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String creator;

    /**
     *
     */
    @ApiModelProperty(value="")
    private Date modifyDate;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String modifierid;

    /**
     *
     */
    @ApiModelProperty(value="")
    private String modifier;

    /**
     *
     */
    @ApiModelProperty(value="")
    private BigDecimal timestamp;


}
