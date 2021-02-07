package com.ershiyi.dto;

import lombok.Data;

@Data
public class LocationRequestDTO {
    private  String studenterId; //学生编号
    private  String longiTude;   //经度
    private  String latiTude;   //纬度
    private  String identification;  //极光标识
    private String position;  //具体位置
    private String city;   //城市
    private String province;  //省份
    private String currentDt; //目前时间
    private Integer Id;
   // private String type;//type 1为月季度  2为年季度
    private String startTime; //开始时间
    private String endTime;  //结束时间
    private String year;//年份
    private String month;//月份
    private String subjectId;//科目id
    private String subjectName;//科目名称

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
//
//    public String getType() {
//        return type;
//    }

//    public void setType(String type) {
//        this.type = type;
//    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getLongiTude() {
        return longiTude;
    }

    public void setLongiTude(String longiTude) {
        this.longiTude = longiTude;
    }

    public String getLatiTude() {
        return latiTude;
    }

    public void setLatiTude(String latiTude) {
        this.latiTude = latiTude;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCurrentDt() {
        return currentDt;
    }

    public void setCurrentDt(String currentDt) {
        this.currentDt = currentDt;
    }
}
