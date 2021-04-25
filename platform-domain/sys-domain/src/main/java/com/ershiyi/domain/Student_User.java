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

    private String devicePassword;

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
    private String schoolId = " ";
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
    private String schoolName = "";

    public Student_User(String id, String studenterId, String createdt, String devicePassword, String studentUserId, String schoolId, String classid, String uniqueCode, Integer userType, String schoolName) {
        this.id = id;
        this.studenterId = studenterId;
        this.createdt = createdt;
        this.devicePassword = devicePassword;
        this.studentUserId = studentUserId;
        this.schoolId = schoolId;
        this.classid = classid;
        this.uniqueCode = uniqueCode;
        this.userType = userType;
        this.schoolName = schoolName;
    }

    public Student_User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(String studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
