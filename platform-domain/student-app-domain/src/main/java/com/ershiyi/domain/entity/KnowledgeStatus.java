package com.ershiyi.domain.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 知识点状态信息
 * @author: zss98
 * @date: 2021-01-19 17:36
 * @version: 1.0
 */
@Data
public class KnowledgeStatus {
    private List categorys = new ArrayList();
    private List nodes = new ArrayList();
    private List links = new ArrayList();

    public KnowledgeStatus(){
        List<HashMap> categorys = new ArrayList<>();
        HashMap status_1 = new HashMap();
        status_1.put("id",0);
        status_1.put("name","未学习");
        HashMap status_2 = new HashMap();
        status_2.put("id",1);
        status_2.put("name","已学习");
        categorys.add(status_1);
        categorys.add(status_2);
        this.categorys = categorys;
     }
}
