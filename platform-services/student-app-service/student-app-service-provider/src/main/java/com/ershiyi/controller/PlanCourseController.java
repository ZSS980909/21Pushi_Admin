package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.PlanCourseDTO;
import com.ershiyi.service.PlanCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  查询计划中课程
 */
@RestController
@RequestMapping("/Plan")
@Api(value = "计划课程 --功能废弃", tags = {"相关计划课程"})
public class
PlanCourseController {
    @Autowired
    private PlanCourseService planCourseservice;
    /**
     * 用户签到
     */
    @PostMapping("/plancourse")
    @ResponseBody
    @ApiOperation(value = "提醒功能中计划中课程查询", notes = "提醒功能中计划中课程查询")
    public AbstractBaseResult plancourse(@RequestBody PlanCourseDTO plancourse) {
        return RespEnum.OK.result(planCourseservice.plancourse(plancourse));
    }
}
