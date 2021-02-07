package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.service.BornService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 天生我才必有用
 */
@RestController
@RequestMapping("/born")
public class BornController {
    @Autowired
    private BornService service;
    /**
     * 查询做题情况
     */
    @PostMapping("/questionSituation")
    @ResponseBody
    @ApiOperation(value = "天生我材必有用", notes = "查询做题情况")
    public AbstractBaseResult questionSituation(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.questionSituation(localtionrequest));
    }

    /**
     * 学习知识数量
     */
    @PostMapping("/knowledgeNumber")
    @ResponseBody
    @ApiOperation(value = "天生我材必有用", notes = "学习知识点数量")
    public AbstractBaseResult knowledgeNumber(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.knowledgeNumber(localtionrequest));
    }

    /**
     * 查询学习时长
     */
        @PostMapping("/studyDuration")
        @ResponseBody
        @ApiOperation(value = "天生我材必有用", notes = "查询学习时长")
        public AbstractBaseResult studyDuration(@RequestBody LocationRequestDTO localtionrequest) {
            return RespEnum.OK.result(service.studyDuration(localtionrequest));
        }

    /**
     * 查询该学生今天学了多少时长,各个科目
     */
    @PostMapping("/studyTime")
    @ResponseBody
    @ApiOperation(value = "天生我材必有用", notes = "查询该学生今天学了多少时长,各个科目")
    public AbstractBaseResult studyTime(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.studyTime(localtionrequest));
    }

    /**
     * 查询该学生各科的综合得分
     */
    @PostMapping("/syntheticalScore")
    @ResponseBody
    @ApiOperation(value = "天生我材必有用", notes = "查询该学生综合得分")
    public AbstractBaseResult syntheticalScore(@RequestBody LocationRequestDTO localtionrequest) {
        return RespEnum.OK.result(service.syntheticalScore(localtionrequest));
    }
}
