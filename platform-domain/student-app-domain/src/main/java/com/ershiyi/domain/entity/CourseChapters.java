package com.ershiyi.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 课程章节信息和评论信息
 * @author: zss98
 * @date: 2020-08-06 11:15
 * @version: 1.0
 */
@Data
public class CourseChapters implements Serializable {
    private Integer courseId;  // 课程id
    // private String name; // 课程名称
    private Integer subjectId;  // 课程科目id
    private List<ChapterInfo> chapters  = new ArrayList<>(); //章节信息
}
