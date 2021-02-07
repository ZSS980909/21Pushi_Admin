package com.ershiyi.domain.entity;


import lombok.Data;


/**
 * @Description: 单选题
 * @author: zss98
 * @date: 2020-08-08 16:39
 * @version: 1.0
 */
@Data
public class QuestionChoice extends QuestionJudge{
    private String optionA = ""; // A选项
    private String optionB = ""; // B选项
    private String optionC = ""; // C选项
    private String optionD = ""; // D选项
    private String isRelevanceFinish;//推送功能次数
}
