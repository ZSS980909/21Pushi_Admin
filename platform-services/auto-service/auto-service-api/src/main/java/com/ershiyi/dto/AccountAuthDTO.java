package com.ershiyi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账号认证DTO
 *
 * @author liy
 * @since 2020/05/29
 */
@Data
@ApiModel(value = "AccountAuthDTO", description = "账号认证DTO")
public class AccountAuthDTO extends AbstractAuthDTO {

    @ApiModelProperty(value = "登录名")
    private String loginId = "";

    @ApiModelProperty(value = "密码")
    private String passWord;

    @ApiModelProperty(value = "手机唯一标识")
    private String uniqueCode;

    @ApiModelProperty(value = "登陆标记 1安卓 2苹果")
    private String loginCode;

    @ApiModelProperty("登录方式,1密码登录，2验证登录")
    private Integer loginType = 1;

    @ApiModelProperty("验证码")
    private String validataCode = "";

    @ApiModelProperty("账号类型")
    private int userTypeId = 1;

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
