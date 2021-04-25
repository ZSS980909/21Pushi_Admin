package com.ershiyi.domain.entity;

/**
 * 课程功能实体类
 */
public class Function_setting {
    private Integer id;
    private String key_word;
    private String functionName;
    private String setSize;
    private String deleted;
    private String isUse;
    private String  createdt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getSetSize() {
        return setSize;
    }

    public void setSetSize(String setSize) {
        this.setSize = setSize;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getCreatedt() {
        return createdt;
    }

    public void setCreatedt(String createdt) {
        this.createdt = createdt;
    }
}
