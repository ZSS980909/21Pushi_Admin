package com.ershiyi.domain.entity;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * 学生试题正确率实体类
 * @author zss98
 */
@Data
public class QuestionsAccuracy {
    private String startTime = "";    // 开始时间
    private String studenterId = "";  // 学生编号
    private Integer subjectId;   // 科目id
    private Long useTime ;        // 使用时间
    private int correct;         // 答题正确与否
}
