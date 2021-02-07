package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.service.SysLoginService;
import com.ershiyi.service.SysSchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/systemSchool")
@Api(value = "学校信息管理", tags = {"学校信息"})
public class SysSchoolController {
    @Autowired
    private SysSchoolService schoolService;

    /**
     * 查询所有学校信息(目前数据库只有湖南区域)
     */
    @PostMapping("/allSchool")
    @ResponseBody
    @ApiOperation(value = "查询所有学校信息", notes = "不需要传参数")
    public AbstractBaseResult allSchool() {
        return RespEnum.OK.result(schoolService.allSchool());

    }
}
