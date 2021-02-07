package com.ershiyi.domain;

import com.ershiyi.genid.GenerateUUID;
import com.ershiyi.utils.TokenUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Accessors(chain = true)
public abstract class AbstractBaseDomain implements Serializable {
    public AbstractBaseDomain init(){
        System.out.println("進入方法");
        String userKey = TokenUtils.getUserKey();
        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String nowTime = sdf.format(nowDate);
        BigDecimal nowTimestamp = BigDecimal.valueOf(nowDate.getTime());
        if(StringUtils.isEmpty(getGuid())){
            setCreatorId(userKey);
            setCreateDate(nowTime);
        }
        setModifierid(userKey);
        setModifyDate(nowTime);
      //  setTimestamp(nowTimestamp);
        return this;
    }

    /** 主键 **/
    //public static final String GUID = "guid";
    public static final String GUID = "guid";
    /** 逻辑删除标识 **/
    public static final String DELETED = "deleted";

    /**
     * 主键
     */
    @Id
    @KeySql(genId = GenerateUUID.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUID")
    @ApiModelProperty(value="主键")
    private String guid = "";

    @Column(name = "DELETED")
    @ApiModelProperty(value="")
    private Integer deleted = 0;

    @Column(name = "CREATEDT")
    @ApiModelProperty(value="")
    private String createDate = "";

    @Column(name = "CREATORID")
    @ApiModelProperty(value="")
    private String creatorId ="";

    @Column(name = "CREATOR")
    @ApiModelProperty(value="")
    private String creator ="";

    @Column(name = "MODIFYDT")
    @ApiModelProperty(value="")
    private String modifyDate ="";

    @Column(name = "MODIFIERID")
    @ApiModelProperty(value="")
    private String modifierid ="";

    @Column(name = "MODIFIER")
    @ApiModelProperty(value="")
    private String modifier = "";

//    @Column(name = "\"TIMESTAMP\"")
//    @ApiModelProperty(value="")
//    private Timestamp timestamp;

}
