package com.ershiyi.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 榜单标题
 */
@Data
@Table(name = "COMMON_BILLBOARD")
@ApiModel(value="billboard", description = "榜单标题")
public class Billboardtitle {
    /**
     * 主键ID
     */
    @Column(name = "ID")
    @ApiModelProperty(value="主键id")
    private  Integer id;

    /**
     * 榜单key
     */
    @Column(name = "BILLBOARDKEY")
    @ApiModelProperty(value="榜单key")
    private  String billboardkey  = "";

    /**
     * 榜单名称
     */
    @Column(name = "BILLBOARDNAME")
    @ApiModelProperty(value="榜单名称")
    private  String billboardname  = "";

    /**
     * 是否删除
     */
    @Column(name = "DELETED")
    @ApiModelProperty(value="是否删除")
    private  Integer deleted;

    /**
     * 是否使用
     */
    @Column(name = "IFUSER")
    @ApiModelProperty(value="是否使用")
    private  Integer ifuser;
    /**
     * 标记 0代表榜单,1代表学科
     */
    @Column(name = "BILLBOARTYPE")
    @ApiModelProperty(value="标记 0代表榜单,1代表学科")
    private  Integer billboartype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillboardkey() {
        return billboardkey;
    }

    public void setBillboardkey(String billboardkey) {
        this.billboardkey = billboardkey;
    }

    public String getBillboardname() {
        return billboardname;
    }

    public void setBillboardname(String billboardname) {
        this.billboardname = billboardname;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getIfuser() {
        return ifuser;
    }

    public void setIfuser(Integer ifuser) {
        this.ifuser = ifuser;
    }

    public Integer getBillboartype() {
        return billboartype;
    }

    public void setBillboartype(Integer billboartype) {
        this.billboartype = billboartype;
    }
}
