package com.ershiyi.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 前端请求参数实体类
 * @author: zss98
 * @date: 2020-12-01 16:39
 * @version: 1.0
 */
@Data
public class RequestDTO {
    private String id;
    private String parenterId;  // 家长编号
    private String studenterId;  // 学生编号
    private String name;
    private String password = "";
    private int courseId;
    private String knowId;
    private String loginId = "";
    private String month = new SimpleDateFormat("MM").format(new Date());
    private String year = new SimpleDateFormat("yyyy").format(new Date());
    private String validataCode;  // 短信验证码
    private String captcha = "";  // 绑定验证码
    private int pageNumber = 1;  // 页码
    private int pageSize = 10;  // 每页展示的数量
    private int type = 2;
    private int subjectId = 0 ; // 科目id
    private int isHot = 0;  // 是否热度排序
    private int isTime = 0;  // 是否按时间排序
    private String period=""; // 阶段
    private int sex = 0;  // 性别
}
