package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 家长信息
 * @author: zss98
 * @date: 2020-12-05 09:53
 * @version: 1.0
 */
@Data
public class ParentInfo {
    private String parenterId;
    private String userImage;  // 用户头像
    private String nickName;  // 昵称
    private String realName;  // 真实姓名
    private String birthdaydt;  // 生日
    private int sex;   // 用户头像
    private String guid;  // 用户编号
    private String email; // 用户邮箱
    private String loginId;  // 用户手机号
    private String QRCode;  // 用户二维码
}
