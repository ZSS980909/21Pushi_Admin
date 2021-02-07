package com.ershiyi.domain;

import com.ershiyi.service.impl.BaseServiceImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 实体类验证码
 * Liy
 * 2020-06-06
 */
@Data
@Table(name = "sys_user_validatacode")
@ApiModel(value="sysuser", description = "验证码发送")
public class ValidataCode implements Serializable {
    /**
     * 1为注册,2为忘记密码修改,3为登录确认
     */
    private Integer type;

    private String guid;

    // 家长编号
    private String parenterId;
    /**
     * 手机验证码
     */
    private String  validataCode;
    /**
     * 手机号码
     */
    private String mobilePhone;

    private String name;
    /**
     * 验证码发送时间
     */
    private String createTime;
    @ApiModelProperty("验证码类型")
    public Integer getType() {
        return type;
    }
    @ApiModelProperty("验证码类型")
    public void setType(Integer type) {
        this.type = type;
    }
    @ApiModelProperty("验证码")
    public String getValidataCode() {
        return validataCode;
    }
    @ApiModelProperty("验证码")
    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }






//    @ApiModelProperty("验证码发送消息内容")
//    public String getMessage() {
//        return message;
//    }
//    @ApiModelProperty("验证码发送消息内容")
//    public void setMessage(String message) {
//        this.message = message;
//    }
    @ApiModelProperty("验证码发送手机号码")
    public String getMobilePhone() {
        return mobilePhone;
    }
    @ApiModelProperty("验证码发送手机号码")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    @ApiModelProperty("验证码发送时间")
    public String getCreateTime() {
        return createTime;
    }
    @ApiModelProperty("验证码发送时间")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
