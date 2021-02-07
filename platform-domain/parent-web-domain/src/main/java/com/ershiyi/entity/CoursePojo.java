package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 课程详细信息
 * @author: zss98
 * @date: 2020-12-08 10:23
 * @version: 1.0
 */
@Data
public class CoursePojo {
    private int courseId;  // 课程id
    private int subjectId;  // 科目id
    private String courseName;  // 课程名称
    private String picture;  // 图片链接
    private String author;  // 作者
    private String synopsis; // 课程介绍
    private String biography;  // 作者简介
    private String subjectName;  // 科目名称
    private int score;  // 课程所需积分
    private int knowNumber;  // 知识点数量
    private int Views;  // 浏览量
    private int isPay;  // 是否购买
    private int isCollection;  // 是否收藏
}
