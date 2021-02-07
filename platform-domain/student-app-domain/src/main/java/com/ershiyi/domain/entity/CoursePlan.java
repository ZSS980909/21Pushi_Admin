package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 学习计划
 * @author: zss98
 * @date: 2020-08-29 10:59
 * @version: 1.0
 */
@Data
public class CoursePlan extends CoursePojo{
    private Integer planType = 0;  //计划类型
    private String planTime = "";  // 计划学习时间

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }
}
