package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 分支信息
 * @author: zss98
 * @date: 2021-01-20 14:18
 * @version: 1.0
 */
@Data
public class Node {
    // 节点id
    private Integer id ;
    // 节点名称
    private String name;
    // 节点状态 是否已经学习
    private Integer category;
    // 节点等级
    private Integer symbolSize;

    public Node(Integer id,String name,Integer category,Integer symbolSize){
        this.id = id;
        this.name = name;
        this.category = category;
        this.symbolSize = symbolSize;
    }

    public Node(){

    }
}
