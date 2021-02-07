package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "COMMON_COURSE_JUDGE")
@ApiModel(value="judge", description = "判断题")
public class Common_Judge {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer questionId;
    /**
     * 选项集合
     */
    @Column(name = "OPTION")
    @ApiModelProperty(value="选项集合")
    private List options ;
    /**
     * 知识点编号
     */
    @Column(name = "KNOWLEDGEID")
    @ApiModelProperty(value="知识点编号")
    private  String knowId;
    /**
     * 题目
     */
    @Column(name = "ID")
    @ApiModelProperty(value="题目内容")
    private  String question;
    /**
     * 解析说明
     */
    @Column(name = "CHOICEB")
    @ApiModelProperty(value="解析")
    private  String resolving;
    /**
     * 正确答案
     */
    @Column(name = "CHOICEC")
    @ApiModelProperty(value="正确答案")
    private  String correctOption;
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
     * 题型
     */
    @Column(name = "QUESTIONTYPE")
    @ApiModelProperty(value="题型")
    private  int type;

    /**
     * 题型
     */
    @Column(name = "COURSEID")
    @ApiModelProperty(value="课程iD")
    private  String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * 问题图片
     * @return
     */
    private  String questionImage;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResolving() {
        return resolving;
    }

    public void setResolving(String resolving) {
        this.resolving = resolving;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Integer getIsgb() {
        return isgb;
    }

    public void setIsgb(Integer isgb) {
        this.isgb = isgb;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }


    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }

    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

}
