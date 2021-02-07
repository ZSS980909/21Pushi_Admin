package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@ApiModel(value="com.ershiyi-domain-IssueListAttach")
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "ISSUE_LISTATTACH")
public class IssueListAttach extends AbstractBaseDomain {
    /**
     * 记录详情主键
     */
    @Column(name = "LISTDETAILGUID")
    @ApiModelProperty(value="记录详情主键")
    private String listdetailguid;

    /**
     * 附件名称
     */
    @Column(name = "ATTACHNAME")
    @ApiModelProperty(value="附件名称")
    private String attachname;

    /**
     * 附件主键
     */
    @Column(name = "ATTACHGUID")
    @ApiModelProperty(value="附件主键")
    private String attachguid;
}