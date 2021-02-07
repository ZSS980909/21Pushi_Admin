package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-10-25 11:36
 * @version: 1.0
 */
@Data
public class StudyRecord {
    private Integer recordId;  // 记录id
    private String knowledgeContentId; // 知识点内容id
    private Integer courseId;  // 课程id
    private Integer chapterId; // 章节id
    private String knowName;  // 知识点名称
    private Integer knowledgeId;  // 知识点id
}
