package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 题目类型
 * @author: zss98
 * @date: 2020-10-31 10:02
 * @version: 1.0
 */
@Data
public class QuestionType {
    private Integer questionId;  // 题目id
    private String knowName;
    private int knowId;
    private Integer questionType;  // 题目类型
    private Integer courseId; //课程id
}
