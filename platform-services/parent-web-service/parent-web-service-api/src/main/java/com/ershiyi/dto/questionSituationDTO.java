package com.ershiyi.dto;

import lombok.Data;

@Data
public class questionSituationDTO {
    private  String errorCount;     //错误数量
    private  String correctCount;  //正确数量
//    private  String accuracy;   //正确率
//    private  String errorRate;  //错误率
    private  String perrorCount;//全部学生错误数量
    private  String pcorrectCount; //全部学生正确数量
//    private  String paccuracy;  //平均正确率
//    private  String perrorRate;//平均错误率
    private  String numberCount; //该学习知识点数量
    private  String pNumberCount; //所有学生学习知识点数量
    private  String sumNumber;
    private  String studenterId;//学生编号
    private  String startTime; //时间
    private String  subjectName;//科目名称
    private  String  id; //id
    private String  usetime;//使用时间

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(String sumNumber) {
        this.sumNumber = sumNumber;
    }

    public String getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(String numberCount) {
        this.numberCount = numberCount;
    }

    public String getpNumberCount() {
        return pNumberCount;
    }

    public void setpNumberCount(String pNumberCount) {
        this.pNumberCount = pNumberCount;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(String errorCount) {
        this.errorCount = errorCount;
    }

    public String getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(String correctCount) {
        this.correctCount = correctCount;
    }

//    public String getAccuracy() {
//        return accuracy;
//    }
//
//    public void setAccuracy(String accuracy) {
//        this.accuracy = accuracy;
//    }
//
//    public String getErrorRate() {
//        return errorRate;
//    }
//
//    public void setErrorRate(String errorRate) {
//        this.errorRate = errorRate;
//    }

    public String getPerrorCount() {
        return perrorCount;
    }

    public void setPerrorCount(String perrorCount) {
        this.perrorCount = perrorCount;
    }

    public String getPcorrectCount() {
        return pcorrectCount;
    }

    public void setPcorrectCount(String pcorrectCount) {
        this.pcorrectCount = pcorrectCount;
    }

//    public String getPaccuracy() {
//        return paccuracy;
//    }
//
//    public void setPaccuracy(String paccuracy) {
//        this.paccuracy = paccuracy;
//    }
//
//    public String getPerrorRate() {
//        return perrorRate;
//    }
//
//    public void setPerrorRate(String perrorRate) {
//        this.perrorRate = perrorRate;
//    }
}
