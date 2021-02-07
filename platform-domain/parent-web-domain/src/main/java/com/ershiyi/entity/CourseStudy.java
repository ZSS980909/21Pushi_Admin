package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 今日学习课程
 * @author: zss98
 * @date: 2020-12-06 10:38
 * @version: 1.0
 */
@Data
public class CourseStudy {
    private int courseId;  // 科目id
    private String courseName;  // 课程名称
    private String picture;  // 图片链接
    private int countKnow;  // 知识点数量
    private int finishKnow;  // 已学习的知识点数量
    private String levelLabel = "";  // 等级标签
}
