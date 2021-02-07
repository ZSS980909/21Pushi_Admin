package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 消息发送者相信信息
 * @author: zss98
 * @date: 2020-07-30 10:46
 * @version: 1.0
 */
@Data
public class MessageUserInfo {
    private String guid = ""; // 用户表id
    private String imageUrl = ""; // 消息发送者头像url
    private String name = ""; // 消息发送者昵称
}
