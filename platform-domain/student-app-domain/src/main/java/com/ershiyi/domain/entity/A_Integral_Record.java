package com.ershiyi.domain.entity;

import com.ershiyi.domain.AbstractBaseDomain;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 积分变化
 * @author: liy
 * @date: 2020-08-07 16:38
 * @version: 1.0
 */
@Data
public class A_Integral_Record  extends AbstractBaseDomain implements Serializable {
    private  Integer id;
    private  Integer integralnameid; //积分任务名称id
    private  String studenterId;  //学生id
    private  String changeintegral; //变化积分
    private  String rawintegral;  // 原始积分
    private  String integralvalue;  //最终积分值
    private  String createdt;  //创建时间
    private  String status;   //状态
    private  String frequency; //任务次数
    private  String schoolId;  //学校id
    private  String keyWord;  //关键词

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

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
