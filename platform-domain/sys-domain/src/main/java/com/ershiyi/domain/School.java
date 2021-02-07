package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
@Data
@Table(name = "COMMON_SCHOOL")
@ApiModel(value="School", description = "学校信息")
public class School extends AbstractBaseDomain implements Serializable {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;
    /**
     * 学校编号
     */
    @Column(name = "SCHOOL_ID")
    @ApiModelProperty(value="school_id")
    private  String school_id;
    /**
     * 学校名称
     */
    @Column(name = "SCHOOL_NAME")
    @ApiModelProperty(value="school_name")
    private  String school_name;
    /**
     * 学校介绍
     */
    @Column(name = "SCHOOL_PROFILE")
    @ApiModelProperty(value="school_profile")
    private  String school_profile;
    /**
     * 学校所在省份
     */
    @Column(name = "PROVINCE")
    @ApiModelProperty(value="province")
    private  String province;
    /**
     * 学校所在城市
     */
    @Column(name = "CITY")
    @ApiModelProperty(value="city")
    private  String city;
    /**
     * 区域
     */
    @Column(name = "AREA")
    @ApiModelProperty(value="area")
    private  String area;

    /**
     * 学校所在地址
     */
    @Column(name = "ADDRESS")
    @ApiModelProperty(value="address")
    private  String address;
    /**
     * 学校联系电话
     */
    @Column(name = "TEL")
    @ApiModelProperty(value="tel")
    private  String tel;
    /**
     * 学校联系电话
     */
    @Column(name = "SCHOOL_TYPE")
    @ApiModelProperty(value="school_type")
    private  String school_type;
    /**
     * 学校联系电话
     */
    @Column(name = "ORGANIZERSNAME")
    @ApiModelProperty(value="organizersname")
    private  String organizersname;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
