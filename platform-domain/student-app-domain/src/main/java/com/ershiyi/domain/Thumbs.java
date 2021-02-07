package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 评论点赞类
 */
@Data
@Table(name = "COMMON_COURSE_DISCUSS_THUMBS")
@ApiModel(value="SignIn", description = "积分任务表")
public class Thumbs {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer thumbId;
    /**
     * 点赞评论的学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="点赞评论的学生编号")
    private  String studenterId;
//    /**
//     * 评论的学生编号
//     */
//    @Column(name = "studenterIdA")
//    @ApiModelProperty(value="评论的学生编号")
//    private  Integer studenterIdA;
    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private  String createdt;
    /**
     * 评论表id
     */
    @Column(name = "DISCUSSID")
    @ApiModelProperty(value="评论表id")
    private  Integer commentId;

}
