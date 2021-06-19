package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.entity.ResultQuestion;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.KnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 知识点方法前端控制前
 * @author: zss98
 * @date: 2020-09-18 15:12
 * @version: 1.0
 */
@RestController
@RequestMapping("/know")
@ApiOperation(value = "知识点配对")
@Api(value = "知识点配对", tags = {"知识点配对"})
public class KnowledgeController {

    @Autowired
    private KnowledgeService service;

    @PostMapping("/courseList")
    @ApiOperation(value = "返回课程列表")
    public AbstractBaseResult courseList(){
        try {
            return RespEnum.OK.result(service.courseList());
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    @PostMapping("/submitRelation")
    @ApiOperation(value = "学生提交题目关联的知识点")
    public AbstractBaseResult submitRelation(@RequestBody RequestDTO request){
        try {
            Integer result = service.submitRelation(request);
            if(result==0){
                return RespEnum.SYS_ERROR.result("关联失败！");
            }
            return RespEnum.OK.result(result);
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 获取一道当前科目下学生未关联的题目
     * @param request studenterId 学生编号 subjectId 科目id
     * @return
     */
    @PostMapping("/getQuestion")
    @ApiOperation(value = "获取该科目下的一道题目")
    public AbstractBaseResult getQuestion(@RequestBody RequestDTO request){
        try{
            ResultQuestion question = service.getQuestion(request);
            if (question==null){
                return RespEnum.SYS_ERROR.result("暂无题目可以关联");
            }
            return RespEnum.OK.result(question);
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 用户刷新题目，标记题目状态
     * @param request
     * @return
     */
    @PostMapping("/restQuestion")
    @ApiOperation("刷新题目")
    public AbstractBaseResult restQuestion(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.restQuestion(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }
    /**
     * 获取节点下的知识点内容列表
     */
    @PostMapping("/getKnowList")
    @ApiOperation("获取节点下的知识点内容列表")
    public AbstractBaseResult getKnowList(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.getKnowList(request));
        }catch (Exception e){
            e.printStackTrace();
            return  RespEnum.ERROR.result("系统繁忙！");
        }
    }
}
