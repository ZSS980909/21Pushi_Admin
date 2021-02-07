package com.ershiyi.vo;

import com.ershiyi.domain.SysMenu;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 *  用于菜单递归  tree结构
 *
 * @author zaz
 * @date 2020-03-09
 */
@Data
public class SysMenuVo extends SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;
   private List<SysMenuVo> ChildMenus;

   private String menupname;

   private Integer sfxz;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<SysMenuVo> getChildMenus() {
        return ChildMenus;
    }

    public void setChildMenus(List<SysMenuVo> childMenus) {
        ChildMenus = childMenus;
    }

    public String getMenupname() {
        return menupname;
    }

    public void setMenupname(String menupname) {
        this.menupname = menupname;
    }

    public Integer getSfxz() {
        return sfxz;
    }

    public void setSfxz(Integer sfxz) {
        this.sfxz = sfxz;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
