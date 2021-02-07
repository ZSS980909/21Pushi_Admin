package com.ershiyi.domain;


import com.ershiyi.common.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "COMMON_COURSE")
@ApiModel(value="course", description = "課程")
public class Course extends PageDTO {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer courseId;
    /**
     *课程名称
     */
    @Column(name = "CURRICULUM")
    @ApiModelProperty(value="课程名称")
    private  String courseName;
    /**
     *作者
     */
    @Column(name = "AUTHOR")
    @ApiModelProperty(value="作者")
    private  String author;
    /**
     *作者简介
     */
    @Column(name = "BIOGRAPHY")
    @ApiModelProperty(value="作者简介")
    private  String biography;
    /**
     *简介
     */
    @Column(name = "SYNOPSIS")
    @ApiModelProperty(value="简介")
    private  String synopsis;
    /**
     *创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private Date createdt;
    /**
     *学科id
     */
    @Column(name = "SUBJECTID")
    @ApiModelProperty(value="学科id")
    private  int subjectId;
    /**
     *图像地址
     */
    @Column(name = "PICTURE")
    @ApiModelProperty(value="图像地址")
    private  String picture = "";
    /**
     *创建者id
     */
    @Column(name = "CREATORID")
    @ApiModelProperty(value="创建者id")
    private  Integer creatorId;
    /**
     *创建者姓名
     */
    @Column(name = "CREATOR")
    @ApiModelProperty(value="创建者姓名")
    private  String creator = "";
    /**
     *修改者id
     */
    @Column(name = "MODIFIERID")
    @ApiModelProperty(value="修改者id")
    private  Integer modifierid;
    /**
     *修改时间
     */
    @Column(name = "MODIFYDT")
    @ApiModelProperty(value="修改时间")
    private  String modifydt = "";
    /**
     *修改者姓名
     */
    @Column(name = "MODIFIER")
    @ApiModelProperty(value="修改者姓名")
    private  String modifier = "";
    /**
     *积分
     */
    @Column(name = "INTEGRAL")
    @ApiModelProperty(value="积分")
    private  Double integral;
    /**
     *评论数
     */
    @ApiModelProperty(value="评论数")
    private  Long discussNumber;
    /**
     *浏览数
     */
    @ApiModelProperty(value="浏览数")
    private  Long pageViews;
    /**
     *收藏数
     */
    @ApiModelProperty(value="收藏数")
    private Long starNumber;
    /**
     *榜单名称
     */
    @Column(name = "BILL")
    @ApiModelProperty(value="榜单名称")
    private  String bill;
    /**
     *榜单排序
     */
    @Column(name = "BILLSORT")
    @ApiModelProperty(value="榜单排序")
    private  String billsort;
    /**
     *标签
     */
    @Column(name = "LABEL")
    @ApiModelProperty(value="标签")
    private  String label;


    public Long getDiscussNumber() {
        return discussNumber;
    }

    public void setDiscussNumber(Long discussNumber) {
        this.discussNumber = discussNumber;
    }
}
