package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 知识点内容
 * @author: zss98
 * @date: 2020-08-07 16:38
 * @version: 1.0
 */
@Data
public class A_KnowContent {
    private String knowContentId;  // 知识点内容id
    private String prologue = ""; // 衔接语
    private String editionName = "";  // 版本名称
    private String content = ""; // 知识点内容
    private Integer studyTime ;  // 学习时间
    private  Integer knowId; //知识点id
    private  Integer editionId; //版本id
    private String knowledgeName;

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowContentId() {
        return knowContentId;
    }

    public void setKnowContentId(String knowContentId) {
        this.knowContentId = knowContentId;
    }

    public String getPrologue() {
        return prologue;
    }

    public void setPrologue(String prologue) {
        this.prologue = prologue;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }
}
