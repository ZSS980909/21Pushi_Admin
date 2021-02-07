package com.ershiyi.domain.entity;

import lombok.Data;

/**
 * @Description: 节点关系图
 * @author: zss98
 * @date: 2021-01-20 15:10
 * @version: 1.0
 */
@Data
public class Link {
    // 节点来源id
    private Integer source;
    // 节点目标id
    private Integer target;

    public Link(){

    }

    public Link(Integer source,Integer target){
        this.source = source;
        this.target = target;
    }

}
