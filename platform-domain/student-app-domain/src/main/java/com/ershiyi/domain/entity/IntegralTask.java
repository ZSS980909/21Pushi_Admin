package com.ershiyi.domain.entity;

import com.ershiyi.domain.AbstractBaseDomain;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 积分任务
 * @author: zss98
 * @date: 2020-08-28 11:09
 * @version: 1.0
 */
@Data
public class IntegralTask {
    private Integer integralId = 0;  // 当前积分任务的id
    private String integralName = "";    // 积分任务名称
    private String integralDescribe = "";  // 任务详细描述
    private Integer integralValue = 0;   // 当前任务每一次增加的值
    private Integer integralValueAll = 0;  // 当前任务总共增加的积分
    private Integer integralCount = 0;  // 任务总次数
    private Integer finishCount = 0 ;  // 完成的任务次数
    private Integer isFinish = 0;   // 当前任务是否完成
    private Integer pageNumber;  // 页码
    private Integer pageSize;  // 每页展示的数量
    private String  updatedt;//修改时间
    private String  studenterId; //学生编号

    public String getStudenterId() {
        return studenterId;
    }

    public void setStudenterId(String studenterId) {
        this.studenterId = studenterId;
    }

    public String getUpdatedt() {
        return updatedt;
    }

    public void setUpdatedt(String updatedt) {
        this.updatedt = updatedt;
    }

    public Integer getIntegralId() {
        return integralId;
    }

    public void setIntegralId(Integer integralId) {
        this.integralId = integralId;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public String getIntegralDescribe() {
        return integralDescribe;
    }

    public void setIntegralDescribe(String integralDescribe) {
        this.integralDescribe = integralDescribe;
    }

    public Integer getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    public Integer getIntegralValueAll() {
        return integralValueAll;
    }

    public void setIntegralValueAll(Integer integralValueAll) {
        this.integralValueAll = integralValueAll;
    }

    public Integer getIntegralCount() {
        return integralCount;
    }

    public void setIntegralCount(Integer integralCount) {
        this.integralCount = integralCount;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
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
