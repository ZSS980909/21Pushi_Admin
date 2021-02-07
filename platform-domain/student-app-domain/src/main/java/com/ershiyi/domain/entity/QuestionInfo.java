package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 题目内容
 * @author: zss98
 * @date: 2020-12-31 11:45
 * @version: 1.0
 */
@Data
public class QuestionInfo {
    private String questionId;
    private int questionType;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
