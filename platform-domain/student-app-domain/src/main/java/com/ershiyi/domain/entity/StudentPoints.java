package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 学生积分类
 * @author: zss98
 * @date: 2020-08-28 11:02
 * @version: 1.0
 */
@Data
public class StudentPoints<T> {
    private Integer changeIntegral = 0;  // 当日变动积分
    private Integer integralValue = 0; // 学生当前所有积分
    private List<T>  IntegralTasks = new ArrayList<>();  // 所有的积分任务

    public Integer getChangeIntegral() {
        return changeIntegral;
    }

    public void setChangeIntegral(Integer changeIntegral) {
        this.changeIntegral = changeIntegral;
    }

    public Integer getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    public List<T> getIntegralTasks() {
        return IntegralTasks;
    }

    public void setIntegralTasks(List<T> integralTasks) {
        IntegralTasks = integralTasks;
    }

    // 暂时先不做历史积分
    // private List<T>  historyFinish;  // 学生历史积分任务
}
