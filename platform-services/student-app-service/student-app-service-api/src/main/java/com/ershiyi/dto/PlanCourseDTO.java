package com.ershiyi.dto;

import com.ershiyi.common.dto.AbstractBaseDTO;
import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 计划中课程(提醒任务中)
 */
@Data
@ApiModel(value = "PlanCourseDTO", description = "计划中课程dto(提醒任务中)")
public class PlanCourseDTO  extends AbstractBaseDomain {
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  String courseid;
    /**
     * 学生编号
     */
    @Column(name = "STUDENTERID")
    @ApiModelProperty(value="学生编号")
    private  String studenterid;
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
    private Date plandt;
    /**
     * 初次计划学习时间
     */
    @Column(name = "SPLANDT")
    @ApiModelProperty(value="初次计划学习时间")
    private Date splandt;
    /**
     * 学科
     */
    @Column(name = "SUBJECTNAME")
    @ApiModelProperty(value="学科")
    private Date subjectname;
    /**
     * 是否设置提醒
     */
    @Column(name = "ISREMIND")
    @ApiModelProperty(value="是否设置提醒")
    private Date isremind;
}
