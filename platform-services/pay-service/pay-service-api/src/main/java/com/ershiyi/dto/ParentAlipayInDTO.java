package com.ershiyi.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ParentAlipayInDTO {
    @ApiModelProperty(value = "学生编号")
    private String studenterId;
    @ApiModelProperty(value = "家长编号")
    private String parentId;
    @ApiModelProperty(value = "家长姓名")
    private String payName;

    @ApiModelProperty(value = "变化积分")
    private Double changeIntegral;
    @ApiModelProperty(value = "原始积分")
    private Double rawIntegral;
    @ApiModelProperty(value = "最终积分")
    private Double integralValue;
    @ApiModelProperty(value = "充值金额")
    private Double rechargeAmount;

    @ApiModelProperty(value = "充值时间")
    private Date prerechargedt;

    @ApiModelProperty(value = "充值方式  1微信  2支付宝")
    private Integer paymentType;

    @ApiModelProperty(value = "支付状态：-1：交易关闭，0：预充值（预退款），1：到帐")
    private Integer status;
    @ApiModelProperty(value = "商户订单号（由我方生成）")
    private String orderId;
    @ApiModelProperty(value = "商品描述")
    private String shopBody;
    @ApiModelProperty(value = "商品名称")
    private String shopName;

    @ApiModelProperty(value = "学校编号")
    private String schoolId;

    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "类型 1是学生购买  2是家长赠送")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public Double getChangeIntegral() {
        return changeIntegral;
    }

    public void setChangeIntegral(Double changeIntegral) {
        this.changeIntegral = changeIntegral;
    }

    public Double getRawIntegral() {
        return rawIntegral;
    }

    public void setRawIntegral(Double rawIntegral) {
        this.rawIntegral = rawIntegral;
    }

    public Double getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Double integralValue) {
        this.integralValue = integralValue;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopBody() {
        return shopBody;
    }

    public void setShopBody(String shopBody) {
        this.shopBody = shopBody;
    }

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Date getPrerechargedt() {
        return prerechargedt;
    }

    public void setPrerechargedt(Date prerechargedt) {
        this.prerechargedt = prerechargedt;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
