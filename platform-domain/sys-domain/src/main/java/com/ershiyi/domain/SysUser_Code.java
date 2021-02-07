package com.ershiyi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser_Code  extends AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *学生id
     */
    private String studenterId;

    /**
     * 登录名（默认手机号）
     */
    private String loginid;
    /**
     * 密码
     */
    @JsonInclude
    private String pwd;
    /**
     * 手机唯一标识
     */
    private String uniquecode;
    /**
     * 验证码
     */
    private String validataCode;

    /**
     * 是否启用(0:停用 1:启用)默认:1
     */
    private Integer ifuse;

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    public String getstudenterId() {
        return studenterId;
    }

    public void setstudenterId(String studenterId) {
        this.studenterId = studenterId;
    }
}
