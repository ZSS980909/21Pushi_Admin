package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 错题库
 * @author: zss98
 * @date: 2020-11-03 14:57
 * @version: 1.0
 */
@Data
public class ResultWrongQuestion extends ResultQuestion {
    private String studyTime; // 做题时间
    private String knowName; // 题目知识点名称
    private String fillAnswer;  // 学生选择选项
    private String courseName;  // 课程名称
    private int subjectId;  // 科目id
}
