package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.ershiyi-domain-IssueInspectUnit")
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "ISSUE_INSPECTUNIT")
public class IssueInspectUnit extends AbstractBaseDomain {
    /**
     * 单位名称
     */
    @Column(name = "UNITNAME")
    @ApiModelProperty(value="单位名称")
    private String unitname;

    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value="排序")
    private BigDecimal sort;

    @Column(name = "CREATEDT")
    @ApiModelProperty(value="")
    private Date createdt;

    @Column(name = "CREATORID")
    @ApiModelProperty(value="")
    private String creatorid;

    @Column(name = "MODIFYDT")
    @ApiModelProperty(value="")
    private Date modifydt;
}