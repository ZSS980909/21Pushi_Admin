package com.ershiyi.controller;

import com.alibaba.fastjson.JSON;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.dto.StudyRecordDTO;
import com.ershiyi.service.LearnService;
import com.ershiyi.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 学习课堂模块前后端交互
 * @author: zss98
 * @date: 2020-08-04 15:53
 * @version: 1.0
 */
@RestController
@CrossOrigin(value = "*", maxAge= 1800,allowedHeaders="*")
@RequestMapping("/learn")
@Api(value = "学习课堂", tags = {"学习课堂"})
public class LearnController {

    @Resource
    private LearnService service;

    /**
     * 根据学生编号查询学生的所有课程列表
     * @param request 前端请求实体类 需包含学生编号，页码，每页展示的数量
     * @return
     */
    @PostMapping("/courseList")
    @ApiOperation(value = "根据学生编号查询学生可学的所有课程",notes = "根据学生编号查询学生可学的所有课程")
    public AbstractBaseResult courseList(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.courseList(request.getStudenterId(),request.getPageNumber(),request.getPageSize(),request.getGrade()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 根据课程id查询出评论列表
     * @param request courseId 课程id studenterId 学生编号
     * @return
     */
    @PostMapping("/commentInfo")
    @ApiOperation(value = "根据课程id查询出评论列表",notes = "根据课程id查询出评论列表")
    public AbstractBaseResult commentInfo(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.commentInfo(request.getCourseId(),request.getGuid(),request.getPageNumber(),request.getPageSize()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 记录知识点学习记录
     * @param record
     * @return
     */
    @PostMapping("/addStudyRecord")
    public AbstractBaseResult addStudyRecord(@RequestBody StudyRecordDTO record){
        try{
            return RespEnum.OK.result(service.addStudyRecord(record));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 自动学习下一个知识点
     * @param request
     * @return
     */
    @PostMapping("/nextKnow")
    public AbstractBaseResult nextKnow(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.nextKnow(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 给当前评论点赞
     * @param request discussId评论id 和点赞的 studenterId学生编号
     * @return 点赞结果 200为成功 其他为失败
     */
    @PostMapping("/giveLike")
    @ApiOperation(value = "对选中的评论进行点赞")
    public AbstractBaseResult giveLike(@RequestBody RequestDTO request){
        try{
            int result = service.giveLike(request.getCommentId(),request.getGuid());
            // 如果返回的结果为0代表添加失败了
            if(result==0){
                return RespEnum.ERROR.result("系统繁忙，请稍后再试");
            }else{
                return RespEnum.OK.result(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 取消当前点赞
     * @param request discussId评论id 和点赞的 studenterId学生编号
     * @return 点赞结果 200为成功 其他为失败
     */
    @PostMapping("/cancelLike")
    @ApiOperation(value = "对选中的评论进行点赞")
    public AbstractBaseResult cancelLike(@RequestBody RequestDTO request){
        try{
            int result = service.cancelLike(request.getCommentId(),request.getStudenterId());
            // 如果返回的结果为0代表添加失败
            if(result==0){
                return RespEnum.ERROR.result("系统繁忙，请稍后再试");
            }else{
                return RespEnum.OK.result("成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }


    /**
     * 发表评论
     * @param request message 评论内容 guid 用户编号 courseId 课程id
     * @return 插入成功返回当前评论的内容
     */
    @PostMapping("/publishComment")
    @ApiOperation("发表评论")
    public AbstractBaseResult publishComment(@RequestBody RequestDTO request){
        try{
            CommentInfo result = service.publishComment(request.getMessage(), request.getGuid(), request.getCourseId());
            if(result==null){
                // 等于0代表插入失败
                return RespEnum.ERROR.result("评论失败，请稍后再试");
            }
            return RespEnum.OK.result(result);
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     *  收藏知识点
     */
    @PostMapping("/collectKnow")
    @ApiOperation(value = "收藏知识点",notes = "用户收藏当前课程下的知识点")
    public AbstractBaseResult collectKnow(@RequestBody RequestDTO request){
        try{
            int result = service.collectKnow(request.getCourseId(),request.getChapterId(), request.getStudenterId(),request.getKnowId(),request.getName(),request.getSubjectId());
            if(result==0){
                return RespEnum.ERROR.result("收藏失败！");
            }
            return RespEnum.OK.result("收藏成功");
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 知识点内容学习完毕根据节点id来查询所关联的题目
     * @param request courseId 课程id   knowId 知识点id name 知识点名称
     * @return
     */
    @PostMapping("/knowQuestion")
    @ApiOperation(value = "题目",notes = "知识点内容学习完毕根据节点id来查询所关联的题目")
    public AbstractBaseResult knowQuestion(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.knowQuestion(request.getKnowId()));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }


    /**
     * 提交学生答题情况
     * @param request
     * @return
     */
    @PostMapping("/submitQuestion")
    @ApiOperation(value = "添加答题情况",notes = "提交用户学习完知识点后的答题情况")
    public AbstractBaseResult submitQuestion(@RequestBody HashMap<String,List<Correct>>  request){
        try{
            int result = service.submitQuestion(request.get("data"));
            if(result==0){
                return RespEnum.ERROR.result("提交失败！");
            }
            return RespEnum.OK.result("成功");
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 获取当前课程下所有的章节信息，以及章节学习状态
     * @param request
     * @return
     */
    @PostMapping("/chapterMenu")
    @ApiOperation("获取当前课程下所有的章节信息，以及章节学习状态")
    public AbstractBaseResult chapterMenu(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.chapterMenu(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 获取下一级的章节信息
     * @param request
     * @return
     */
    @RequestMapping("/knowList")
    public AbstractBaseResult knowList(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.KnowList(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试");
        }
    }

    /**
     * 第一层的章节信息
     * @param request
     * @return
     */
    @RequestMapping("/firstMenu")
    public AbstractBaseResult firstMenu(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.firstMenu(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试！");
        }
    }

    /**
     * 下一层的章节信息
     * @param request
     * @return
     */
    @RequestMapping("/nextMenu")
    public AbstractBaseResult nextMenu(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.nextMenu(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙，请稍后再试！");
        }
    }
    /**
     * 获取共享学习笔记内容列表
     * @param request
     * @return
     */
    @RequestMapping("/noteList")
    public AbstractBaseResult noteList(@RequestBody RequestDTO request){
        try{
            return RespEnum.OK.result(service.noteList(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 发布学习笔记
     * @param request
     * @return
     */
    @RequestMapping("/pushNote")
    public AbstractBaseResult pushNote(@RequestBody NoteInfo request){
        try{
            return RespEnum.OK.result(service.pushNote(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 点赞学习笔记
     * @param request
     * @return
     */
    @RequestMapping("/likeNote")
    public AbstractBaseResult likeNote(@RequestBody NoteInfo request){
        try{
            return RespEnum.OK.result(service.likeNote(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 删除学习笔记点赞
     * @param request
     * @return
     */
    @RequestMapping("/cancelNoteLike")
    public AbstractBaseResult cancelNoteLike(@RequestBody NoteInfo request){
        try{
            return RespEnum.OK.result(service.cancelNoteLike(request));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }
}
