package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 课程计划实体类
 * @author: zss98
 * @date: 2020-08-03 10:20
 * @version: 1.0
 */
@Data
public class CurriculumPojo {
    private Integer curriculumId; // 课程表id
    private String studenterId = "";  // 学生编号
    private Integer courseId;    // 课程id
    private String    planTime = "";    // 计划时间
    private String planWeek = "";     // 计划星期
    private Integer knowledgeNumber = 0 ;  // 章节数量
    private Integer planNumber = 0;  // 计划学习节数

    public CurriculumPojo(){

    }

    @Override
    public String toString() {
        return "CurriculumPojo{" +
                "curriculumId=" + curriculumId +
                ", studenterId='" + studenterId + '\'' +
                ", courseId=" + courseId +
                ", planTime='" + planTime + '\'' +
                ", planWeek='" + planWeek + '\'' +
                ", knowledgeNumber=" + knowledgeNumber +
                ", planNumber=" + planNumber +
                '}';
    }

    public Integer getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Integer curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setstudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getPlanWeek() {
        return planWeek;
    }

    public void setPlanWeek(String planWeek) {
        this.planWeek = planWeek;
    }

    public CurriculumPojo(Integer curriculumId, String studenterId, Integer courseId, String planTime, String planWeek) {
        this.curriculumId = curriculumId;
        this.studenterId = studenterId;
        this.courseId = courseId;
        this.planTime = planTime;
        this.planWeek = planWeek;
    }
}
