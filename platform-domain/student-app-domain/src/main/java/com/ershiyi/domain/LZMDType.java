package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * 临阵磨刀pojo类
 */
@Data
@ApiModel(value="lzmdtype", description = "知识点")
public class LZMDType {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 总共知识点数量
     */
    @Column(name = "KNOWLEDGECOUNTNUMBER")
    @ApiModelProperty(value="总共知识点数量")
    private  String knowledgeCountNumber;
    /**
     * 已学的知识点数量
     */
    @Column(name = "KNOWLEDGESTUDYNUMBER")
    @ApiModelProperty(value="已学的知识点数量")
    private  String knowledgeStudyNumber;
    /**
     * 课程名称
     */
    @Column(name = "CURRICULUM")
    @ApiModelProperty(value="课程名称")
    private  String curriculum;
    /**
     * 总知识点
     */
    @Column(name = "COUNTKNOWLEDGEID")
    @ApiModelProperty(value="总知识点")
    private  String countknowledgeId;
    /**
     * 已学知识点
     */
    @Column(name = "STUDYKNOWLEDGEID")
    @ApiModelProperty(value="已学知识点")
    private  String studyknowledgeId;
    /**
     * 科目
     */
    @Column(name = "SUBJECTID")
    @ApiModelProperty(value="科目")
    private  String subjectId;
    /**
     * 课程复习百分比
     */
    @Column(name = "PERCENTAGE")
    @ApiModelProperty(value="课程复习百分比")
    private  String percentage;
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  String courseId;

    /**
     *课程图片
     * @return
     */
    @Column(name = "PICTURE")
    @ApiModelProperty(value="课程图片")
    private  String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnowledgeCountNumber() {
        return knowledgeCountNumber;
    }

    public void setKnowledgeCountNumber(String knowledgeCountNumber) {
        this.knowledgeCountNumber = knowledgeCountNumber;
    }

    public String getKnowledgeStudyNumber() {
        return knowledgeStudyNumber;
    }

    public void setKnowledgeStudyNumber(String knowledgeStudyNumber) {
        this.knowledgeStudyNumber = knowledgeStudyNumber;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getCountknowledgeId() {
        return countknowledgeId;
    }

    public void setCountknowledgeId(String countknowledgeId) {
        this.countknowledgeId = countknowledgeId;
    }

    public String getStudyknowledgeId() {
        return studyknowledgeId;
    }

    public void setStudyknowledgeId(String studyknowledgeId) {
        this.studyknowledgeId = studyknowledgeId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
