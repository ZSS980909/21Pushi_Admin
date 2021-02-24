package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.ParentService;
import com.ershiyi.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 家长端前端控制器
 * @author: zss98
 * @date: 2020-12-01 16:30
 * @version: 1.0
 */
@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService service;

    /**
     * 根据手机号搜索学生详细信息
     * @param request
     * @return
     */
    @RequestMapping("/searchStudent")
    public AbstractBaseResult searchStudent(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.searchStudent(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长关联学生编号
     * @param request
     * @return
     */
    @RequestMapping("/relationStudent")
    public AbstractBaseResult relationStudent(@RequestBody RequestDTO request){
        try{
            if(request.getCaptcha()==null||request.getValidataCode()==""){
                // 没有验证码
                return RespEnum.CHECK_FAILED.result("请输入学生验证码来完成绑定！");
            }
            String captcha = null;
            if(request.getType()==1){
                // 短信验证
                captcha = RedisUtils.get(request.getLoginId());
            }else{
                // 推送验证
                captcha = RedisUtils.get(request.getStudenterId());
            }
            if(captcha==null||captcha.isEmpty()){
                return RespEnum.CHECK_FAILED.result("请重新发送验证码！");
            }
            if(!captcha.equals(request.getCaptcha())){
                return RespEnum.CHECK_FAILED.result("验证码错误！");
            }
            // 验证通过，清空验证码
            if(request.getType()==1){
                RedisUtils.del(request.getLoginId());
            }else{
                RedisUtils.del(request.getStudenterId());
            }
            return  RespEnum.OK.result(service.relationStudent(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     *  对学生推送绑定验证码
      * @param requestDTO
     * @return
     */
    @RequestMapping("/sendPushCaptcha")
    public AbstractBaseResult sendPushCaptcha(@RequestBody RequestDTO requestDTO){
        try{
            return RespEnum.OK.result("");
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 学生荣耀得分
     * @param request
     * @return
     */
    @RequestMapping("/gloryScore")
    public AbstractBaseResult gloryScore(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.gloryScore(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 今日学习课程
     * @param request
     * @return
     */
    @RequestMapping("/toDayStudy")
    public AbstractBaseResult toDayStudy (@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.toDayStudy(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长关联的学生信息
     * @param requestDTO
     * @return
     */
    @RequestMapping("/associateStudents")
    public AbstractBaseResult associateStudents (@RequestBody RequestDTO requestDTO){
        try{
            return RespEnum.OK.result(service.associateStudents(requestDTO));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长个人中心详细信息
     * @param request
     * @return
     */
    @RequestMapping("/parentInfo")
    public AbstractBaseResult parentInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.parentInfo(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 获取当前课程的错题列表
     * @param request
     * @return
     */

    @RequestMapping("/wrongQuestion")
    public AbstractBaseResult wrongQuestion(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.wrongQuestion(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长先学后教
     */
    @RequestMapping("/ParentLearn")
    public AbstractBaseResult ParentLearn(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.parentLearn(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 当前课程学习情况
     * @param request
     * @return
     */
    @RequestMapping("/CourseInfo")
    public AbstractBaseResult CourseInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.CourseInfo(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * allCourse
     * @param request
     * @return
     */
    @RequestMapping("/allCourse")
    public AbstractBaseResult allCourse(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.allCourse(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长关注当前课程
     * @param request
     * @return
     */
    @RequestMapping("/collectCourse")
    public AbstractBaseResult collectCourse(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.collectCourse(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长取消关注当前课程
     * @param request
     * @return
     */
    @RequestMapping("/cancelCollect")
    public AbstractBaseResult cancelCollect(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.cancelCollect(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }



    /**
     * 家长查看当前课程详情
     * @param request
     * @return
     */
    @RequestMapping("/chapterInfo")
    public AbstractBaseResult chapterInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.chapterInfo(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 模糊搜索课程
     * @param request
     * @return
     */
    @RequestMapping("/searchCourse")
    public AbstractBaseResult searchCourse(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.searchCourse(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 当前课程历史学习情况
     * @param request
     * @return
     */
    @RequestMapping("/historyStudy")
    public AbstractBaseResult historyStudy(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.historyStudy(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 获取家长端app各平台下载链接
     * @param request
     * @return
     */
    @RequestMapping("/getDownUrl")
    public AbstractBaseResult getDownUrl(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.getDownUrl(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }

    /**
     * 家长端轮播图
     * @param request
     * @return
     */
    @RequestMapping("/banner")
    public AbstractBaseResult banner(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.banner(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }


    @RequestMapping("/getCourseInfo")
    public AbstractBaseResult getCourseInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.getCourseInfo(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙,请稍后再试！");
        }
    }
}
