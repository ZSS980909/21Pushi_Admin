package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 学习计划
 * @author: zss98
 * @date: 2020-08-29 10:59
 * @version: 1.0
 */
@Data
public class A_CoursePlan extends CoursePojo {
    private Integer planType = 0;  //计划类型
    private String planDt = "";  // 下次计划学习时间
    private String splanDt = "";  // 初次计划时间
}
