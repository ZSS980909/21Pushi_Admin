package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 知识点表
 */
@Data
@Table(name = "COMMON_COURSE_KNOWLEDGE")
@ApiModel(value="knowledge", description = "知识点")
public class Knowledge {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer knowId;
    /**
     * 知识点内容
     */
    @Column(name = "KNOWLEDGECONTENT")
    @ApiModelProperty(value="知识点内容")
    private  String content;
    /**
     * 是否删除
     */
    @Column(name = "ID")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;
}
