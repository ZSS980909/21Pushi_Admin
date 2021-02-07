package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="course_select", description = "知识主页上根据知识点课程查询")
public class Course_Select {
    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     *课程名称
     */
    @ApiModelProperty(value="课程名称")
    private  String courseName;
    /**
     *图像地址
     */
    @ApiModelProperty(value="图像地址")
    private  String picture;
    /**
     * 章节
     */
    @ApiModelProperty(value="章节")
    private  String chapterName;
    /**
     *知识点内容
     */
    @ApiModelProperty(value="知识点名称")
    private  String knowName;
    /**
     * 是否已经购买此课程
     */
    @ApiModelProperty(value="是否购买此课程")
    private  String isbuycourse;
    /**
     * 知识点内容
     */
    @ApiModelProperty(value="知识点内容")
    private  String content;
    /**
     * 版本
     */
    @ApiModelProperty(value="版本名称")
    private  String editionName;

    /**
     * 课程id
     * @return
     */
    @ApiModelProperty(value="课程id")
    private  String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getIsbuycourse() {
        return isbuycourse;
    }

    public void setIsbuycourse(String isbuycourse) {
        this.isbuycourse = isbuycourse;
    }

}
