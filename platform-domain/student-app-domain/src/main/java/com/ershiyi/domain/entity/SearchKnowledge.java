package com.ershiyi.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索知识点具体类
 * 2020-11-02
 * liy
 */
public class SearchKnowledge implements Serializable {
    private Integer Id;
    private String  courseName; //课程名称
    private String  knowledgeName; //知识点名称
    private String  chapterName; //章节名称
    private String  knowledgeContextName; //知识点内容名称
    private Integer commentNumber; //评论数
    private Integer browseNumber; //浏览数
    private Integer collectNumber; //收藏数
    private String  videoUrl; //视频播放路径
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getKnowledgeContextName() {
        return knowledgeContextName;
    }

    public void setKnowledgeContextName(String knowledgeContextName) {
        this.knowledgeContextName = knowledgeContextName;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(Integer browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
