package com.ershiyi.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 积分查询dto表
 */
@Data
@ApiModel(value="Integral_work", description = "积分查询dto表")
public class Integral_work {
    /**
     * 学生编号
     */
    @ApiModelProperty(value="学生编号")
    private  String studenterId;
    /**
     * 关键字
     */
    @ApiModelProperty(value="关键字")
    private  String keyword;

    // 学校编号

    private String schoolId;

    public String getschoolId() {
        return schoolId;
    }

    public void setschoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getstudenterId() {
        return studenterId;
    }

    public void setstudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
