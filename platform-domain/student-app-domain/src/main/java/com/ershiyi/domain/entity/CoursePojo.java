package com.ershiyi.domain.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 课程表实体类
 * @author: zss98
 * @date: 2020-07-28 17:54
 * @version: 1.0
 */
@Data
public class  CoursePojo {
    private Integer courseId = 0;   // 课程id
    private String courseName  = "";    // 课程名称
    private String synopsis  = "";   // 课程介绍
    private Integer discussNumber = 0;  // 课程评论数
    private Integer starNumber = 0;  // 收藏数量
    private Integer pageViews = 0;  // 课程浏览数量
    private String picture  = "";  // 图片地址
    private String author = "";  // 作者姓名
    private String biography = "";   // 作者简介
    private String label = "";  // 课程标签
    private Integer integral;  // 课程所需积分
    private Integer creatorId = 0;  // 作者id
    private Integer subjectId = 0;  // 科目id
    private String subjectName;  // 科目名称
    private Integer chapterNumber = 0 ;  // 章节数量
    private Integer isPay = 0;  // 是否购买
    private Integer isCollect = 0;  // 是否收藏 0为未收藏，1为已收藏
    private List courseTime = new ArrayList<>(); // 上课时间
    private Integer isComplete = 0;  // 是否完成
}
