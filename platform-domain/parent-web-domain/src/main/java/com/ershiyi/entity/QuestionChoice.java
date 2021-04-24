package com.ershiyi.entity;


import lombok.Data;

/**
 * @Description: 单选题
 * @author: zss98
 * @date: 2020-08-08 16:39
 * @version: 1.0
 */
@Data
public class QuestionChoice {
    private String knowledgeId = "";
    private String knowledgeName = "";
    private Integer type;  // 题目类型
    private Integer questionId;   // 题目id
    private String question = ""; // 题目内容
    private String optionA = ""; // A选项
    private String optionB = ""; // B选项
    private String optionC = ""; // C选项
    private String optionD = ""; // D选项
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
    private String knowId;//相关联知识点
    private String knowName;//知识点内容名称

    private String isRelevanceFinish;//推送功能次数

    public String getIsRelevanceFinish() {
        return isRelevanceFinish;
    }

    public void setIsRelevanceFinish(String isRelevanceFinish) {
        this.isRelevanceFinish = isRelevanceFinish;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
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
