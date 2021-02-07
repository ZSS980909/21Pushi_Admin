package com.ershiyi.vo;

import com.ershiyi.domain.SysUser;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 *  用户信息
 *
 * @author liy
 * @date 2020-06-11
 */
@Data
public class SysUserVo  implements Serializable {
    private static final long serialVersionUID = 1L;
    private SysUser userInfo;
    private List<SysMenuVo> menus;

    public void setUserInfo(SysUser userInfo) {
        this.userInfo = userInfo;
    }

    public void setMenus(List<SysMenuVo> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
