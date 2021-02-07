package com.ershiyi.dto;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "ExamDTO", description = "考试模块实体类")
public class ExamDTO extends AbstractBaseDomain {
    private String  courseId;
    private String  chapterId;
    private String knowledgeId;
    private String chapterpinjieId; //章节下的知识点拼接的id
    private String  knowledgeContentId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getChapterpinjieId() {
        return chapterpinjieId;
    }

    public void setChapterpinjieId(String chapterpinjieId) {
        this.chapterpinjieId = chapterpinjieId;
    }

    public String getKnowledgeContentId() {
        return knowledgeContentId;
    }

    public void setKnowledgeContentId(String knowledgeContentId) {
        this.knowledgeContentId = knowledgeContentId;
    }
}
