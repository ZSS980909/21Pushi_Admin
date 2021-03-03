package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.Common_Choice;
import com.ershiyi.domain.Common_StudyrateBy;
import com.ershiyi.dto.ExamDTO;
import com.ershiyi.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟考试功能
 */
@RestController
@RequestMapping("/Exam")
@Api(value = "模拟考试", tags = {"模拟考试"})
public class ExamController {
    @Autowired
    private ExamService examseservice;
    /**
     * 考试根据章节出题
     */
    @PostMapping("/randomExam")
    @ResponseBody
    @ApiOperation(value = "考试根据章节出题", notes = "考试根据章节出题")
    public AbstractBaseResult JHZCourse(@RequestBody HashMap<String,List<ExamDTO>> request) {
        try{

            return RespEnum.OK.result(examseservice.randomExam(request.get("data")));

        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }
    /**
     * 获取考试结果,提交数据
     */
    @PostMapping("/submitExam")
    @ResponseBody
    @ApiOperation(value = "获取考试结果,提交数据", notes = "获取考试结果,提交数据")
    public AbstractBaseResult submitExam(@RequestBody Map<String, List<Common_Choice>> list) {
        return RespEnum.OK.result(examseservice.submitExam(list));
    }
}
