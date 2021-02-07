package com.ershiyi.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * 公共的属性
 * 
 * @author liy
 */
@Data
public class PageDTO {
    /** 当前记录起始索引 */
    @ApiModelProperty("页码")
    private Integer pageNumber;

    /** 每页显示记录数 */
    @ApiModelProperty("每页数量")
    private Integer pageSize;

    /** 排序列 */
    @ApiModelProperty("排序列")
    private String orderByColumn;

    /** 排序的方向 "desc" 或者 "asc". */
    @ApiModelProperty("排序的方向")
    private String sort;

    public String orderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return orderByColumn + " " + sort;
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

    public String getOrderByColumn() {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}