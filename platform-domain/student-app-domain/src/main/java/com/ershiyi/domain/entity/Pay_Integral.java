package com.ershiyi.domain.entity;

import lombok.Data;

/**
 *
 */
@Data
public class Pay_Integral {
    private String studenterId;
    private String changeIntegral;
    private String rawIntegral;
    private String integralValue;
    private String status;
    private String schoolId;
    private String keyWord;
    private String rechargeAmount;
    private String openId;
    private String orderId;
    private String paymentType;

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getChangeIntegral() {
        return changeIntegral;
    }

    public void setChangeIntegral(String changeIntegral) {
        this.changeIntegral = changeIntegral;
    }

    public String getRawIntegral() {
        return rawIntegral;
    }

    public void setRawIntegral(String rawIntegral) {
        this.rawIntegral = rawIntegral;
    }

    public String getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(String integralValue) {
        this.integralValue = integralValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
