package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "COMMON_COURSE_CHOICE")
@ApiModel(value="choice", description = "单选题")
public class Common_Choice {
    /**
     * 主键ID
     */
    @Column(name = "QUESTIONID")
    @ApiModelProperty(value="主键id")
    private  Integer questionId;
    /**
     * 知识点编号
     */
    @Column(name = "KNOWID")
    @ApiModelProperty(value="知识点编号")
    private  String knowId;
    /**
     * 选项集合
     */
    @Column(name = "OPTION")
    @ApiModelProperty(value="选项集合")
    private List options = new ArrayList();
    /**
     * 选项A
     */
    @Column(name = "OPTIONA")
    @ApiModelProperty(value="选项A")
    private  String optionA ;
    /**
     * 选项B
     */
    @Column(name = "CHOICEB")
    @ApiModelProperty(value="选项B")
    private  String optionB ;
    /**
     * 选项C
     */
    @Column(name = "CHOICEC")
    @ApiModelProperty(value="选项C")
    private  String optionC ;
    /**
     * 选项D
     */
    @Column(name = "CHOICED")
    @ApiModelProperty(value="选项D")
    private  String optionD ;
    /**
     * 正确答案
     */
    @Column(name = "RIGHTAWS")
    @ApiModelProperty(value="正确答案")
    private  String correctOption ;
    /**
     * 解析
     */
    @Column(name = "RESOLVING")
    @ApiModelProperty(value="解析")
    private  String resolving ;

    /**
     * 题目
     */
    @Column(name = "QUESTION")
    @ApiModelProperty(value="题目")
    private  String question ;
    /**
     * 是否国标
     */
    @Column(name = "ISGB")
    @ApiModelProperty(value="是否国标")
    private  Integer isgb;
    /**
     * 学校编号
     */
    @Column(name = "schoolId")
    @ApiModelProperty(value="学校编号")
    private  String schoolId;
    /**
     * 学科编号
     */
    @Column(name = "SUBJECTID")
    @ApiModelProperty(value="学科编号")
    private  int subjectId;
    /**
     * 查看題目圖片
     */
    private  String questionImage;
    /**
     * 题型
     */
    @Column(name = "TYPE")
    @ApiModelProperty(value="题型")
    private  int type;
    /**
     * 是否正确
     */
    @Column(name = "CORRECT")
    @ApiModelProperty(value="是否正确")
    private String correct;

    /**
     * 课程id
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程id")
    private String courseId;

    /**
     * 章节id
     */
    @Column(name = "CHAPTERID")
    @ApiModelProperty(value="章节id")
    private String chapterId;

    /**
     * 学生编号
     */
    @Column(name = "STUDENTERID")
    @ApiModelProperty(value="学生编号")
    private String studenterId;



    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public Integer getIsgb() {
        return isgb;
    }

    public void setIsgb(Integer isgb) {
        this.isgb = isgb;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }
}
