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
 * 问题检查内容domain
 *
 * @author mos
 * @since 2020/03/17
 */
@Data
@Table(name = "ISSUE_INSPECTITEM")
@ApiModel(value="IssueInspectItem", description = "问题检查内容domain")
public class IssueInspectItem extends AbstractBaseDomain{

    /**  **/
    public static final String PGUID = "pGuid";

    /**
     * 内容
     */
    @Column(name = "ITEMNAME")
    @ApiModelProperty(value="内容")
    private String itemName;

    /**
     * 父节点
     */
    @Column(name = "PGUID")
    @ApiModelProperty(value="父节点")
    private String pGuid;

    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value="排序")
    private BigDecimal sort;

}