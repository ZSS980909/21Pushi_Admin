package com.ershiyi.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 学习状态
 * @author: zss98
 * @date: 2021-01-09 09:56
 * @version: 1.0
 */
@Data
public class LearnStatus implements Serializable {
    private Integer attention;  // 关注度
    private Integer efficiency;  // 效率
    private Integer progress;  // 知识点学习进度
    private Integer learnLength; // 学习时长
    private Integer studyScore; // 学习力
}
