package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * 学生学习时间实体类
 * 分别展示一周内的数据
 * @author zss98
 */
@Data
public class StudentStudyTime {
    private Integer today_StudyTime;
    private Integer average_toDay;
    private Integer yesterday_StudyTime;
    private Integer average_YesterDay;
    private Integer twoDaysAgo_StudyTime;
    private Integer average_twoDaysAgo;
    private Integer threeDaysAgo_StudyTime;
    private Integer average_threeDaysAgo;
    private Integer fourDaysAgo_StudyTime;
    private Integer average_fourDaysAgo;
    private Integer fiveDaysAgo_StudyTime;
    private Integer average_fiveDaysAgo;
    private Integer sixDaysAgo_StudyTime;
    private Integer average_sixDaysAgo;
}
