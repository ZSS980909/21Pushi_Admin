package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.List;

@Data
@Table(name = "COMMON_COURSE_MULTI")
@ApiModel(value="multi", description = "多选题")
public class Common_Multi {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer questionId;
    /**
     * 知识点编号
     */
    @Column(name = "KNOWLEDGEID")
    @ApiModelProperty(value="知识点编号")
    private  String knowId;
    /**
     * 选项集合
     */
    @Column(name = "OPTION")
    @ApiModelProperty(value="选项集合")
    private List options ;
    /**
     * 选项A
     */
    @Column(name = "ID")
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
    @Column(name = "TITILE")
    @ApiModelProperty(value="题目")
    private  String question;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getKnowId() {
        return knowId;
    }

    public void setKnowId(String knowId) {
        this.knowId = knowId;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
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
}
