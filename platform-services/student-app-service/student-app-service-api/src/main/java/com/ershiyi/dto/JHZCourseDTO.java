package com.ershiyi.dto;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 今日待学课程(知识主页中)
 */
@ApiModel(value = "JHZCourseDTO", description = "今日待学课程(知识主页中)")
public class JHZCourseDTO extends AbstractBaseDomain {
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  Integer courseId;
    /**
     * 学生编号
     */
    @Column(name = "STUDENTERID")
    @ApiModelProperty(value="学生编号")
    private  String studenterId = "";
    /**
     * 下次计划学习时间
     */
    @Column(name = "PLANDT")
    @ApiModelProperty(value="下次计划学习时间")
    private String plandt = "";
    /**
     * 初次计划学习时间
     */
    @Column(name = "SPLANDT")
    @ApiModelProperty(value="初次计划学习时间")
    private String splandt = "";
    /**
     * 是否设置提醒
     */
    @Column(name = "ISREMIND")
    @ApiModelProperty(value="是否设置提醒")
    private Integer isremind = 0;
    /**
     *课程名称
     */
    @Column(name = "CURRICULUM")
    @ApiModelProperty(value="课程名称")
    private String courseName = "";
    /**
     *作者
     */
    @Column(name = "AUTHOR")
    @ApiModelProperty(value="作者")
    private String author = "";
    /**
     *简介
     */
    @Column(name = "SYNOPSIS")
    @ApiModelProperty(value="简介")
    private String synopsis = "";
    /**
     *学科id
     */
    @Column(name = "SUBJECTID")
    @ApiModelProperty(value="学科id")
    private Integer subjectId = 0;
    /**
     *图像路径
     */
    @Column(name = "PICTURE")
    @ApiModelProperty(value="图像路径")
    private String picture = "";
    /**
     *积分
     */
    @Column(name = "INTEGRAL")
    @ApiModelProperty(value="积分")
    private String integral = "";
    /**
     *章节数
     */
    @Column(name = "CHAPTERNUMBER")
    @ApiModelProperty(value="章节数")
    private Integer chapterNumber =0;

    /**
     *评论id
     */
    @Column(name = "DISCUSSID")
    @ApiModelProperty(value="评论id")
    private String commentId = "";

    /**
     *浏览数
     */

    @ApiModelProperty(value="浏览数")
    private Integer pageViews =0;

    /**
     *点赞数
     */

    @ApiModelProperty(value="收藏数")
    private Integer starNumber =0;


    /**
     *是否已经计划
     */
    @Column(name = "PLANTYPE")
    @ApiModelProperty(value="是否已经计划")
    private Integer planType = 0;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setstudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getPlandt() {
        return plandt;
    }

    public void setPlandt(String plandt) {
        this.plandt = plandt;
    }

    public String getSplandt() {
        return splandt;
    }

    public void setSplandt(String splandt) {
        this.splandt = splandt;
    }

    public Integer getIsremind() {
        return isremind;
    }

    public void setIsremind(Integer isremind) {
        this.isremind = isremind;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }
}
