package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 学习记录实体类
 * @author: zss98
 * @date: 2020-07-29 17:26
 * @version: 1.0
 */
@Data
public class IdeaRecord {
    private Integer ideaId ; // 知识点id
    private Integer subjectId;  // 科目id
    private String idea = ""; // 学生学习记录内容
    private String recordTime = ""; // 记录时间
}
