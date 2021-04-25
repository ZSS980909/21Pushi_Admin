package com.ershiyi.controller;

import com.ershiyi.Utils.IdsUtils;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.entity.A_Feedback;
import com.ershiyi.domain.entity.CommonResult;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.PersonalCenterService;
import com.ershiyi.service.VerificationCodeService;
import com.ershiyi.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @Description: 学生个人信息接口交互
 * @author: zss98
 * @date: 2020-07-28 14:58
 * @version: 1.0
 */
@RestController
@RequestMapping("/center")
@Api(value = "个人中心", tags = {"个人中心"})
public class PersonalCenterController {
    @Autowired
    private PersonalCenterService centerService;

    @Autowired
    private VerificationCodeService messageService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/studentInfo", method = RequestMethod.POST)
    @ApiOperation(value = "查询学生个人中心主页数据", notes = "查询学生个人中心主页数据")
    private AbstractBaseResult StudentInfo(@RequestBody RequestDTO request) {
        return RespEnum.OK.result(centerService.findByStudenterId(request.getStudenterId()));
    }


    /**
     * 根绝学生id修改用户头像
     * @param request 请求集合实体类
     * @return
     */
    @PostMapping("/updateImage")
    @ApiOperation(value = "根据学生id修改用户头像", notes = "根据学生id修改用户头像")
    public AbstractBaseResult UpdateImage(@RequestBody RequestDTO request) {
        try {
            int result = centerService.UpdateImage(request.getGuid(), request.getUrl());
            if (result == 0) {
                return RespEnum.UPLOAD_ERROR.result("修改失败");
            } else {
                return RespEnum.OK.result("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器错误");
        }
    }

    /**
     * 修改学生个人的姓名，性别，生日接口
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/updateInfo")
    @ApiOperation(value = "修改学生个人的姓名，性别，生日接口", notes = "修改学生个人的姓名，性别，生日接口")
    public AbstractBaseResult UpdateInfo(@RequestBody RequestDTO request) {
        try {
            int result = centerService.UpdateInfo(request.getGuid(), request.getName(), request.getSex(), request.getBirthday());
            if (result == 0) {
                return RespEnum.UPLOAD_ERROR.result("修改失败，请检查日志文件");
            } else {
                return RespEnum.OK.result("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("修改失败，请稍后再试");
        }
    }

    /**
     * 修改用户密码
     * @param request guid  用户id passWord  密码  newPass 新密码
     * @return
     */
    @PostMapping("/modifyPass")
    @ApiOperation(value = "修改用户密码")
    public AbstractBaseResult modifyPass(@RequestBody RequestDTO request){
        try{
            int result = centerService.modifyPass(request.getGuid(),request.getPassWord(),request.getNewPass());
            return RespEnum.OK.result(result);
        }catch (Exception e){
            e.printStackTrace();
            return  RespEnum.SYS_ERROR.result("系统繁忙");
        }
    }

    @PostMapping("/sendCode")
    @ApiOperation("校验用户身份信息")
    public AbstractBaseResult sendCode(@RequestBody RequestDTO request) {
        try {
            // 发送验证码
            request.setType(2);
            CommonResult commonResult = messageService.sendVerificationCode(request);
            try{
                if((Integer) commonResult.getData()==201){
                    return RespEnum.ERROR.result("请稍后再进行发送,验证码有效期2分钟");
                }
            }catch (ClassCastException e){
            }
            return RespEnum.OK.result(commonResult.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.SYS_ERROR.result("系统繁忙");
        }

    }



    @PostMapping("modifyPhone")
    @ApiOperation(value = "用户更改手机号")
    public AbstractBaseResult modifyPhone(@RequestBody RequestDTO request){
        try {
            // 判断当前用户原手机是否正确
            List<String> boole = centerService.checkPhone(request);
            if(boole.isEmpty()){
                return RespEnum.OK.result("原手机号不正确！");
            }
            // 验证手机验证码
            boolean flag = RedisUtils.hasKey(request.getMobilePhone());
            if(!flag){
                // 验证码不存在,可能已经失效
                return  RespEnum.SYS_ERROR.result("请重新发送验证码");
            }
            if(!request.getValidataCode().equals(RedisUtils.get(request.getMobilePhone()))){
                return RespEnum.SYS_ERROR.result("验证码不正确");
            }
            // 查询新手机号是否有人使用
            if(!centerService.queryInfoByPhone(request.getMobilePhone()).isEmpty()){
                return RespEnum.CHECK_FAILED.result("手机号已被使用！");
            }
            // 各种没问题，开始修改手机号码
            int result = centerService.modifyPhone(request);
            if(result==0){
                return RespEnum.SYS_ERROR.result("失败！");
            }
            return RespEnum.OK.result("修改成功！");
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 根据学生编号查询到学生所有购买的课程数量
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/payCourse")
    @ApiOperation(value = "根据学生编号查询到学生所有购买的课程数量", notes = "根据学生编号查询到学生所有购买的课程数量")
    public AbstractBaseResult findPayCourseNumber(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.queryCourseNumber(request.getStudenterId()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器异常，请检查日志文件，稍后重试");
        }
    }

    /**
     * 根据学生编号查询到学生所有收藏的课程
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/course")
    @ApiOperation(value = "根据学生编号查询到学生所有收藏的课程", notes = "根据学生编号查询到学生所有收藏的课程")
    public AbstractBaseResult findAllCourse(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.getCollectCourse(request.getStudenterId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器异常，请检查日志文件，稍后重试");
        }
    }

    /**
     * 获取导航栏上所有的科目信息
     * @return
     */
    @PostMapping("/subjects")
    @ApiOperation(value = "获取导航栏上所有的科目信息", notes = "获取导航栏上所有的科目信息")
    public AbstractBaseResult findAllSubjects() {
        return RespEnum.OK.result(centerService.getAllSubject());
    }

    /**
     * 根据学生编号和科目编号查询所有的知识点
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/knowledge")
    @ApiOperation(value = "根据学生编号和科目编号查询所有的知识点", notes = "根据学生编号和科目编号查询所有的知识点")
    public AbstractBaseResult findKnowledgeBySubject(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.findCollectKnow(request.getStudenterId(), request.getSubjectId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试");
        }
    }

    /**
     * 根据学生编号和科目查询学生的学习记录
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/idea")
    @ApiOperation(value = "根据学生编号和科目查询学生的学习记录", notes = "根据学生编号和科目查询学生的学习记录")
    public AbstractBaseResult findIdeaRecord(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.findIdeaRecord(request.getStudenterId(), request.getSubjectId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试");
        }
    }
    /**
     * 根据课程id删除对应的收藏课程
     * @param request 前端请求参数 内为id集合
     * @return
     */
    @PostMapping("/deleteCourse")
    @ApiOperation(value = "根据课程id删除对应的收藏课程", notes = "根据课程id删除对应的收藏课程")
    public AbstractBaseResult deleteCourse(@RequestBody RequestDTO request) {
        try {
            // 获取到所有的id
            List<Integer> list = IdsUtils.getList(request.getIds());
            int result = centerService.deleteCourse(list,request.getStudenterId());
            if (result == 0) {
                return RespEnum.SYS_ERROR.result("删除失败,请稍后再试");
            } else {
                return RespEnum.OK.result("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("删除失败,请稍后再试");
        }
    }

    /**
     * 根据知识点id删除对应的收藏知识点
     * @param request 前端请求参数 内为id集合
     * @return
     */
    @PostMapping("/deleteKnowledge")
    @ApiOperation(value = "根据知识点id删除对应的收藏知识点", notes = "根据知识点id删除对应的收藏知识点")
    public AbstractBaseResult deleteKnowledge(@RequestBody RequestDTO request) {
        try {
            // 获取到所有的id
            List<Integer> list = IdsUtils.getList(request.getIds());
            int result = centerService.deleteKnowledge(list);
            if (result == 0) {
                return RespEnum.SYS_ERROR.result("删除失败,请稍后再试");
            } else {
                return RespEnum.OK.result("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("删除失败,请稍后再试");
        }
    }

    /**
     * 根据学习记录id删除对应的学习记录
     * @param request 前端请求参数 内为id集合
     * @return
     */
    @PostMapping("/deleteIdea")
    @ApiOperation(value = "根据学习记录id删除对应的学习记录", notes = "根据学习记录id删除对应的学习记录")
    public AbstractBaseResult deleteIdea(@RequestBody RequestDTO request) {
        try {
            // 获取到所有的id
            List<Integer> list = IdsUtils.getList(request.getIds());
            int result = centerService.deleteIdea(list);
            if (result == 0) {
                return RespEnum.SYS_ERROR.result("删除失败,请稍后再试");
            } else {
                return RespEnum.OK.result("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("删除失败,请稍后再试");
        }
    }

    /**
     * 根据学生编号查询到学生所有浏览过的课程列表
     * @param request 前端请求实体类
     * @return
     */
    @PostMapping("/courseHistory")
    @ApiOperation(value = "根据学生编号查询到学生所有浏览过的课程", notes = "根据学生编号查询到学生所有浏览过的课程")
    public AbstractBaseResult courseHistory(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.findHistoryCourse(request.getStudenterId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("服务器异常，请检查日志文件，稍后重试");
        }
    }

    /**
     * 根据课程id删除历史记录对应的课程
     * @param request 请求参数,内为id集合
     * @return
     */
    @PostMapping("/deleteHistory")
    @ApiOperation(value = "根据课程id删除历史记录对应的课程", notes = "根据课程id删除历史记录对应的课程")
    public AbstractBaseResult deleteHistory(@RequestBody RequestDTO request) {
        try {
            // 获取到所有的课程id
            List<Integer> courseIds = IdsUtils.getList(request.getIds());
            int result = centerService.deleteHistoryCourse(courseIds,request.getStudenterId());
            if (result == 0) {
                return RespEnum.SYS_ERROR.result("删除失败,请稍后再试");
            } else {
                return RespEnum.OK.result("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("删除失败,请稍后再试");
        }
    }

    /**
     * 根据学生编号查询学生接收的所有信息
     * @param request 前端请求集合实体类
     * @return
     */
    @PostMapping("/queryMessage")
    @ApiOperation(value = "根据学生id查询学生所有接收到的消息列表", notes = "根据学生id查询学生所有接收到的消息列表")
    public AbstractBaseResult queryMessage(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.queryMessage(request.getStudenterId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }
    }

    /**
     * 根据学生编号查询学生所有未完成的课程
     * @param request 前端请求集合实体类
     * @return
     */
    @PostMapping("/finishCourse")
    @ApiOperation(value = "查询学生已经完成了的课程", notes = "查询学生已经完成了的课程")
    public AbstractBaseResult finishCourse(@RequestBody RequestDTO request) {
        try {
            return RespEnum.OK.result(centerService.queryFinish(request.getStudenterId(), request.getPageNumber(), request.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }

    }

    /**
     * 意见反馈
     * @return
     */
    @PostMapping("/feedback")
    @ApiOperation(value = "意见反馈", notes = "意见反馈")
    public AbstractBaseResult feedback(@RequestBody A_Feedback feedback) {
        try {
            return RespEnum.OK.result(centerService.submitOpinion(feedback));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("提交失败，系统异常，请稍后重试！");
        }

    }

    /**
     * 我的课程
     * @return
     */
    @PostMapping("/myCourse")
    @ApiOperation(value = "查询我的课程", notes = "我的课程")
    public AbstractBaseResult myCourse(@RequestBody RequestDTO requestdto) {
        try {
            return RespEnum.OK.result(centerService.myCourse(requestdto));
        } catch (Exception e) {
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }

    }
    /**
     * 检查版本更新
     */
    @PostMapping("/checkUpdate")
    @ApiOperation("检查版本是否有更新可以用")
    public AbstractBaseResult checkUpdate(@RequestBody RequestDTO request){
        try{
            // 查询当前应用是否有更新
            return RespEnum.OK.result(centerService.checkUpdate(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result( "系统繁忙!");
        }
    }

    /**
     * 获取二维码
     * @param request
     * @return
     */
    @RequestMapping("/getQRCode")
    @ApiOperation("获取二维码")
    public AbstractBaseResult getQRCode(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(centerService.getQRCode(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }
    }

    /**
     * 获取最新的app下载地址
     * @param request
     * @return
     */
    @RequestMapping("/getDownUrl")
    @ApiOperation("获取最新的app下载地址")
    public AbstractBaseResult getDownUrl(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(centerService.getDownUrl(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }
    }

    /**
     * 查询系统功能
     * @return
     */
    @RequestMapping("/getFunctionSetting")
    @ApiOperation("查询系统功能")
    public AbstractBaseResult getFunctionSetting(){
        try{
            return RespEnum.OK.result(centerService.getFunctionSetting());
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("查询失败，系统异常，请稍后重试！");
        }
    }
}
