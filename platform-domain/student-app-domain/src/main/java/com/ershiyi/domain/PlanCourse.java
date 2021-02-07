package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 计划课程
 */
@Data
@Table(name = "COMMON_COURSE_PLAN")
@ApiModel(value="plan", description = "计划课程")
public class PlanCourse {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 学生编号
     */
    @Column(name = "STUDENTERID")
    @ApiModelProperty(value="学生编号")
    private  String studenterid;
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  String courseid;
    /**
     * 类型
     */
    @Column(name = "PLANTYPE")
    @ApiModelProperty(value="类型(0计划中,1计划完成,-1未计划)")
    private  String plantype;
    /**
     * 是否是学生自己添加
     */
    @Column(name = "ISCLANIM")
    @ApiModelProperty(value="是否是老师要求 0学生自加  1老师要求")
    private  String isclaim;
    /**
     * 下次计划学习时间
     */
    @Column(name = "PLANDT")
    @ApiModelProperty(value="下次计划学习时间")
    private String plandt;
    /**
     * 初次计划学习时间
     */
    @Column(name = "SPLANDT")
    @ApiModelProperty(value="初次计划学习时间")
    private String splandt;

}
