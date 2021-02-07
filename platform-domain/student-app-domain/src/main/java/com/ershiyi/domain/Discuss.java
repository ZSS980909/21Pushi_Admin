package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "COMMON_COURSE_DISCUSS")
@ApiModel(value="discuss", description = "评论表")
public class Discuss {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer commentId;
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  Integer courseid;
    /**
     * 评论内容
     */
    @Column(name = "DISCUSS")
    @ApiModelProperty(value="评论内容")
    private  String discuss;
    /**
     * 学生编号
     */
    @Column(name = "STUDENTERID")
    @ApiModelProperty(value="学生编号")
    private  String studenterid;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private  String createdt;
    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;
    /**
     * 评论点赞次数
     */
    @Column(name = "THUMBS")
    @ApiModelProperty(value="评论点赞次数")
    private  Integer thumbs;

    /**
     * 课程名称
     */
    @Column(name = "CURRICULUM")
    @ApiModelProperty(value="课程名称")
    private  String curriculum;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME")
    @ApiModelProperty(value="昵称")
    private  String nickname;

}
