package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用搜索,返回一些常见的参数
 */
@Data
@ApiModel(value="common_search", description = "通用搜索,返回一些常见的参数")
public class Common_Return {
    @ApiModelProperty(value="主键id")
    private  Integer Id;
    @ApiModelProperty(value="课程编号")
    private  String courseId;
    @ApiModelProperty(value="知识点id,,号分割")
    private  String knowId = "";
    @ApiModelProperty(value="课程名称")
    private  String courseName = "";
    @ApiModelProperty(value="科目")
    private  String subjectId;
    @ApiModelProperty(value="图片")
    private  String picture;
    @ApiModelProperty(value="知识点数量")
    private  String knowIdNumber;

    public String getKnowIdNumber() {
        return knowIdNumber;
    }

    public void setKnowIdNumber(String knowIdNumber) {
        this.knowIdNumber = knowIdNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
