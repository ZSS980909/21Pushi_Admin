package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 题目数据
 * @author: zss98
 * @date: 2020-08-25 17:06
 * @version: 1.0
 */
@Data
public class ResultQuestion {
    private Integer type;  // 题目类型
    private Integer questionId;   // 题目id
    private String question; // 题目内容
    private String KnowContentName;  // 相关知识点名称
    private String knowContentId; // 知识点内容id
    private List<String> options = new ArrayList<>();  // 选项
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
    private String KnowName;
    private String  KnowId;

    public String getKnowName() {
        return KnowName;
    }

    public void setKnowName(String knowName) {
        KnowName = knowName;
    }

    public String getKnowId() {
        return KnowId;
    }

    public void setKnowId(String knowId) {
        KnowId = knowId;
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

    public String getKnowContentName() {
        return KnowContentName;
    }

    public void setKnowContentName(String knowContentName) {
        KnowContentName = knowContentName;
    }

    public String getKnowContentId() {
        return knowContentId;
    }

    public void setKnowContentId(String knowContentId) {
        this.knowContentId = knowContentId;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }
}
