package com.ershiyi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;


/**
 * 菜单DTO
 *
 * @author zaz
 * @since 2020/03/23
 */
@Data
@ApiModel(value = "SysMenuDeskDTO", description = "")
public class SysMenuDeskDTO {

    @Column(name = "userid")
    @ApiModelProperty(value = "userId")
    private String userid;

    @Column(name = "menuguid")
    @ApiModelProperty(value = "菜单数组 ")
    private String[] menuguid;

    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value = "排序")
    private BigDecimal sort;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String[] getMenuguid() {
        return menuguid;
    }

    public void setMenuguid(String[] menuguid) {
        this.menuguid = menuguid;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }
}
