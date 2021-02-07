package com.ershiyi.entity;

/**
 * 返回答题正确数和错误数
 * @author zss98
 */
public class QuestionResult {
    private String subjectName = "";     // 科目名称
    private Integer correct = 0;   // 正确的个数
    private Integer wrong = 0;     // 错误的个数

    @Override
    public String toString() {
        return "QuestionResult{" +
                "subjectName='" + subjectName + '\'' +
                ", correct=" + correct +
                ", wrong=" + wrong +
                '}';
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public Integer getWrong() {
        return wrong;
    }

    public void setWrong(Integer wrong) {
        this.wrong = wrong;
    }

    public QuestionResult() {
    }

    public QuestionResult(String subjectName, Integer correct, Integer wrong) {
        this.subjectName = subjectName;
        this.correct = correct;
        this.wrong = wrong;
    }
}
