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
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 角色 guid
     */
    private String roleguid;
    /**
     * 菜单guid 数组
     */
    private String[] menuguids;


    @ApiModelProperty("角色 guid")
    public void setRoleguid(String roleguid) {
        this.roleguid = roleguid;
    }

    @ApiModelProperty("角色 guid")
    public String getRoleguid() {
        return roleguid;
    }


    @ApiModelProperty("菜单guid 数组")
    public void setMenuguids(String[] menuguids) {
        this.menuguids = menuguids;
    }

    @ApiModelProperty("菜单guid 数组")
    public String[] getMenuguids() {
        return menuguids;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
