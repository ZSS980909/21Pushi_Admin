package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题检查记录明细domain
 *
 * @author mos
 * @since 2020/03/07
 */
@Data
@Table(name = "ISSUE_LISTDETAIL")
@ApiModel(value="IssueListDetail", description = "问题检查记录明细domain")
public class IssueListDetail extends AbstractBaseDomain{

    /** 记录主键 **/
    public static final String LISTGUID = "listguId";

    /**
     * 记录主键
     */
    @Column(name = "LISTGUID")
    @ApiModelProperty(value="记录主键")
    private String listguId;

    /**
     * 检查内容主键
     */
    @Column(name = "ITEMGUID")
    @ApiModelProperty(value="检查内容主键")
    private String itemguId;

    /**
     * 限时
     */
    @Column(name = "LIMITDT")
    @ApiModelProperty(value="限时")
    private Date limitDate;

    /**
     * 责任人
     */
    @Column(name = "PERSONLIABLE")
    @ApiModelProperty(value="责任人")
    private String personLiable;

    /**
     * 问题描述
     */
    @Column(name = "PROBLEMDESCRIPE")
    @ApiModelProperty(value="问题描述")
    private String problemDescripe;

    /**
     * 状态
     */
    @Column(name = "\"STATUS\"")
    @ApiModelProperty(value="状态")
    private BigDecimal status;

    /**
     * 整改措施
     */
    @Column(name = "RECTIFYMEASURES")
    @ApiModelProperty(value="整改措施")
    private String rectifyMeasures;

    /**
     * 整改结果
     */
    @Column(name = "RECTIFYRESULT")
    @ApiModelProperty(value="整改结果")
    private String rectifyResult;

    /**
     * 反馈人
     */
    @Column(name = "FEEDBACKPERSON")
    @ApiModelProperty(value="反馈人")
    private String feedbackPerson;

    /**
     * 反馈时间
     */
    @Column(name = "FEEDBACKDT")
    @ApiModelProperty(value="反馈时间")
    private Date feedbackDate;

    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value="排序")
    private BigDecimal sort;

}