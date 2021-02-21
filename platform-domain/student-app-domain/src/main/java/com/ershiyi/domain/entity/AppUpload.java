package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description:
 * @author: zss98
 * @date: 2021-02-21 10:36
 * @version: 1.0
 */
@Data
public class AppUpload {
    private int id;
    private int type;  // 应用端，0学生端，1家长端，2老师端
    private int appType;  // 应用平台id，0安卓，1pad，2ios
    private int urgentUpdate;  // 是否必须更新
    private String version;  // 版本信息
    private String message;  // 更新内容
    private Double size;  // 版本大小
    private String downUrl;  // app下载地址
}
