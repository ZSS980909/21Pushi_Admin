package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "SYS_USER_INTEGRAL_RECORD")
@ApiModel(value="Integral_record", description = "积分变化表")
public class Integral_record extends AbstractBaseDomain implements Serializable {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 积分任务名称id
     */
    @Column(name = "INTEGRALNAMEID")
    @ApiModelProperty(value="积分任务名称id")
    private  Integer integralnameid;
    /**
     * 学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="学生编号")
    private  String studenterId;
    /**
     * 变化积分
     */
    @Column(name = "CHANGEINTEGRAL")
    @ApiModelProperty(value="变化积分")
    private  String changeintegral;
    /**
     * 原始积分
     */
    @Column(name = "RAWINTEGRAL")
    @ApiModelProperty(value="原始积分")
    private  String rawintegral;
    /**
     * 最终积分值
     */
    @Column(name = "INTEGRALVALUE")
    @ApiModelProperty(value="最终积分值")
    private  String integralvalue;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private  String createdt;
    /**
     * 状态
     */
    @Column(name = "STATUS")
    @ApiModelProperty(value="状态")
    private  String status;
    /**
     * 任务次数
     */
    @Column(name = "FREQUENCY")
    @ApiModelProperty(value="任务次数")
    private  String frequency;
    /**
     * 学校id
     */
    @Column(name = "schoolId")
    @ApiModelProperty(value="学校id")
    private  String schoolId;
    @Column(name = "KEYWORD")
    @ApiModelProperty(value="关键词")
    private  String keyword;
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntegralnameid() {
        return integralnameid;
    }

    public void setIntegralnameid(Integer integralnameid) {
        this.integralnameid = integralnameid;
    }

    public String getstudenterId() {
        return studenterId;
    }

    public void setstudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getChangeintegral() {
        return changeintegral;
    }

    public void setChangeintegral(String changeintegral) {
        this.changeintegral = changeintegral;
    }

    public String getRawintegral() {
        return rawintegral;
    }

    public void setRawintegral(String rawintegral) {
        this.rawintegral = rawintegral;
    }

    public String getIntegralvalue() {
        return integralvalue;
    }

    public void setIntegralvalue(String integralvalue) {
        this.integralvalue = integralvalue;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getschoolId() {
        return schoolId;
    }

    public void setschoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
