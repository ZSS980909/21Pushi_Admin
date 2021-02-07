package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 积分总值
 * @author: liy
 * @date: 2020-08-07 16:38
 * @version: 1.0
 */
@Data
public class A_Integral {
    private  Integer id; //id
    private  String integralvalue; //积分值
    private  String studenterId; //学生id
    private  String schoolId; //学校id
    private  String createdt; //创建时间
    private  String modifydt; //修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntegralvalue() {
        return integralvalue;
    }

    public void setIntegralvalue(String integralvalue) {
        this.integralvalue = integralvalue;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getModifydt() {
        return modifydt;
    }

    public void setModifydt(String modifydt) {
        this.modifydt = modifydt;
    }
}
