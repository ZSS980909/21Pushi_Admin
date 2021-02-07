package com.ershiyi.domain;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 表 SYS_MENU
 *
 * @author zaz
 * @date 2020-03-09
 */
@Data
@Table(name = "SYS_MENU")
@ApiModel(value="SysMenu", description = "系统菜单")
public class    SysMenu  extends AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 父节点ID(根节点为-1)
     */
    @Column(name = "MENUPID")
    @ApiModelProperty(value="父节点ID(根节点为-1)")
    private String menupid;
    /**
     * 菜单打开方式(0：非页面，1：iframe，2：新窗口页面)
     */
    @Column(name = "OPENTYPE")
    @ApiModelProperty(value="菜单打开方式(0：非页面，1：iframe，2：新窗口页面)")
    private BigDecimal opentype;

    /**
     * 排序
     */
    @Column(name = "SORT")
    @ApiModelProperty(value="排序")
    private BigDecimal sort;
    /**
     * 图标路径
     */
    @Column(name = "IMGPATH")
    @ApiModelProperty(value="图标路径")
    private String imgpath;
    /**
     * 菜单名称
     */
    @Column(name = "MENUNAME")
    @ApiModelProperty(value="菜单名称")
    private String menuname;
    /**
     * 路径
     */
    @Column(name = "PATH")
    @ApiModelProperty(value="路径")
    private String path;
    /**
     * 背景色
     */
    @Column(name = "BGCOLOR")
    @ApiModelProperty(value="背景色")
    private String bgcolor;

    /**
     * 是否启用 (0:否 1:是)
     */
    @Column(name = "IFUSE")
    @ApiModelProperty(value="是否启用 (0:否 1:是)")
    private BigDecimal ifuse;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMenupid() {
        return menupid;
    }

    public void setMenupid(String menupid) {
        this.menupid = menupid;
    }

    public BigDecimal getOpentype() {
        return opentype;
    }

    public void setOpentype(BigDecimal opentype) {
        this.opentype = opentype;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public BigDecimal getIfuse() {
        return ifuse;
    }

    public void setIfuse(BigDecimal ifuse) {
        this.ifuse = ifuse;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
