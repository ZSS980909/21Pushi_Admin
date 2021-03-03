package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description:
 * @author: zss98
 * @date: 2021-02-02 16:36
 * @version: 1.0
 */
@Data
public class  ChapterMenu {
    private int chapterId;
    private String chapterName = "";
    private String knowContent = "";
    private int pid;
    private int isStudy = 0;
    private int subjectId;
    private int courseId;
    private int isLast;
    private int level;
    private int leftValue;
    private int rightValue;
}
