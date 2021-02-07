package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 上课时间
 * @author: zss98
 * @date: 2020-07-31 17:11
 * @version: 1.0
 */
@Data
public class ClassTime {
    private String week = "";  // 星期几
    private String time = ""; // 上课时间
    private Integer curriculumId; // 课表id
}
