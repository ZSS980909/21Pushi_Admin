package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用搜索,传送一些常见的参数
 */
@Data
@ApiModel(value="common_search", description = "通用搜索,传送一些常见的参数")
public class Common_Search {

    private int grade = 1;

    @ApiModelProperty(value="主键id")
    private  Integer Id;
    @ApiModelProperty(value="学生编号")
    private  String studenterId;
    @ApiModelProperty(value="页码")
    private Integer pageNumber;  // 页码
    @ApiModelProperty(value="每页展示的数量")
    private Integer pageSize;  // 每页展示的数量

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
