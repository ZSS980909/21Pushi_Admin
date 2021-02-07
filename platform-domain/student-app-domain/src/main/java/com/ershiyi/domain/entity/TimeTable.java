package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 课表实体类
 * @author: zss98
 * @date: 2020-07-31 18:19
 * @version: 1.0
 */
@Data
public class TimeTable {
    private int curriculumId;  // 课表id
    private int courseId; // 课程id
    private String courseName = ""; // 课程名称
    private String planWeek = "";  // 课程计划星期
    private String planTime = "";  // 课程计划时间
}
