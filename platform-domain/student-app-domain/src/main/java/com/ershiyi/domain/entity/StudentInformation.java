package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.Date;


/**
 * @Description: 学生个人详细信息实体类
 * @author: zss98
 * @date: 2020-07-28 09:38
 * @version: 1.0
 */
@Data
public class StudentInformation {
    private String guid = "";    // 学生id
    private String loginId = ""; // 登录名
    private String schoolId;  // 学校编号
    private String email = "";  // 用户邮箱
    private String name = "";  // 用户昵称
    private String userImage = "";  // 用户头像
    private String sex;   // 用户性别
    private String birthdaydt = "";  // 用户生日
    private String studenterId =""; // 学生编号
    private String qrCode = "";  // 二维码地址URL
    private Integer classNumber;  // 学生班级数量
    private String pwd = "" ;  // 学生密码
    private Integer ifUse;  // 是否使用
    private Integer deleted;  // 是否删除
    private Integer courseNumber;  // 学生课程数量
    private Integer collectNumber;  // 学生收藏课程数量
    private Long historyNumber;  // 学生浏览历史数量
}
