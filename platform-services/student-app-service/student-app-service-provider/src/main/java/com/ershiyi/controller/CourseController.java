package com.ershiyi.controller;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.CourseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 查询课程
 */
@RestController
@RequestMapping("/Course")
@Api(value = "课程相关", tags = {"课程相关"})
public class CourseController {
    @Autowired
    private CourseService courseseservice;
    /**
     * 添加课堂模糊搜索
     */
    @PostMapping("/obscure")
    @ResponseBody
    @ApiOperation(value = "查询课程的模糊查询", notes = "查询课程的模糊查询")
    public AbstractBaseResult Obscure(@RequestBody RequestDTO request) {
        return RespEnum.OK.result(courseseservice.Obscure(request));
    }

    /**
     * 查询出作者的其他课程
     * @param request
     * @return
     */
    @PostMapping("/authorAbout")
    @ApiOperation(value = "查询出作者的其他课程",notes = "查询出作者的其他课程")
    public AbstractBaseResult authorAbout(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(courseseservice.authorAbout(request.getCreatorId(),request.getCourseId(),request.getPageNumber(),request.getPageSize()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    @PostMapping("/courseForSubject")
    @ApiOperation(value = "根据科目id查询分类下的课程信息",notes = "根据科目id查询分类下的课程信息")
    public AbstractBaseResult courseForSubject(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(courseseservice.courseForSubject(request.getStudenterId(),request.getSubjectId(),request.getPageNumber(),request.getPageSize()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    @PostMapping("/buyCourse")
    @ApiOperation(value = "学生购买当前课程",notes = "学生购买当前课程")
    public AbstractBaseResult buyCourse(@RequestBody RequestDTO request){
        try{
            int result = courseseservice.buyCourse(request.getCourseId(),request.getStudenterId());
            int resultInt = 0;
            if(result==203) {
                // 当前课程已经购买
                resultInt= 411;
            }else if(result == 201){
                // 积分不足以购买当前课程
                resultInt=410;
            }else{
                resultInt=200;
            }
            return RespEnum.OK.result(resultInt);
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.SYS_ERROR.result("系统繁忙,请稍后重试！");
        }
    }

    @PostMapping("/studyPlan")
    @ApiOperation(value = "查看当日学习计划",notes = "查看当日学习计划")
    public AbstractBaseResult studyPlan(@RequestBody RequestDTO request){
        if(request.getDate()==null||request.getDate()==""){
            // 没有上传时间默认为查询当天的时间
            request.setDate(new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
        }
        try{
            return RespEnum.OK.result(courseseservice.queryStudyPlan(request.getStudenterId(),request.getDate()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }
    /**
     * 查询榜单
     * RECOMMEND 推荐课堂
     * STUDYAID  助学课堂
     * NEWEST  最新课堂
     * HOT  热门课堂
     */
    @PostMapping("/billboard")
    @ResponseBody
    @ApiOperation(value = "查询榜单", notes = "查询榜单")
    public AbstractBaseResult billboard (@RequestBody Billboardtitle billboardtitle) {
        return RespEnum.OK.result(courseseservice.billboard(billboardtitle));
    }



    /**
     * 根据课程id 查询课程章节
     */
    @PostMapping("/chapterById")
    @ResponseBody
    @ApiOperation(value = "查询课程章节", notes = "查询课程章节")
    public AbstractBaseResult chapterById (@RequestBody Course course) {
        return RespEnum.OK.result(courseseservice.chapterById(course));
    }

    /**
     * 每次点击课程 浏览数加1
     */
    @PostMapping("/courseByBrowse")
    @ResponseBody
    @ApiOperation(value = "增加浏览数", notes = "增加浏览数")
    public AbstractBaseResult courseByBrowse (@RequestBody RequestDTO request) {
        return RespEnum.OK.result(courseseservice.courseByBrowse(request.getCourseId(),request.getStudenterId()));
    }
    /**
     * 精品课堂  -----后续改为视图   --目前暂时功能需求更改 不用
     */
    @PostMapping("/courseBycompetitiv")
    @ResponseBody
    @ApiOperation(value = "增加浏览数 --视图", notes = "增加浏览数--视图")
    public AbstractBaseResult courseBycompetitiv () {
        return RespEnum.OK.result(courseseservice.courseByCompetitiv());
    }
    /**
     *根据知识点生成题目,随机生成各种类型的题目   ---未完成
     */
    @PostMapping("/courseByknowledge")
    @ResponseBody
    @ApiOperation(value = "根据知识点生成题目,随机生成各种类型的题目", notes = "根据知识点生成题目,随机生成各种类型的题目")
    public AbstractBaseResult courseByknowledge(@RequestBody Chapter chapter) {
        return RespEnum.OK.result(courseseservice.courseByknowledge(chapter));
    }
    /**                                      .
     *查询该章节下所有的知识点的标题
     */
    @PostMapping("/courseByknowledgeAll")
    @ResponseBody
    @ApiOperation(value = "查询所有知识点", notes = "查询所有知识点")
    public AbstractBaseResult courseByKnowledgeAll(@RequestBody Chapter chapter) {
        return RespEnum.OK.result(courseseservice.courseByKnowledgeAll(chapter));
    }
    /**
     *根据课程id 查询课程全部信息
     */
    @PostMapping("/courseByid")
    @ResponseBody
    @ApiOperation(value = "课程查询全部信息", notes = "根据课程id 查询课程全部信息")
    public AbstractBaseResult courseByid(@RequestBody Course course) {
        return RespEnum.OK.result(courseseservice.courseById(course));
    }
    /**
     * 根据知识点id查询知识点的内容
     */
    @PostMapping("/courseByknowledgecontent")
    @ResponseBody
    @ApiOperation(value = "根据知识点id 查询知识点内容", notes = "根据知识点id 查询知识点内容")
    public AbstractBaseResult courseByknowledgecontent(@RequestBody Knowledge  knowledge) {
        return RespEnum.OK.result(courseseservice.courseByKnowledgeContent(knowledge));
    }
//    /**
//     *根据知识点,查询学习资料
//     */
//    @PostMapping("/courseByStudy")
//    @ResponseBody
//    @ApiOperation(value = "查询该知识点的资料", notes = "查询该知识点的资料")
//    public AbstractBaseResult courseByStudy(@RequestBody QuestionContent questioncontent) {
//        return RespEnum.OK.result(courseseservice.courseByStudy(questioncontent));
//    }


    /**
     * 课程评价查询
     */
    /*@PostMapping("/courseByAppraise")
    @ResponseBody
    @ApiOperation(value = "根据课程id 查询课程全部信息", notes = "根据课程id 查询课程全部信息")
    public AbstractBaseResult courseByAppraise(@RequestBody Course course) {
        return RespEnum.OK.result(courseseservice.courseByAppraise(course));
    }*/
    /**
     * 评论点赞
     */
    @PostMapping("/appraiseByDiscuss")
    @ResponseBody
    @ApiOperation(value = "评论点赞", notes = "评论点赞")
    public AbstractBaseResult appraiseByDiscuss(@RequestBody Thumbs thumbs) {
        try{
            int result = courseseservice.appraiseByDiscuss(thumbs);
            if(result==0){
                return RespEnum.ERROR.result("插入失败");
            }
            else{
                return RespEnum.OK.result("成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }

    }
    /**
     * 评论点赞状态设置
     */
    @PostMapping("/appraiseBydiscussStatus")
    @ResponseBody
    @ApiOperation(value = "评论点赞", notes = "评论点赞")
    public AbstractBaseResult appraiseBydiscussStatus(@RequestBody Thumbs thumbs) {
        try{
            int result = courseseservice.appraiseByDiscussStatus(thumbs);
            if(result==0){
                return RespEnum.ERROR.result("插入失败");
            }
            else{
                return RespEnum.OK.result("成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    /**
     * 加入收藏课程
     */
    @PostMapping("/courseByCollect")
    @ResponseBody
    @ApiOperation(value = "加入收藏课程", notes = "加入收藏课程")
    public AbstractBaseResult courseByCollect(@RequestBody RequestDTO request) {
        try {
            Integer result = courseseservice.courseByCollect(request.getStudenterId(), request.getCourseId());
            if(result!=0){
                // 收藏成功
                return RespEnum.OK.result("收藏成功");
            }else{
                return RespEnum.SYS_ERROR.result("收藏失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  RespEnum.ERROR.result("失败");
        }
    }

    /**
     * 查询该学生该课程是否加入收藏
     */
    @PostMapping("/courseByCollectIf")
    @ResponseBody
    @ApiOperation(value = "查询是否加入收藏课程", notes = "查询是否加入收藏课程")
    public AbstractBaseResult courseByCollectIf(@RequestBody Collect_Course collect_course) {
        return RespEnum.OK.result(courseseservice.courseByCollectIf(collect_course));
    }

    /**
     *查询课程公告
     */
    @PostMapping("/courseByNotice")
    @ResponseBody
    @ApiOperation(value = "查询课程公告", notes = "查询课程公告")
    public AbstractBaseResult courseByNotice(@RequestBody Notice notice) {
        return RespEnum.OK.result(courseseservice.courseByNotice(notice));
    }

//    /**
//     * 查询未计划课程
//     */
//    @PostMapping("/notplancourse")
//    @ResponseBody
//    @ApiOperation(value = "查询未计划课程", notes = "查询未计划课程")
//    public AbstractBaseResult notplancourse(@RequestBody ) {
//        return RespEnum.OK.result(courseseservice.courseByNotice(notice));
//    }
    /**
     *悬浮菜单 ---学习课堂课堂查询---查询该学生已购买的课程
     *
     */
    @PostMapping("/searchbyBusCourse")
    @ResponseBody
    @ApiOperation(value = "查询该学生已购买的课程", notes = "查询该学生已购买的课程")
    public AbstractBaseResult searchbyBusCourse(@RequestBody Common_Search search) {
        return RespEnum.OK.result(courseseservice.searchByBusCourse(search));
    }

//    /**
//     *悬浮菜单 --查看课表   暂停,先不做
//     */
//    @PostMapping("/selectBySyllabus")
//    @ResponseBody
//    @ApiOperation(value = "查看课表", notes = "查看课表")
//    public AbstractBaseResult selectBySyllabus(@RequestBody Common_Search search) {
//        return RespEnum.OK.result(courseseservice.selectBySyllabus(search));
//    }

    /**
     * 悬浮菜单 --添加课程  ---查询页面课程--根据菜单栏
     */
    @PostMapping("/selectCourseBySubject")
    @ResponseBody
    @ApiOperation(value = "根据菜单栏查询页面课程", notes = "根据菜单栏查询页面课程")
    public AbstractBaseResult selectCourseBySubject(@RequestBody RequestDTO request) {
        return RespEnum.OK.result(courseseservice.selectCourseBySubject(request.getStudenterId(),request.getSubjectId()));
    }
    /**
     *复习课堂 --- 复习课堂 --临阵磨刀知识点数据展示
     */
    @PostMapping("/LZMDknowledge")
    @ResponseBody
    @ApiOperation(value = "临阵磨刀知识点数据展示", notes = "临阵磨刀知识点数据展示")
    public AbstractBaseResult LZMDknowledge(@RequestBody Common_Search search) {
        return RespEnum.OK.result(courseseservice.LZMDKnowledge(search));
    }
    /**
     *复习课堂 --- 复习课堂 --临阵磨刀选择课堂后 进行随机出题
     */
    @PostMapping("/LZMDknowledgeByQuestion")
    @ResponseBody
    @ApiOperation(value = "临阵磨刀选择课堂后 进行随机出题", notes = "临阵磨刀选择课堂后 进行随机出题")
    public AbstractBaseResult LZMDknowledgeByQuestion(@RequestBody LZMDType lzmdtype) {
        return RespEnum.OK.result(courseseservice.LZMDknowledgeByQuestion(lzmdtype));
    }
    /**
     *复习课堂 --- 复习课堂 --临阵磨刀提交学习数据
     * 提交数量不固定 提交题型不固定
     * 需要每道题目的开始时间 截止时间
     */
    @PostMapping("/LZMDquestionBySubmit" )
    @ResponseBody
    @ApiOperation(value = "临阵磨刀选择课堂后 临阵磨刀提交学习数据", notes = "临阵磨刀选择课堂后 临阵磨刀提交学习数据")
    public AbstractBaseResult LZMDquestionBySubmit(@RequestBody Map<String, List<Common_StudyrateBy>> list) {
        return RespEnum.OK.result(courseseservice.LZMDQuestionBySubmit(list));
    }

    /**
     * 学生查看错题库
     * @param request
     * @return
     */
    @PostMapping("/wrongQuestion")
    @ApiOperation("学生查看错题库")
    public AbstractBaseResult wrongQuestion(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(courseseservice.wrongQuestion(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    /**
     * 知识点主页首页推荐
     * @param request
     * @return
     */
    @PostMapping("/courseRecommend")
    @ApiOperation("知识点主页首页推荐")
    public AbstractBaseResult courseRecommend(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(courseseservice.courseRecommend(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }

    /**
     * 查询页面所有功能
     */
    @PostMapping("/Querytitle")
    @ApiOperation("查询页面所有功能")
    public AbstractBaseResult Querytitle(){
        try{
            return RespEnum.OK.result(courseseservice.Querytitle());
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙");
        }
    }
}
