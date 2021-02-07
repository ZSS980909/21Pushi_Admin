//package com.ershiyi.domain;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Table;
//
//@Data
//@Table(name = "SYS_USER_INTEGRAL_ENTRY")
//@ApiModel(value="SignIn", description = "积分任务表")
//public class SignInWork {
//    /**
//     * 主键ID
//     */
//    @Column(name = "ID")
//    @ApiModelProperty(value="主键id")
//    private  Integer id;
//    /**
//     * 积分
//     */
//    @Column(name = "INTEGRALVALUE")
//    @ApiModelProperty(value="积分")
//    private  String Integralvalue;
//
//    /**
//     * 任务名称
//     */
//    @Column(name = "INTEGRALNAME")
//    @ApiModelProperty(value="任务名称")
//    private  String Integralname;
//
//    /**
//     * 任务描述
//     */
//    @Column(name = "INTEGRALDESCRIBE")
//    @ApiModelProperty(value="任务描述")
//    private  String Integraldescribe;
//    /**
//     * 创建时间
//     */
//    @Column(name = "CREATEDT")
//    @ApiModelProperty(value="创建时间")
//    private  String createdt;
//
//    /**
//     * 任务限制次数
//     */
//    @Column(name = "FREQUENCY")
//    @ApiModelProperty(value="任务限制次数")
//    private  String frequency;
//    /**
//     * 任务关键字
//     */
//    @Column(name = "KEYWORD")
//    @ApiModelProperty(value="任务关键字")
//    private  String keyword;
//    /**
//     * 是否删除
//     */
//    @Column(name = "DELETED")
//    @ApiModelProperty(value="是否删除")
//    private  String deleted;
//    /**
//     * 学校编号
//     */
//    @Column(name = "schoolId")
//    @ApiModelProperty(value="学校编号")
//    private  String schoolId;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getIntegralvalue() {
//        return Integralvalue;
//    }
//
//    public void setIntegralvalue(String integralvalue) {
//        Integralvalue = integralvalue;
//    }
//
//    public String getIntegralname() {
//        return Integralname;
//    }
//
//    public void setIntegralname(String integralname) {
//        Integralname = integralname;
//    }
//
//    public String getFrequency() {
//        return frequency;
//    }
//
//    public void setFrequency(String frequency) {
//        this.frequency = frequency;
//    }
//
//    public String getKeyword() {
//        return keyword;
//    }
//
//    public void setKeyword(String keyword) {
//        this.keyword = keyword;
//    }
//
//    public String getDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(String deleted) {
//        this.deleted = deleted;
//    }
//
//    public String getschoolId() {
//        return schoolId;
//    }
//
//    public void setschoolId(String schoolId) {
//        this.schoolId = schoolId;
//    }
//
//    public String getIntegraldescribe() {
//        return Integraldescribe;
//    }
//
//    public void setIntegraldescribe(String integraldescribe) {
//        Integraldescribe = integraldescribe;
//    }
//
//    public String getCreatedt() {
//        return createdt;
//    }
//
//    public void setCreatedt(String createdt) {
//        this.createdt = createdt;
//    }
//}
