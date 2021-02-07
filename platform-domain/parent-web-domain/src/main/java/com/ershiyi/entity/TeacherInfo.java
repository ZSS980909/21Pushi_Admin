package com.ershiyi.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 教师信息
 * @author: zss98
 * @date: 2020-12-23 09:35
 * @version: 1.0
 */
@Data
public class TeacherInfo {
    private String teacherId;  // 老师id
    private String realName;  // 真实姓名
    private String userImage;  // 用户头像地址
    private int age;  // 年龄
    private int sex;  // 性别
    private double evaluate = 0.0; // 好评率
    private String campus;  // 校区
    private String qq;   // QQ号码
    private String telPhone;   // 手机号码
    private String email;  // 邮箱
    private int price;  // 单价
    private int learnNumber = 0;  // 已经学习的学生数量
    private List<String> label;  // 教师标签
    private List<SubjectInfo> subjects;  // 科目信息
    private int views = 0;  // 人气
    private double score = 5;  // 评分
    private String biography; // 个人简介
    private int learnTime = 0;  // 授课时长
    private double renew = 1; // 续课率
}
