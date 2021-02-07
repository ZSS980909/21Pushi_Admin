package com.ershiyi.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返回课程数据封装类
 */
public class Common_Question {

    @ApiModelProperty(value="主键id")
    private  Integer Id;
    @ApiModelProperty(value="课程题型")
    private  String questionType;
    @ApiModelProperty(value="知识点id")
    private  String knowId;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getKnowledgeId() {
        return knowId;
    }

    public void setKnowledgeId(String knowId) {
        this.knowId = knowId;
    }
}

