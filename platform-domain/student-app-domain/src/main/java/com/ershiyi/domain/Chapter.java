package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "COMMON_COURSE_CHAPTER")
@ApiModel(value="chapter", description = "章节")
public class Chapter {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 章节名称
     */
    @Column(name = "CHAPTERNAME")
    @ApiModelProperty(value="章节名称")
    private  String chapterName = "";
    /**
     * 课程ID
     */
    @Column(name = "courseId")
    @ApiModelProperty(value="课程ID")
    private  Integer courseId ;
    /**
     * 是否国标
     */
    @Column(name = "CHAPTERNAME")
    @ApiModelProperty(value="是否国标")
    private  Integer isgb;
    /**
     * 学校编号
     */
    @Column(name = "schoolId")
    @ApiModelProperty(value="学校编号")
    private  Integer schoolId;
    /**
     * 知识点id
     */
    @Column(name = "knowledgeId")
    @ApiModelProperty(value="节点id")
    private  String knowId;


    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }
}
