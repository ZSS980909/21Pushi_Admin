package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 课程公告
 */
@Data
@Table(name = "COMMON_COURSE_NOTICE")
@ApiModel(value="plan", description = "计划课程")
public class Notice {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 公告类型名称
     */
    @Column(name = "NOTICETYPENAME")
    @ApiModelProperty(value="公告类型名称")
    private  String noticetypename;
    /**
     * 公告内容
     */
    @Column(name = "NOTICECONTENT")
    @ApiModelProperty(value="公告内容")
    private  String noticecontent;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private  String createdt;
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  String courseid;
    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  String deleted;
}
