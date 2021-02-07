//package com.ershiyi.domain;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Table;
//
///**
// * 问题类型
// */
//@Data
//@Table(name = "COMMON_COURSE_QUESTION")
//@ApiModel(value="question", description = "课程类型")
//public class Question {
//    /**
//     * 主键ID
//     */
//    @Column(name = "ID")
//    @ApiModelProperty(value="主键id")
//    private  Integer id;
//    /**
//     * 知识点id
//     */
//    @Column(name = "KNOWLEDGEID")
//    @ApiModelProperty(value="知识点id")
//    private  Integer knowledgeid;
//    /**
//     * 正确率
//     */
//    @Column(name = "ACC")
//    @ApiModelProperty(value="正确率")
//    private  String acc;
//    /**
//     * 题目类型
//     */
//    @Column(name = "QUESTIONTYPEID")
//    @ApiModelProperty(value="题目类型")
//    private  Integer questiontypeid;
//    /**
//     * 题目id
//     */
//    @Column(name = "QUESTUONID")
//    @ApiModelProperty(value="题目id (可能是判断题或者选择题,具体题目的id,提醒根据typeid去区别)")
//    private  Integer questionid;
//}
