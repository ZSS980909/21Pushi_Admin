package com.ershiyi.domain.entity;

/**
 * @Description: 积分任务表
 * @author: liy
 * @date: 2020-08-07 16:38
 * @version: 1.0
 */
public class A_SignInWork {
    private  Integer id;
    private  String Integralvalue;  //积分
    private  String Integralname; //积分任务名称
    private  String Integraldescribe;//任务描述
    private  String createdt;  //创建时间
    private  String frequency;//任务限制次数
    private  String keyword; //任务关键字
    private  String deleted;
    private  String schoolId;//学校id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntegralvalue() {
        return Integralvalue;
    }

    public void setIntegralvalue(String integralvalue) {
        Integralvalue = integralvalue;
    }

    public String getIntegralname() {
        return Integralname;
    }

    public void setIntegralname(String integralname) {
        Integralname = integralname;
    }

    public String getIntegraldescribe() {
        return Integraldescribe;
    }

    public void setIntegraldescribe(String integraldescribe) {
        Integraldescribe = integraldescribe;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getschoolId() {
        return schoolId;
    }

    public void setschoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
