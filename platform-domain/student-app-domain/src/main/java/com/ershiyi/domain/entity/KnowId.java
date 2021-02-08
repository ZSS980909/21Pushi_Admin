package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 知识点id
 * @author: zss98
 * @date: 2021-02-08 11:33
 * @version: 1.0
 */
@Data
public class KnowId{
    private int knowId;
    private KnowId son;
}
