package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 知识点内容
 * @author: zss98
 * @date: 2020-08-07 16:38
 * @version: 1.0
 */
@Data
public class KnowContent {
    private String knowContentId;  // 知识点内容id
    private String prologue = ""; // 衔接语
    private String editionName = "";  // 版本名称
    private String content = ""; // 知识点内容
    private String knowContentName = "";  // 知识点名称
    private Integer studyTime ;  // 学习时间
    private String knowledgetextUrl;//视频路径
}

