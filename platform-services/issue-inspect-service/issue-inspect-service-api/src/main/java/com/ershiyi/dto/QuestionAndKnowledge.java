package com.ershiyi.dto;

import com.ershiyi.domain.AbstractBaseDomain;

public class QuestionAndKnowledge extends AbstractBaseDomain {
    private String  studenterId;  //学生编号
    private String  thisPushDt;   //本次推送时间
    private String  nextPushDt;     //下次推送时间
    private String  statics;     //状态
    private String  createDt;
    private String  plushContentId;   //知识点id
    private String  plushFrequency;   //推送次数
    private String  pushType;   //推送类型
    private String  chapterId;  //章节id 已废弃
    private String  courseId;    //课程id
    private String  uniqueCode;     //推送对象的唯一编码值
    private String  questionType;       //题目类型
    private String  knowledgeName;  //知识点名称
    private String  knowledgetext;  //
    private String  courseName;  //课程名称
    private String plushId;  //推送记录id

    public String getPlushId() {
        return plushId;
    }

    public void setPlushId(String plushId) {
        this.plushId = plushId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgetext() {
        return knowledgetext;
    }

    public void setKnowledgetext(String knowledgetext) {
        this.knowledgetext = knowledgetext;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getThisPushDt() {
        return thisPushDt;
    }

    public void setThisPushDt(String thisPushDt) {
        this.thisPushDt = thisPushDt;
    }

    public String getNextPushDt() {
        return nextPushDt;
    }

    public void setNextPushDt(String nextPushDt) {
        this.nextPushDt = nextPushDt;
    }

    public String getStatics() {
        return statics;
    }

    public void setStatics(String statics) {
        this.statics = statics;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getPlushContentId() {
        return plushContentId;
    }

    public void setPlushContentId(String plushContentId) {
        this.plushContentId = plushContentId;
    }

    public String getPlushFrequency() {
        return plushFrequency;
    }

    public void setPlushFrequency(String plushFrequency) {
        this.plushFrequency = plushFrequency;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
