package com.ershiyi.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 表 COMMON_PRISON
 *
 * @author zaz
 * @date 2020-03-18
 */
public class CommonPrison implements Serializable
        {
private static final long serialVersionUID = 1L;

/** 监所名称 */
private String prisonname;
/** 父级单位 */
private String prisonpid;
/** 1:看守所 2:拘留所 3:戒毒所:4:收教所 8:其它 9:支队 10:省厅(原为----0:看守所 1:支队 2:省厅) */
private Integer orglevel;
/** $column.columnComment */
private Date modifydt;
/** $column.columnComment */
private String modifier;
/** $column.columnComment */
private String creator;
/** $column.columnComment */
private Long timestamp;
/** 排序 */
private Integer sort;
/** $column.columnComment */
private Integer deleted;
/** 主键 */
private String guid;
/** 等级标识，1：省厅，2：支队，3：监所   暂时弃用 */
private Integer grademark;
/** $column.columnComment */
private Date createdt;
/** $column.columnComment */
private String modifierid;
/** 监所编号 */
private String prisonid;
/** 监所短号 */
private String prisonshortid;
/** 监所简称 */
private String prisonshortname;
/** $column.columnComment */
private String creatorid;

@ApiModelProperty("监所名称")
public void setPrisonname(String prisonname)
        {
        this.prisonname = prisonname;
        }
@ApiModelProperty("监所名称")
public String getPrisonname()
        {
        return prisonname;
        }
@ApiModelProperty("父级单位")
public void setPrisonpid(String prisonpid)
        {
        this.prisonpid = prisonpid;
        }
@ApiModelProperty("父级单位")
public String getPrisonpid()
        {
        return prisonpid;
        }
@ApiModelProperty("1:看守所 2:拘留所 3:戒毒所:4:收教所 8:其它 9:支队 10:省厅(原为----0:看守所 1:支队 2:省厅)")
public void setOrglevel(Integer orglevel)
        {
        this.orglevel = orglevel;
        }
@ApiModelProperty("1:看守所 2:拘留所 3:戒毒所:4:收教所 8:其它 9:支队 10:省厅(原为----0:看守所 1:支队 2:省厅)")
public Integer getOrglevel()
        {
        return orglevel;
        }
@ApiModelProperty("$column.columnComment")
public void setModifydt(Date modifydt)
        {
        this.modifydt = modifydt;
        }
@ApiModelProperty("$column.columnComment")
public Date getModifydt()
        {
        return modifydt;
        }
@ApiModelProperty("$column.columnComment")
public void setModifier(String modifier)
        {
        this.modifier = modifier;
        }
@ApiModelProperty("$column.columnComment")
public String getModifier()
        {
        return modifier;
        }
@ApiModelProperty("$column.columnComment")
public void setCreator(String creator)
        {
        this.creator = creator;
        }
@ApiModelProperty("$column.columnComment")
public String getCreator()
        {
        return creator;
        }
@ApiModelProperty("$column.columnComment")
public void setTimestamp(Long timestamp)
        {
        this.timestamp = timestamp;
        }
@ApiModelProperty("$column.columnComment")
public Long getTimestamp()
        {
        return timestamp;
        }
@ApiModelProperty("排序")
public void setSort(Integer sort)
        {
        this.sort = sort;
        }
@ApiModelProperty("排序")
public Integer getSort()
        {
        return sort;
        }
@ApiModelProperty("$column.columnComment")
public void setDeleted(Integer deleted)
        {
        this.deleted = deleted;
        }
@ApiModelProperty("$column.columnComment")
public Integer getDeleted()
        {
        return deleted;
        }
@ApiModelProperty("主键")
public void setGuid(String guid)
        {
        this.guid = guid;
        }
@ApiModelProperty("主键")
public String getGuid()
        {
        return guid;
        }
@ApiModelProperty("等级标识，1：省厅，2：支队，3：监所   暂时弃用")
public void setGrademark(Integer grademark)
        {
        this.grademark = grademark;
        }
@ApiModelProperty("等级标识，1：省厅，2：支队，3：监所   暂时弃用")
public Integer getGrademark()
        {
        return grademark;
        }
@ApiModelProperty("$column.columnComment")
public void setCreatedt(Date createdt)
        {
        this.createdt = createdt;
        }
@ApiModelProperty("$column.columnComment")
public Date getCreatedt()
        {
        return createdt;
        }
@ApiModelProperty("$column.columnComment")
public void setModifierid(String modifierid)
        {
        this.modifierid = modifierid;
        }
@ApiModelProperty("$column.columnComment")
public String getModifierid()
        {
        return modifierid;
        }
@ApiModelProperty("监所编号")
public void setPrisonid(String prisonid)
        {
        this.prisonid = prisonid;
        }
@ApiModelProperty("监所编号")
public String getPrisonid()
        {
        return prisonid;
        }
@ApiModelProperty("监所短号")
public void setPrisonshortid(String prisonshortid)
        {
        this.prisonshortid = prisonshortid;
        }
@ApiModelProperty("监所短号")
public String getPrisonshortid()
        {
        return prisonshortid;
        }
@ApiModelProperty("监所简称")
public void setPrisonshortname(String prisonshortname)
        {
        this.prisonshortname = prisonshortname;
        }
@ApiModelProperty("监所简称")
public String getPrisonshortname()
        {
        return prisonshortname;
        }
@ApiModelProperty("$column.columnComment")
public void setCreatorid(String creatorid)
        {
        this.creatorid = creatorid;
        }
@ApiModelProperty("$column.columnComment")
public String getCreatorid()
        {
        return creatorid;
        }

@Override
public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
        }

        }
