package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 表 SYS_MENU
 *
 * @author liy
 * @date 2020-06-29
 */
@Data
@Table(name = "COMMON_STUDENT_USER")
@ApiModel(value="STUDENT_USER", description = "学生编号账户绑定类")
public class Student_User {
    /**
     * ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="ID")
    private String id;
    /**
     * 学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="学生编号")
    private String studenterId;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private String createdt;
    /**
     * 绑定账户关系表guid
     */
    @Column(name = "STUDENTUSERID")
    @ApiModelProperty(value="绑定账户关系表guid")
    private String studentUserId;
    /**
     * 学校ID
     */
    @Column(name = "schoolId")
    @ApiModelProperty(value="学校ID")
    private String schoolId = "";
    /**
     * 班级id
     */
    @Column(name = "CLASSID")
    @ApiModelProperty(value="班级id")
    private String classid;

    // 手机唯一标识
    @Column(name = "uniqueCode")
    @ApiModelProperty(value = "手机唯一标识")
    private String uniqueCode;

    // 账号类型
    private Integer userType;
}
