package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-12-23 09:44
 * @version: 1.0
 */
@Data
public class SubjectInfo {
    private int subjectId;  // 科目id
    private String period;  // 阶段
    private String subjectName;  // 科目名称
}
