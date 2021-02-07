package com.ershiyi.dto;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 微信支付入参
 *
 * @author : liyong
 */
@ApiModel(value = "微信支付入参")
@Data
public class WechatPayInDTO extends AbstractBaseDomain {

//    @ApiModelProperty(value = "主键")
//    private String guid;
    @ApiModelProperty(value = "学生编号")
    private String studenterId;

    @ApiModelProperty(value = "微信唯一号")
    private String openId;

    @ApiModelProperty(value = "学校编号")
    private String schoolId;

    @ApiModelProperty(value = "商户订单号（由我方生成）")
    private String orderId;

    @ApiModelProperty(value = "任务key")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @ApiModelProperty(value = "变化积分")
    private Double changeIntegral;
    @ApiModelProperty(value = "原始积分")
    private Double rawIntegral;
    @ApiModelProperty(value = "最终积分")
    private Double integralValue;

    @ApiModelProperty(value = "充值人员姓名")
    private String Name;

    @ApiModelProperty(value = "充值金额")
    private Integer rechargeAmount;

    @ApiModelProperty(value = "充值时间")
    private Date prerechargedt;

    @ApiModelProperty(value = "充值方式  1微信  2支付宝")
    private Integer paymentType;

    @ApiModelProperty(value = "支付状态：-1：交易关闭，0：预充值（预退款），1：到帐")
    private Integer status;

//    @ApiModelProperty(value = "审核状态：0：待审核，1：已确认，-1：已退款")
//    private Integer verifyStatus;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Integer rechargeAmount) {
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

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
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

}
