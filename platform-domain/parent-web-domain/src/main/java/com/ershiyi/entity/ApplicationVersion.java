package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: app版本信息
 * @author: zss98
 * @date: 2020-11-07 10:35
 * @version: 1.0
 */
@Data
public class ApplicationVersion {
    private Integer id;
    private Integer isUpdate = 0; // 是否可以更新 0代表没有更新,1代表有更新,2代表强制更
    private Integer urgentUpdate; // 是否强制更新
    private Double version;  // 版本信息
    private String message;  // 更新信息
    private String size ;  // 软件大小
    private String downUrl;  // 下载地址
    private String appType;  // 应用平台
}
