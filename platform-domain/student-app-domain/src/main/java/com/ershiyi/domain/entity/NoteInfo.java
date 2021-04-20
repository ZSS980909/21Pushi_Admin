package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * 课堂笔记实体类
 */
@Data
public class NoteInfo {
    private int id;
    private String content;
    private int courseId;
    private int knowledgeId;
    private int likeNumber;
    private int isLike=0;
    private String sendTime;
    private String studenterId;
    private String sendName;
    private String sendImg;
}
