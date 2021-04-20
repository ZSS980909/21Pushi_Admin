package com.ershiyi.domain;

import com.ershiyi.domain.AbstractBaseDomain;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表 SYS_USER
 *
 * @author liy
 * @date 2020-03-05
 */
@Data
@Table(name = "sys_user")
@ApiModel(value="sysuser", description = "用户账户")
public class SysUser extends AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    public SysUser(String schoolName,Integer ifuse,String loginId, String schoolId, String realName, String pwd, Integer userTypeId, Integer sex, String nickname, String uniqueCode, String validataCode, String email) {
        this.userTypeId = userTypeId;
        this.ifuse = ifuse;
        this.loginId = loginId;
        this.schoolId = schoolId;
        this.realName = realName;
        this.pwd = pwd;
        this.sex = sex;
        this.nickname = nickname;
        this.uniqueCode = uniqueCode;
        this.validataCode = validataCode;
        this.email = email;
        this.schoolName=schoolName;
    }

    /**
     * 是否启用(0:停用 1:启用)默认:1
     */
    private Integer ifuse = 1;


    public SysUser() {
    }

    public SysUser(String loginId){
        this.loginId = loginId;
    }



    /**
     * 登录名（默认手机号）
     */
    private String loginId = "";
    /**
     * 学校单位编码  标准：GA 300.1
     */
    private String schoolId = "";  //已废弃
    private String  schoolName=""; //学校姓名  目前已修改成填写
    // 用户真实姓名
    private String realName = "" ;
//        /**
//         * 时间戳
//         */
//        private Long timestamp;
        /**
         * 是否删除(1,删除，0未删除)
         */
    //   private Integer deleted;

//    public Integer getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(Integer deleted) {
//        this.deleted = deleted;
//    }

    /**
         * 密码
         */
    @JsonInclude
    private String pwd;
    /**
     * 用户ID VARCHAR类型id
     */
   // private String userid;

    /**
     * 用户帐号类型（学生,老师 具体看表usertype）
     */
    private Integer userTypeId;
    /**
     * 性别
     */
    private Integer sex;
    /**
     *昵称
     */
    private String nickname;
    // 唯一标识
    private String uniqueCode;

    /**
     *验证码
     */
    @JsonInclude
    private String validataCode;

    /**
     * 邮箱
     * @return
     */
    private String  email;

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }
}
