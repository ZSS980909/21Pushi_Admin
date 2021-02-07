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
// * 题型
// */
//@Data
//@Table(name = "COMMON_COURSE_QUESTIONTYPE")
//@ApiModel(value="questiontype", description = "题型")
//public class Questiontype {
//    /**
//     * 主键ID
//     */
//    @Column(name = "ID")
//    @ApiModelProperty(value="主键id")
//    private  Integer id;
//    /**
//     * 题型id
//     */
//    @Column(name = "QUESTIONTYPE")
//    @ApiModelProperty(value="题型typeid")
//    private  Integer questiontype;
//    /**
//     * 题型名称
//     */
//    @Column(name = "QUESTIONNAME")
//    @ApiModelProperty(value="题型名称")
//    private  Integer questionname;
//    /**
//     * 排序
//     */
//    @Column(name = "SORT")
//    @ApiModelProperty(value="排序")
//    private  Integer sort;
//    /**
//     * 学科
//     */
//    @Column(name = "ID")
//    @ApiModelProperty(value="学科")
//    private  Integer subjectid;
//    /**
//     * 是否删除
//     */
//    @Column(name = "DELETED")
//    @ApiModelProperty(value="是否删除")
//    private  Integer deleted;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getQuestiontype() {
//        return questiontype;
//    }
//
//    public void setQuestiontype(Integer questiontype) {
//        this.questiontype = questiontype;
//    }
//
//    public Integer getQuestionname() {
//        return questionname;
//    }
//
//    public void setQuestionname(Integer questionname) {
//        this.questionname = questionname;
//    }
//
//    public Integer getSort() {
//        return sort;
//    }
//
//    public void setSort(Integer sort) {
//        this.sort = sort;
//    }
//
//    public Integer getSubjectid() {
//        return subjectid;
//    }
//
//    public void setSubjectid(Integer subjectid) {
//        this.subjectid = subjectid;
//    }
//
//    public Integer getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Integer deleted) {
//        this.deleted = deleted;
//    }
//}
