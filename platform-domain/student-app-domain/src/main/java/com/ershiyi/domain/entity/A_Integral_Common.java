package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 积分查询通用
 * @author: liy
 * @date: 2020-08-07 16:38
 * @version: 1.0

 */
@Data
public class A_Integral_Common {
    private  String studenterId; //学生编号
    private  String keyWord;  //关键字
    private String schoolId; //学校编号

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }


    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
