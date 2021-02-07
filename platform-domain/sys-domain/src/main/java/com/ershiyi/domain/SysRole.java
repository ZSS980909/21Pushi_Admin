package com.ershiyi.domain;

import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Table;
import java.io.Serializable;
/**
 * 表 SYS_ROLE
 *
 * @author zaz
 * @date 2020-03-06
 */
@Data
@Table(name = "SYS_ROLE")
@ApiModel(value="SysRole", description = "系统角色")
public class SysRole  extends AbstractBaseDomain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用")
    private Integer ifuse;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String rolename;
    /**
     * 单位编码
     */
    @ApiModelProperty("单位编码")
    private String prisonid;

    /**
     * 岗位编码
     */
    @ApiModelProperty("岗位编码")
    private String post;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIfuse() {
        return ifuse;
    }

    public void setIfuse(Integer ifuse) {
        this.ifuse = ifuse;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPrisonid() {
        return prisonid;
    }

    public void setPrisonid(String prisonid) {
        this.prisonid = prisonid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

//    @Override
//    public String toString() {
//        return ToStxringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
//    }
}
