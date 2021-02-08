package com.ershiyi.dto;

import lombok.Data;

/**
 * @Description: 学习记录实体类
 * @author: zss98
 * @date: 2021-02-07 16:32
 * @version: 1.0
 */
@Data
public class StudyRecordDTO {
    private String studenterId;  // 学生编号
    private int courseId;  // 课程id
    private int knowId;  // 知识点id
    private long startTime = 0l;  // 开始时间
    private long endTime = 0;  // 结束时间
    private int leftValue;
    private int rightValue;
    private long useTime;
    private int flag;
    private int level;
}
