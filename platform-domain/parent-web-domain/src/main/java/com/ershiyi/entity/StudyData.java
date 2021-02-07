package com.ershiyi.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 学习时间
 * @author: zss98
 * @date: 2020-12-07 21:42
 * @version: 1.0
 */
@Data
public class StudyData {
    private Long allStudyLength = 0l;  // 总学习时长
    private Long nowStudyLength = 0l;  // 今日学习时长
    private List<MonthsToDay> studyData;  // 学习时长记录
}
