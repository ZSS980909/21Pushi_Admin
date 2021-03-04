package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 答题前端实体类
 * @author: zss98
 * @date: 2020-08-13 18:09
 * @version: 1.0
 */
@Data
public class Correct {
    private String questionId;  // 题目id
    private String studenterId; // 学生编号
    private String knowId;  // 知识点id
    private String startdt; // 开始时间
    private String enddt; // 结束时间
    private String questionType;  // 题目类型
    private Long useTime;  // 使用时间
    private Integer correct; // 答题是否正确
    private String answer;  // 答案
    private String courseId;  //  课程id
    private String fillAnswer;  // 学生选择的选项
    private Integer isQuery; // 是否有疑问
    private String sendType;//推送类型  1表示知识点 6题目
    private Float accuracy; //正确率
    private int plushFrequency =0; //推送次数  默认0
    private String plushId;//推送记录id

    public int getPlushFrequency() {
        return plushFrequency;
    }

    public void setPlushFrequency(int plushFrequency) {
        this.plushFrequency = plushFrequency;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public String getStartdt() {
        return startdt;
    }

    public void setStartdt(String startdt) {
        this.startdt = startdt;
    }

    public String getEnddt() {
        return enddt;
    }

    public void setEnddt(String enddt) {
        this.enddt = enddt;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public Integer getCorrect() {
        return correct;
    }

    public void setCorrect(Integer correct) {
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getFillAnswer() {
        return fillAnswer;
    }

    public void setFillAnswer(String fillAnswer) {
        this.fillAnswer = fillAnswer;
    }

    public Integer getIsQuery() {
        return isQuery;
    }
}
