package com.ershiyi.domain.entity;

import lombok.Data;


/**
 * @Description: 消息列表详细实体类
 * @author: zss98
 * @date: 2020-07-30 10:42
 * @version: 1.0
 */
@Data
public class MessageInfo {
    private int messageId;   // 表记录id
    private String message = ""; // 消息内容
    private String sendTime = "";  // 发送时间
    private String sendId = "";  // 发送者编号
    private String studenterId = ""; // 学生编号
    private int userType;   // 发送者类型，0为学生，1为老师，2为家长，3为校长
    private String guid = ""; // 用户表id
    private String sendImageUrl = ""; // 消息发送者头像url
    private String sendName = ""; // 消息发送者昵称
    private String acceptImageUrl = ""; // 消息接受者头像url
    private String acceptName = "";  // 消息接受者昵称
}
