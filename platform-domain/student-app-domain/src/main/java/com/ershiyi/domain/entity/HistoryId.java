package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 历史记录id值实体类
 * @author: zss98
 * @date: 2020-07-30 09:21
 * @version: 1.0
 */
@Data
public class HistoryId {
    private Integer historyId;  // 历史记录表id
    private Integer courseId;  // 课程id
}
