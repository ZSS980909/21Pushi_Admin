package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 题目数据
 * @author: zss98
 * @date: 2020-08-25 17:06
 * @version: 1.0
 */
@Data
public class ResultQuestion {
    private Integer type;  // 题目类型
    private Integer questionId;   // 题目id
    private String question; // 题目内容
    private List<String> options = new ArrayList<>();  // 选项
    private String correctOption = ""; // 正确答案
    private String resolving = "";  // 题目解析
    private String KnowName;
    private String  KnowId;
}
