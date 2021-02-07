package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 收藏课程
 */
@Data
@Table(name = "COMMON_COURSE_CHAPTER")
@ApiModel(value="chapter", description = "章节")
public class Collect_Course {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;

    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  Integer courseid;
    /**
     * 学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="学生编号")
    private  String studenterId;
    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;
}
