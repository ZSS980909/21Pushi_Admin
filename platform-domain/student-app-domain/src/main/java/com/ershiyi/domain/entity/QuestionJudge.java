package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 判断题实体类
 * @author: zss98
 * @date: 2020-08-10 12:05
 * @version: 1.0
 */
@Data
public class QuestionJudge {
    private Integer type;  // 题目类型
    private Integer questionId; // 题目id
    private String question = ""; // 题目内容
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
    private String knowId = "";
    private String knowName = "";

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }
}
