package com.ershiyi.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@ApiModel(value = "TokenVO", description = "响应结构")
public class TokenVO implements Serializable {
    /**
     * 身份唯一标识
     **/
    private String userKey;

    /**
     * 用户安全退出密码
     */
    private String devicePassword;

    /**
     * 令牌
     **/
    @ApiModelProperty(value = "令牌")
    private String token;
    /**
     * 授权系统
     **/
    @ApiModelProperty(value = "授权系统")
    private String sys;
    /**
     * 授权设备
     **/
    @ApiModelProperty(value = "授权设备")
    private String device;
    /**
     * 令牌过期时间
     **/
    @ApiModelProperty(value = "令牌过期时间")
    private long exp;
    /**
     * 令牌有效刷新时间
     **/
    @ApiModelProperty(value = "令牌有效刷新时间")
    private long refresh;

    /**
     * 学校编号
     * @return
     */
    @ApiModelProperty(value = "学校编号")
    private String  schoolId;

    public TokenVO(){

    }

    public TokenVO(String userKey, String devicePassword, String token, String sys, String device, long exp, long refresh, String schoolId) {
        this.userKey = userKey;
        this.devicePassword = devicePassword;
        this.token = token;
        this.sys = sys;
        this.device = device;
        this.exp = exp;
        this.refresh = refresh;
        this.schoolId = schoolId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getRefresh() {
        return refresh;
    }

    public void setRefresh(long refresh) {
        this.refresh = refresh;
    }
}
