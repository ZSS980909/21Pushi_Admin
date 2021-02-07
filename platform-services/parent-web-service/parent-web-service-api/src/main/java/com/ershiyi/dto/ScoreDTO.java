package com.ershiyi.dto;

import lombok.Data;

@Data
public class ScoreDTO {
    private String  subjectName;
    private int score;
    private String createdt;
    private  String studenterId;
    private String subjectId;
    private String lastScore;

    public String getLastScore() {
        return lastScore;
    }

    public void setLastScore(String lastScore) {
        this.lastScore = lastScore;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
