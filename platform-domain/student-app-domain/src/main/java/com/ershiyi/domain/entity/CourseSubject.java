package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * 科目实体类
 */
@Data
public class CourseSubject {
    private int subjectId;    // 科目id
    private String subjectName = "";  // 科目名称
    private int deleted;    // 是否删除
    private int ifUser;    // 是否使用

    public CourseSubject() {
    }

    @Override
    public String toString() {
        return "CourseSubject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", deleted=" + deleted +
                ", ifuser=" + ifUser +
                '}';
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIfUser() {
        return ifUser;
    }

    public void setIfUser(int ifUser) {
        this.ifUser = ifUser;
    }
    public CourseSubject(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.deleted = 0;
        this.ifUser = 1;
    }

    public CourseSubject(int subjectId, String subjectName, int deleted, int ifUser) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.deleted = deleted;
        this.ifUser = ifUser;
    }
}
