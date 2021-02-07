package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 查询学习资料
 */
@Data
public class QuestionContent {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 学科id
     */
    @Column(name = "SUBJECTID")
    @ApiModelProperty(value="学科id")
    private  Integer subjectid;
    /**
     * 知识点id
     */
    @Column(name = "KNOWLEDGEID")
    @ApiModelProperty(value="知识点id")
    private  Integer knowledgeid;

}
