package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题检查记录domain
 *
 * @author mos
 * @since 2020/03/07
 */
@Data
@Table(name = "ISSUE_LIST")
@ApiModel(value="IssueList", description = "问题检查记录domain")
public class IssueList extends AbstractBaseDomain{

    /** 监所编号 **/
    public static final String PRISONID = "prisonId";
    /** 检查单位 **/
    public static final String INSPECTUNIT = "inspectUnit";
    /** 检查类型 **/
    public static final String INSPECTTYPE = "inspectType";
    /** 状态 **/
    public static final String STATUS = "status";

    /**
     * 监所编号
     */
    @Column(name = "PRISONID")
    @ApiModelProperty(value="监所编号")
    private String prisonId;

    /**
     *
     */
    @Column(name = "INSPECTNUM")
    @ApiModelProperty(value="")
    private String inspectNum;

    /**
     * 检查类型
     */
    @Column(name = "INSPECTTYPE")
    @ApiModelProperty(value="检查类型")
    private String inspectType;

    /**
     * 检查子类型
     */
    @Column(name = "INSPECTSUBTYPE")
    @ApiModelProperty(value="检查子类型")
    private String inspectsubType;

    /**
     * 检查单位
     */
    @Column(name = "INSPECTUNIT")
    @ApiModelProperty(value="检查单位")
    private String inspectUnit;

    /**
     * 检查时间
     */
    @Column(name = "INSPECTDATE")
    @ApiModelProperty(value="检查时间")
    private Date inspectDate;

    /**
     * 检查人员ID
     */
    @Column(name = "INSPECTPERSONID1")
    @ApiModelProperty(value="检查人员ID")
    private String inspectPersonId1;

    /**
     * 检查人员
     */
    @Column(name = "INSPECTPERSON1")
    @ApiModelProperty(value="检查人员")
    private String inspectPerson1;

    /**
     * 其余检查人员
     */
    @Column(name = "INSPECTPERSON2")
    @ApiModelProperty(value="其余检查人员")
    private String inspectPerson2;

    /**
     * 发送状态--0：未发送，1：已发送，2：正在处理中，9：已处理
     */
    @Column(name = "\"STATUS\"")
    @ApiModelProperty(value="发送状态", notes = "0：未发送，1：已发送，2：正在处理中，9：已处理")
    private BigDecimal status;

    /**
     * 记录人
     */
    @Column(name = "INPUTPERSON")
    @ApiModelProperty(value="记录人")
    private String inputPerson;

    /**
     * 记录时间
     */
    @Column(name = "INPUTDT")
    @ApiModelProperty(value="记录时间")
    private Date inputDate;

}