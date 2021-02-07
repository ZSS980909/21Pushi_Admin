package com.ershiyi.domain.entity;

/**
 * 搜索
 */
public class Search {
    private String Id;
    private Integer searchType;  //题目类型  1课程 2知识点 3题目
    private String keyWord;     //关键字
    private String conText;     //内容
    private Integer pageNumber=1;  // 页码
    private Integer pageSize=10;  // 每页展示的数量
    private Integer isPay;//是否购买  0表示未购买  1表示已购买
    private Integer questionType; //題型  搜索題目所需字段
    private String studenterId; //学生编号
    private String courseId; //课程id

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getConText() {
        return conText;
    }

    public void setConText(String conText) {
        this.conText = conText;
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

}
