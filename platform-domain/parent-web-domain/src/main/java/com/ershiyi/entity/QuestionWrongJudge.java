package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 错误的判断题
 * @author: zss98
 * @date: 2020-11-03 15:05
 * @version: 1.0
 */
@Data
public class QuestionWrongJudge extends JudgeQuestion {
    private String studyTime; // 做题时间
    private String knowName; // 题目知识点名称
    private String fillAnswer;  // 学生选择选项
    private String knowId;  // 知识点id
    private String courseName;  // 课程名称
    private int subjectId;  // 科目id
}
