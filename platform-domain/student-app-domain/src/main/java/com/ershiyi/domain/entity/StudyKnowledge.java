package com.ershiyi.domain.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-11-12 09:41
 * @version: 1.0
 */
@Data
public class StudyKnowledge {
    private Long useTime;  // 使用时间
    private String courseId;  // 课程id
    private String chapterId;  // 章节id
    private String knowId; // 知识点id
    private Integer isQuery = 0;  // 是否有疑问
    private Integer subjectId; // 科目id
    private String planTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());  // 计划下次学习时间
    private String knowContentId; // 知识点内容id
    private String studenterId;  // 学生编号
    private String startTime; // 开始时间
    private String endTime; // 结束时间

    public Long getUseTime() {
        return useTime;
    }

    public void setUseTime(Long useTime) {
        this.useTime = useTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public Integer getIsQuery() {
        return isQuery;
    }

    public void setIsQuery(Integer isQuery) {
        this.isQuery = isQuery;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getKnowContentId() {
        return knowContentId;
    }

    public void setKnowContentId(String knowContentId) {
        this.knowContentId = knowContentId;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
