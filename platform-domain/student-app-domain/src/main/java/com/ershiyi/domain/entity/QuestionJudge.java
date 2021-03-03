package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 判断题实体类
 * @author: zss98
 * @date: 2020-08-10 12:05
 * @version: 1.0
 */
@Data
public class QuestionJudge {
    private Integer type;  // 题目类型
    private Integer questionId; // 题目id
    private String question = ""; // 题目内容
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
    private String knowId = "";
    private String knowName = "";

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public String getKnowName() {
        return knowName;
    }

    public void setKnowName(String knowName) {
        this.knowName = knowName;
    }
}
