package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 学生信息
 * @author: zss98
 * @date: 2020-12-01 16:47
 * @version: 1.0
 */
@Data
public class StudentInfo {
    private String studenterId = ""; // 学生编号
    private String realName = ""; // 学生真实姓名
    private String userImage = "";  // 学生头像地址
    private String nickName = "";  // 学生姓名
    private String schoolName = "";  // 学校名称
    private String loginId = ""; // 手机号
    private String levelLabel ="学霸"; // 学生等级标签
    private int isRelation = 0;  // 是否已经跟该家长绑定
}
