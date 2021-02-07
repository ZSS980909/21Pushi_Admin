package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 轮播图信息
 * @author: zss98
 * @date: 2020-12-15 10:11
 * @version: 1.0
 */
@Data
public class BannerInfo {
    private int serial;  // 序号
    private String jumpUrl;  // 跳转链接
    private String picture;  // 图片地址
    private int jumpType; // 跳转类型
    private String paramentId;  // 参数id
    private String title;  // 页面标题
}
