package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 课程信息实体类
 * @author: zss98
 * @date: 2020-07-31 14:43
 * @version: 1.0
 */
@Data
public class CourseInfo {
    private Integer courseId;  // 课程id
    private String courseName = "";  // 课程名称
    private Integer subjectId;  // 科目id
}
