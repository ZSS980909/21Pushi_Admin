package com.ershiyi.dto;

import com.ershiyi.common.dto.AbstractBaseDTO;

public class questiontext1pojo extends AbstractBaseDTO {
    private String  msg;
    private  String code;
    private  String  date ;
    private  String  utime ;
    private String  ctime ;
    private  String  knowledge_tree;
    private String children;
    private String  name;
    private  String  id ;
    private  String  key ;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getKnowledge_tree() {
        return knowledge_tree;
    }

    public void setKnowledge_tree(String knowledge_tree) {
        this.knowledge_tree = knowledge_tree;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
