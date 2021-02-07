package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 科目
 */
@Data
@Table(name = "COMMON_COURSE_SUBJECT")
@ApiModel(value="Subject", description = "科目表")
public class Subject {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer subjectId;
    /**
     * 科目名称
     */
    @Column(name = "SUBJECTNAME")
    @ApiModelProperty(value="科目名称")
    private  Integer subjectName;
    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;
    /**
     * 是否使用
     */
    @Column(name = "IFUSER")
    @ApiModelProperty(value="是否使用")
    private  Integer ifuser;
}
