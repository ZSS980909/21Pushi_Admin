package com.ershiyi.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * @author zaz
 * @date 2020-03-06
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID VARCHAR类型id
     */
    private String userid;
    /**
     * 角色guid 数组
     */
    private String[] roleguids;


    @ApiModelProperty("用户ID VARCHAR类型id")
    public void setUserid(String userid) {
        this.userid = userid;
    }

    @ApiModelProperty("用户ID VARCHAR类型id")
    public String getUserid() {
        return userid;
    }


    @ApiModelProperty("角色guid 数组")
    public void setRoleguids(String[] roleguids) {
        this.roleguids = roleguids;
    }

    @ApiModelProperty("角色guid 数组")
    public String[] getRoleguids() {
        return roleguids;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
