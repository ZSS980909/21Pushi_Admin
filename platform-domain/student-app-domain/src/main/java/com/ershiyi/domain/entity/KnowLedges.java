package com.ershiyi.domain.entity;


import lombok.Data;

import java.util.Date;

/**
 * @Description: 知识点实体类
 * @author: zss98
 * @date: 2020-07-29 11:26
 * @version: 1.0
 */
@Data
public class KnowLedges {
    private Integer collectId= 0; // 收藏id
    private Integer knowId = 0 ; // 知识点id
    private Integer subjectId = 0; // 学科id
    private String courseName = "" ; // 课程名称
    private String chapterName = ""; // 章名称
    private String knowName = "";  // 知识点名称
    private Integer courseId = 0; // 课程id
    private Integer chapterId = 0;  // 章节id
    private String studyTime = ""; // 练习时间
}
