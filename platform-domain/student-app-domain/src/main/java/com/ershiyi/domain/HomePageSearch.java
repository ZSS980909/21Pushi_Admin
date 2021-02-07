package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "COMMON_COURSE_INTEGRAL")
@ApiModel(value="HomePageSearch", description = "首页搜索")
public class    HomePageSearch extends AbstractBaseDomain {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 课程名称
     */
    @Column(name = "CURRICULUM")
    @ApiModelProperty(value="课程名称")
    private  String courseName;
    /**
     * 学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="学生版编号")
    private  String studenterId;
    /**
     * 作者
     */
    @Column(name = "AUTHOR")
    @ApiModelProperty(value="作者")
    private  String author;
    /**
     * 介绍
     */
    @Column(name = "SYNOPSIS")
    @ApiModelProperty(value="介绍")
    private  String synopsis;
    /**
     * 图像"
     */
    @Column(name = "PICTURE")
    @ApiModelProperty(value="图像")
    private  String picture;
    /**
     * 积分
     */
    @Column(name = "INTEGRAL")
    @ApiModelProperty(value="积分")
    private  String integral;
    /**
     * 浏览数
     */
    @ApiModelProperty(value="浏览数")
    private  String pageViews;
    /**
     * 点赞数
     */
    @ApiModelProperty(value="收藏数")
    private  String starNumber;
    /**
     * 评论id
     */
    @Column(name = "DISCUSSID")
    @ApiModelProperty(value="评论id")
    private  String commentId;

    /**
     * 当天开始时间
     */
    @Column(name = "STIME")
    @ApiModelProperty(value="当天开始时间")
    private  String startTime;

    /**
     * 当天结束时间
     */
    @Column(name = "ETIME")
    @ApiModelProperty(value="当天结束时间")
    private  String endTime;
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
    /**
     * 章节数
     */
    @Column(name = "CHAPTNUMBER")
    @ApiModelProperty(value="章节数")
    private String chapterNumber;

}
