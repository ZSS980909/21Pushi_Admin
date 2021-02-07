package com.ershiyi.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 章节目录信息
 * @author: zss98
 * @date: 2020-08-06 14:03
 * @version: 1.0
 */
@Data
public class ChapterInfo implements Serializable {
    private Integer chapterId; // 章节id
    private String chapterName = ""; // 章节名称
    private String knowIds = ""; // 节id集合
    private Integer isStudy = 0;  // 学习状态
    private List knows = new ArrayList<>(); //节信息
}
