package com.ershiyi.domain.entity;

import io.swagger.models.auth.In;

/**
 * 搜索知识点详情字段
 * 2020-11-02
 */
public class SearchByKnowledge {
    private Integer Id;
    private Integer collectNumber;//收藏数
    private Integer browsingNumber;//浏览数
    private Integer discussNumber;//评论数
    private Integer IsPay;//是否购买
    private String curriculum;//课程名称
    private String knowledgetext; //知识点内容
    private String knowledgeName;//知识点名称
    private String knowledgeUrl;//知识点视频路径
    private Integer iscomplete;//是否完成
    private String picture;//图片地址
    private String synopsis; //课程介绍

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Integer iscomplete) {
        this.iscomplete = iscomplete;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Integer collectNumber) {
        this.collectNumber = collectNumber;
    }

    public Integer getBrowsingNumber() {
        return browsingNumber;
    }

    public void setBrowsingNumber(Integer browsingNumber) {
        this.browsingNumber = browsingNumber;
    }

    public Integer getDiscussNumber() {
        return discussNumber;
    }

    public void setDiscussNumber(Integer discussNumber) {
        this.discussNumber = discussNumber;
    }

    public Integer getIsPay() {
        return IsPay;
    }

    public void setIsPay(Integer isPay) {
        IsPay = isPay;
    }

    public String getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }

    public String getKnowledgetext() {
        return knowledgetext;
    }

    public void setKnowledgetext(String knowledgetext) {
        this.knowledgetext = knowledgetext;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeUrl() {
        return knowledgeUrl;
    }

    public void setKnowledgeUrl(String knowledgeUrl) {
        this.knowledgeUrl = knowledgeUrl;
    }
}
