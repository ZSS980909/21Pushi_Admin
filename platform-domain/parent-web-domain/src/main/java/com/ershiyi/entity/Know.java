package com.ershiyi.entity;

import lombok.Data;

/**
 * @Description: 知识点列表
 * @author: zss98
 * @date: 2020-12-07 19:18
 * @version: 1.0
 */
@Data
public class Know {
    private int knowId;  // 知识点id
    private String knowName;  // 知识点名称
    private int status = 1;  // 知识点掌握状态 1为已掌握
}


