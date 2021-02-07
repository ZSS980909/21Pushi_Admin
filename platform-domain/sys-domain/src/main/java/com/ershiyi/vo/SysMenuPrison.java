package com.ershiyi.vo;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 *
 *
 * @author zaz
 * @date 2020-03-06
 */
public class SysMenuPrison implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 所编号
     */
    private String prisonid;
    /**
     * 菜单guid 数组
     */
    private String[] menuguids;


    @ApiModelProperty("所编号")
    public void setPrisonid(String prisonid) {
        this.prisonid = prisonid;
    }

    @ApiModelProperty("所编号")
    public String getPrisonid() {
        return prisonid;
    }


    @ApiModelProperty("菜单guid 数组")
    public void setMenuguids(String[] menuguids) {
        this.menuguids = menuguids;
    }

    @ApiModelProperty("菜单guid 数组")
    public String[] getMenuguids() {
        return menuguids;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
