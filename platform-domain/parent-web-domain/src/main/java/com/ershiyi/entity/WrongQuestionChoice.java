package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 错误题目
 * @author: zss98
 * @date: 2020-11-03 15:03
 * @version: 1.0
 */
@Data
public class WrongQuestionChoice extends QuestionChoice {
    private String fillAnswer; // 学生选择选项
    private String studyTime;  // 学生做题时间
    private String knowName; // 题目知识点名称
    private String courseName;  // 课程名称
    private int subjectId;  // 科目id
    private String subjectImgUrl; //科目图片路径

}
