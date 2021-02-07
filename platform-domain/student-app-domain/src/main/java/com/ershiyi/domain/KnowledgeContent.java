package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 知识点内容
 */
@Data
@Table(name = "COMMON_COURSE_KNOWLEDGE_CONTENT")
@ApiModel(value="content", description = "知识点")
public class KnowledgeContent {
    /**
     * id
     */
    @Column(name = "ID")
    @ApiModelProperty(value="知识点id")
    private  Integer knowContentId;
    /**
     * 知识点id
     */
    @Column(name = "KNOWLEDGEID")
    @ApiModelProperty(value="节点id")
    private  Integer knowId;
    /**
     * 知识点内容
     */
    @Column(name = "KNOWLEDGETEXT")
    @ApiModelProperty(value="知识点内容")
    private  String content;
    /**
     * 版本id
     */
    @Column(name = "EDITIONID")
    @ApiModelProperty(value="版本id")
    private  Integer editionId;
    /**
     * 版本名称
     */
    @Column(name = "EDITIONNAME")
    @ApiModelProperty(value="版本名称")
    private  String editionName;
    /**
     * 开场白
     */
    @Column(name = "PROLOGUE")
    @ApiModelProperty(value="开场白")
    private  String prologue;
    /**
     * 学习时长
     */
    @Column(name = "STUDYTIME")
    @ApiModelProperty(value="学习时长")
    private  String studyTime;
}
