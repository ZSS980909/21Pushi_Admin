package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.entity.CurriculumPojo;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.TimeTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 课程表前后端交互lei
 * @author: zss98
 * @date: 2020-07-31 18:44
 * @version: 1.0
 */
@RestController
@RequestMapping("/curriculum")
@Api(value = "学生课表",tags = {"查询学生课程表安排，添加修改课表"})
public class TimeTableController {
    @Autowired
    private TimeTableService service;

    /**
     * 根据学生编号查询学生的课表信息
     * @param request 请求集合实体类
     * @return
     */
    @PostMapping("/table")
    @ApiOperation(value = "查询学生课表")
    public AbstractBaseResult queryAllTimeTable(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.queryCurriculumTable(request.getStudenterId()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，稍后重试");
        }
    }


    /**
     * 查询当前课程下有多少节
     * @param request 请求集合实体类
     * @return
     */
    @PostMapping("/queryKnowNUmber")
    @ApiOperation("查询当前课程下有多少节")
    public AbstractBaseResult queryKnowNUmber(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.queryKnowNUmber(request.getCourseId()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，稍后重试");
        }
    }
    /**
     * 根据学生编号查询学生该课程的安排
     * @param request 请求集合实体类
     * @return
     */
    @PostMapping("/queryCurriculum")
    @ApiOperation(value = "根据学生编号查询课程安排")
    public AbstractBaseResult queryCurriculum(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.findCurriculumByCourse(request.getStudenterId(),request.getCourseId()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，请稍后重试");
        }
    }

    @PostMapping("/setCoursePlan")
    @ApiOperation("设置学生学习计划安排")
    public AbstractBaseResult addCoursePlan(@RequestBody CurriculumPojo curriculum){
        try{
            Integer result = service.setCoursePlan(curriculum);
            if(result!=0){
                return RespEnum.OK.result("成功");
            }else{
                return RespEnum.SYS_ERROR.result("失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 学生添加学习计划
     * @param curriculum 学生课程计划安排信息实体类
     * @return
     */
    @PostMapping("/addCurriculum")
    @ApiOperation(value = "添加学生课程安排")
    public AbstractBaseResult addCurriculum(@RequestBody CurriculumPojo curriculum){
        try{
            int flag = service.checkRepeat(curriculum.getPlanTime(),curriculum.getPlanWeek(),curriculum.getStudenterId(),curriculum.getCourseId());
            if(flag==201){
                return RespEnum.CHECK_FAILED.result("该时间段已有计划安排，请重新选择");
            }
            if(flag==202||flag==203){
                return RespEnum.CHECK_FAILED.result("该课程当天已安排，不能再安排");
            }
            int result = service.addCurriculum(curriculum);
            if (result == 0) {
                return RespEnum.ERROR.result("添加失败,请检查用户信息");
            }else{
                return RespEnum.OK.result("成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("新增失败，请稍后重试");
        }
    }

    /**
     * 重置学生该课程计划
     * @param curriculum 学生课程计划安排信息实体类
     * @return
     */
    @PostMapping("/resetCurriculum")
    @ApiOperation(value = "重置学生课程计划安排")
    public AbstractBaseResult resetCurriculum(@RequestBody CurriculumPojo curriculum){
        try{
            int result = service.resetCurriculum(curriculum.getCurriculumId());
            if (result == 0) {
                return RespEnum.ERROR.result("修改失败,请检查用户信息");
            }else{
                return RespEnum.OK.result("修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("修改失败，请稍后重试");
        }
    }

}
