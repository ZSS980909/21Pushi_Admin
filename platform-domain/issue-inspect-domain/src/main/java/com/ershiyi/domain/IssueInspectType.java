package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.ershiyi-domain-IssueInspectType")
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "ISSUE_INSPECTTYPE")
public class IssueInspectType extends AbstractBaseDomain {
    /**
     * 名称
     */
    @Column(name = "TYPENAME")
    @ApiModelProperty(value="名称")
    private String typename;

    /**
     * 父节点
     */
    @Column(name = "PGUID")
    @ApiModelProperty(value="父节点")
    private String pguid;

    @Column(name = "\"TYPE\"")
    @ApiModelProperty(value="")
    private BigDecimal type;

    @Column(name = "ITEMTYPE")
    @ApiModelProperty(value="")
    private BigDecimal itemtype;

    @Column(name = "SORT")
    @ApiModelProperty(value="")
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