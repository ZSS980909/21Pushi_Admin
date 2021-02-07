package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.TutoringService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 家长端一对一补教前端控制器
 * @author: zss98
 * @date: 2020-12-23 09:26
 * @version: 1.0
 */
@RestController
@RequestMapping("/Tutoring")
@ApiOperation("家长端一对一补教前端控制器")
public class TutoringController {

    @Autowired
    public TutoringService service;

    /**
     * 获取老师列表信息
     * @param request
     * @return
     */
    @RequestMapping("/TeacherList")
    public AbstractBaseResult TeacherList(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.TeacherList(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 根据姓名搜索老师
     * @param request
     * @return
     */
    @RequestMapping("/SearchTeacher")
    public AbstractBaseResult SearchTeacher(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.SearchTeacher(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }


    /**
     * 老师评论信息列表
     * @param request
     * @return
     */
    @RequestMapping("/CommentInfo")
    public AbstractBaseResult CommentInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.CommentInfo(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

}
