package com.ershiyi.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 课程学习情况
 * @author: zss98
 * @date: 2020-12-07 18:22
 * @version: 1.0
 */
@Data
public class CourseInfo {
    private int courseId;  // 课程id
    private String courseName;   // 课程名称
    private Long studyLength;  // 学习时长
    private int finishKnows;  // 学完的知识点数量
    private int finishQuestions;  // 完成的题目数量
    private int wrongQuestions;  //  错误的题目数量
    private List<Know> knows = new ArrayList<>();  // 知识点列表
}
