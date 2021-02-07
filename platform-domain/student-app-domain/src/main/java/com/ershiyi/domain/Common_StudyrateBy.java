package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 临阵磨刀答题记录
 */
@Data
@Table(name = "COMMON_LZMD_STUDYRATE")
@ApiModel(value="studyrate", description = "临阵磨刀模式学习做题记录表")
public class Common_StudyrateBy {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 知识点id
     */
    @Column(name = "KNOWID")
    @ApiModelProperty(value="知识点id")
    private  String knowId;
    /**
     * 开始时间
     */
    @Column(name = "STARTDT")
    @ApiModelProperty(value="开始时间")
    private  String startdt;
    /**
     * 结束时间
     */
    @Column(name = "ENDDT")
    @ApiModelProperty(value="结束时间")
    private  String enddt;
    /**
     * 用时
     */
    @Column(name = "USERDT")
    @ApiModelProperty(value="用时")
    private  String userdt;
    /**
     * 创建时间
     */
    @Column(name = "CREATEDT")
    @ApiModelProperty(value="创建时间")
    private  String createdt;
    /**
     * 答案
     */
    @Column(name = "AWSWER")
    @ApiModelProperty(value="答案")
    private  String answer;
    /**
     * 用户填写答案  多个,号分开
     */
    @Column(name = "FILLANSWER")
    @ApiModelProperty(value="用户填写答案  多个,号分开")
    private  String fillAnswer;

    /**
     * 题目类型
     */
    @Column(name = "QUESTIONTYPE")
    @ApiModelProperty(value="题目类型")
    private  int questionType;
    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private  int courseId;
    /**
     * 学生编号
     */
    @Column(name = "studenterId")
    @ApiModelProperty(value="学生编号")
    private  String studenterId;
    /**
     * 知识点是否完成(正确才算完成,错误的话就会进入错题库,直到答题正确才会更正)  0未完成 1完成
     */
    @Column(name = "KNOWLEDGEIDISCOMPLETED")
    @ApiModelProperty(value="知识点是否完成(正确才算完成,错误的话就会进入错题库,直到答题正确才会更正)  0未完成 1完成")
    private  String knowledgeidiscompleted;
    /**
     * 题目id
     */
    @Column(name = "QUESTIONID")
    @ApiModelProperty(value="题目id")
    private  int questionId;
    /**
     * 是否正确
     */
    @Column(name = "ISRIGHT")
    @ApiModelProperty(value="是否正确")
    private  int isright;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public String getStartdt() {
        return startdt;
    }

    public void setStartdt(String startdt) {
        this.startdt = startdt;
    }

    public String getEnddt() {
        return enddt;
    }

    public void setEnddt(String enddt) {
        this.enddt = enddt;
    }

    public String getUserdt() {
        return userdt;
    }

    public void setUserdt(String userdt) {
        this.userdt = userdt;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFillAnswer() {
        return fillAnswer;
    }

    public void setFillAnswer(String fillAnswer) {
        this.fillAnswer = fillAnswer;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getKnowledgeidiscompleted() {
        return knowledgeidiscompleted;
    }

    public void setKnowledgeidiscompleted(String knowledgeidiscompleted) {
        this.knowledgeidiscompleted = knowledgeidiscompleted;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getIsright() {
        return isright;
    }

    public void setIsright(int isright) {
        this.isright = isright;
    }
}
