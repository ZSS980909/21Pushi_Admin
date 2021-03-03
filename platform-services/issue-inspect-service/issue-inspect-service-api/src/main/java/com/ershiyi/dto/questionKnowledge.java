package com.ershiyi.dto;

import com.ershiyi.common.dto.AbstractBaseDTO;
import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "questionKnowledge")
@ApiModel(value="questionKnowledge", description = "")
public class questionKnowledge extends AbstractBaseDomain {
    private String Id;
    private  String knowledge_name;
    private String subject_name;
    private String period;
    private String  idenification;
    private String children_no;
    private  String children_seond;
    private String children_three;
    private String coursetype;

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getKnowledge_name() {
        return knowledge_name;
    }

    public void setKnowledge_name(String knowledge_name) {
        this.knowledge_name = knowledge_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getIdenification() {
        return idenification;
    }

    public void setIdenification(String idenification) {
        this.idenification = idenification;
    }

    public String getChildren_no() {
        return children_no;
    }

    public void setChildren_no(String children_no) {
        this.children_no = children_no;
    }

    public String getChildren_seond() {
        return children_seond;
    }

    public void setChildren_seond(String children_seond) {
        this.children_seond = children_seond;
    }

    public String getChildren_three() {
        return children_three;
    }

    public void setChildren_three(String children_three) {
        this.children_three = children_three;
    }
}
