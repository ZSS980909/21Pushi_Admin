package com.ershiyi.dto;

import lombok.Data;

/**
 * @Description: 学生端前端请求实体类
 * @author: zss98
 * @date: 2020-08-05 13:59
 * @version: 1.0
 */
@Data
public class RequestDTO {
    private Integer id;  // 表id
    private String studenterId;    // 学生编号
    private String courseName;  // 课程名字
    private Integer courseId; // 课表id
    private Integer chapterId;  // 章节id
    private Integer knowId; // 节点id
    private String  knowContentId;//知识点内容id
    private Integer commentId;  // 评论id
    private String message;  // 信息
    private String guid; // 用户表记录id
    private String url;  // 用户头像url地址
    private Integer appType;  // app检查更新
    private String name;  // 用户昵称
    private String sex;  // 用户性别
    private String birthday;  // 用户生日
    private Integer subjectId = 0;  // 科目id
    private Integer pageNumber=1;  // 页码
    private Integer pageSize=10;  // 每页展示的数量
    private Integer type;  // 类型
    private String date ;  // 日期
    private String Key;  // 功能模块代号
    private Integer creatorId;  // 作者id
    private String passWord;   // 用户原密码
    private String newPass;  // 用户新密码
    private String ids; // id集合
    private String questionId; // 题目id
    private String mobilePhone;  // 手机号码
    private String validataCode;  // 验证码
    private String loginId;  // 登录手机号
    private Float correct;  // 做题正确率
    private Double version;  // 应用版本信息
    private int grade = 1; // 阶段信息 0小学 1初中 2高中

    public String getKnowContentId() {
        return knowContentId;
    }

    public void setKnowContentId(String knowContentId) {
        this.knowContentId = knowContentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getKnowId() {
        return knowId;
    }

    public void setKnowId(Integer knowId) {
        this.knowId = knowId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Float getCorrect() {
        return correct;
    }

    public void setCorrect(Float correct) {
        this.correct = correct;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "id=" + id +
                ", studenterId='" + studenterId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                ", chapterId=" + chapterId +
                ", knowId=" + knowId +
                ", commentId=" + commentId +
                ", message='" + message + '\'' +
                ", guid='" + guid + '\'' +
                ", url='" + url + '\'' +
                ", appType=" + appType +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", subjectId=" + subjectId +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", type=" + type +
                ", date='" + date + '\'' +
                ", Key='" + Key + '\'' +
                ", creatorId=" + creatorId +
                ", passWord='" + passWord + '\'' +
                ", newPass='" + newPass + '\'' +
                ", ids='" + ids + '\'' +
                ", questionId='" + questionId + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", validataCode='" + validataCode + '\'' +
                ", loginId='" + loginId + '\'' +
                ", correct=" + correct +
                ", version=" + version +
                '}';
    }
}
