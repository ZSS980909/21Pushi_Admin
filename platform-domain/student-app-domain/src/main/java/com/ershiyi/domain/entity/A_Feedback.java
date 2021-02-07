package com.ershiyi.domain.entity;

/**
 * 意见反馈
 */
public class A_Feedback {
    private Integer id;
    private String studenterId;
    private String content;
    private String createDt;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
