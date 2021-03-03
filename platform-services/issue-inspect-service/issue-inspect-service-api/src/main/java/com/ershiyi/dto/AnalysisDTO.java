package com.ershiyi.dto;

import com.ershiyi.common.dto.AbstractBaseDTO;
import com.ershiyi.domain.AbstractBaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="AnalysisDTO", description = "数据分析实体类")
public class AnalysisDTO  extends AbstractBaseDomain {
    @ApiModelProperty(value="学生编号")
    private String studenterId;

}
