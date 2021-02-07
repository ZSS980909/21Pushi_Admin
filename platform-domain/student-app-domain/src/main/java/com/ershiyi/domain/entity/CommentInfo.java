package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 评论详细信息
 * @author: zss98
 * @date: 2020-08-06 11:57
 * @version: 1.0
 */
@Data
public class CommentInfo {
    private Integer commentId; // 评论id
    private String message = ""; // 评论内容
    private String sendName = ""; // 评论者昵称
    private String guid = ""; // 评论者id
    private String sendUrl = ""; // 评论者头像url
    private int likeFlag = 0;  // 当前学生是否对该评论点赞
    private String sendTime; // 评论时间
    private Integer likes; // 评论点赞数
}
