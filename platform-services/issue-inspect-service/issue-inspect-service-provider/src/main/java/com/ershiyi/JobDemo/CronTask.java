package com.ershiyi.JobDemo;

import javax.persistence.Transient;
import java.util.Map;

public class CronTask {
    /** 任务名称 */
    private String jobName;

    /** 参数 */
    private String parameter;

    /** 任务分组 1:   2： */
    private Long type;

    /** 任务分组名 */
    private String typeName;

    /** 调度规则：0/5 * * * * ? */
    private String cronExpression;

    /**  任务执行时调用哪个类的方法 包名+类名 */
    private String beanClass;

    /** 任务描述 */
    private String description;

    /**  任务状态 */
    private Long jobStatus;

    /** 0 未删除   1 删除 */
    private Long deleteFlag;

    /**参数的map*/
    @Transient
    private Map<String,String> parameterMap;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Long jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }
}