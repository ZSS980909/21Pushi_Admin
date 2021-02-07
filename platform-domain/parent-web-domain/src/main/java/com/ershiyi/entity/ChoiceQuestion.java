package com.ershiyi.entity;


import lombok.Data;


/**
 * @Description: 单选题
 * @author: zss98
 * @date: 2020-08-08 16:39
 * @version: 1.0
 */
@Data
public class ChoiceQuestion {
    private Integer type;  // 题目类型
    private int questionId;   // 题目id
    private String question = ""; // 题目内容
    private String optionA = ""; // A选项
    private String optionB = ""; // B选项
    private String optionC = ""; // C选项
    private String optionD = ""; // D选项
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
}
