package com.ershiyi.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudyRank {
    private String StudyRank = "";    // 返回学习情况排名
    private String QuestionsRank = ""; // 返回答题排名
}
