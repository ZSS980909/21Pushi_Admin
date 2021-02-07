package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 评论信息
 * @author: zss98
 * @date: 2020-12-23 17:29
 * @version: 1.0
 */
@Data
public class CommentInfo {
    private String commentId;  // 评论id
    private String message;  // 评论内容
    private int assess;  // 评分
    private int userType;  // 用户类型
    private String userId;  // 用户编号
    private String nickName;  // 用户昵称
    private String userImage;  // 用户头像
    private int learnTime;  // 学习时间
    private int isLike; // 是否点赞
    private int likeNumber;  // 点赞数量
}
