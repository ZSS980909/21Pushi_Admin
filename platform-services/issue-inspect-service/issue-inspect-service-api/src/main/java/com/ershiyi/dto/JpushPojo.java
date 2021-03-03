package com.ershiyi.dto;

import com.ershiyi.domain.AbstractBaseDomain;
import lombok.Data;

import java.io.File;

@Data
public class JpushPojo extends AbstractBaseDomain {
    private String  msg;
    private  String  registrationId;//个体标记
    private  String  sendType;//type推送类型  锁屏2   解锁3  截屏4  抓拍5   推送知识点 1   推送错题 6
    private  String  imageUrl="";//图片路径
    private  String  studenterId;//学生编号
    private String  parenterId;//家长编号
    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getParenterId() {
        return parenterId;
    }

    public void setParenterId(String parenterId) {
        this.parenterId = parenterId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
