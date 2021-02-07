package com.ershiyi.controller;

import com.alibaba.fastjson.JSON;
import com.ershiyi.Utils.DateUtils;
import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.dist.RespEnum;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.service.StudyDataService;
import com.ershiyi.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin (value = "*", maxAge= 1800,allowedHeaders="*")
@Api(value = "查询学习数据", tags = {"查询学生最近的学习情况"})
@RequestMapping("/studyData")
public class StudyDataController {
    @Autowired
    private StudyDataService studyDataService;

    /**
     * 根据学生编号查询学生的排名
     * @param request 请求集合实体类
     * @return 学生的排名情况
     */
    @RequestMapping("/studyRank")
    @ApiOperation(value = "查询当日学习数据排名",notes = "查询当日学习数据排名")
    public AbstractBaseResult getStudyRanking(@RequestBody RequestDTO request){
        StudyRank studentRank = studyDataService.getStudentRank(request.getStudenterId());
        if (studentRank == null) {
            studentRank = new StudyRank();
            studentRank.setStudyRank("暂无数据");
            studentRank.setQuestionsRank("暂无数据");
        }
        return RespEnum.OK.result(studentRank);
    }

    /**
     * 查询学生的最近学习时间以及最近平均学习时间
     * @param request 请求集合实体类
     * @return
     */
    @RequestMapping("/studyTime")
    @ApiOperation(value = "查询学生当日学习时间以及平均学习时间",notes = "查询学生当日学习时间以及平均学习时间")
    public AbstractBaseResult getStudyTime(@RequestBody RequestDTO request){
        LinkedHashMap<String,List> map = new LinkedHashMap<>();
        List<String> date = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        List<Integer> averageTime = new ArrayList<>();
        StudentStudyTime student = studyDataService.getStudentStudyTime(request.getStudenterId());
        for (int i = 0; i < 7; i++) {
            date.add(DateUtils.getDeleteDay("M-dd",6-i));
        }
        time.add(student.getSixDaysAgo_StudyTime());
        time.add(student.getFiveDaysAgo_StudyTime());
        time.add(student.getFourDaysAgo_StudyTime());
        time.add(student.getThreeDaysAgo_StudyTime());
        time.add(student.getTwoDaysAgo_StudyTime());
        time.add(student.getYesterday_StudyTime());
        time.add(student.getToday_StudyTime());
        averageTime.add(student.getAverage_sixDaysAgo());
        averageTime.add(student.getAverage_fiveDaysAgo());
        averageTime.add(student.getAverage_fourDaysAgo());
        averageTime.add(student.getAverage_threeDaysAgo());
        averageTime.add(student.getAverage_twoDaysAgo());
        averageTime.add(student.getAverage_YesterDay());
        averageTime.add(student.getAverage_toDay());
        map.put("date",date);
        map.put("time",time);
        map.put("averageTime",averageTime);
        return RespEnum.OK.result(map);
    }

    /**
     * 查询学生的科目占比情况
     * @param request 请求集合实体类
     * @return
     */
    @RequestMapping("/subjectData")
    @ApiOperation(value = "查询学生的各科学习时间占比",notes = "查询学生的各科学习时间占比")
    public AbstractBaseResult getSubjectData(@RequestBody RequestDTO request){
        return RespEnum.OK.result(studyDataService.getSubjectData(request.getStudenterId()));
    }

    /**
     * 查询学生最近的答题情况 正确数和错误的数量
     * @param request 请求集合实体类
     * @return
     */
    @RequestMapping("/questionsData")
    @ApiOperation(value = "查询学生当天、七天内、三十天内的各科目答题情况",notes = "查询学生当天、七天内、三十天内的各科目答题情况")
    public AbstractBaseResult QuestionBarChart(@RequestBody RequestDTO request){
        HashMap<String,List> map = new HashMap<>();
        // 当天的各科目答题情况
        List<QuestionResult> dayResults = studyDataService.generateToDayQuestionChart(request.getStudenterId());
        // 当天的各科目答题情况
        List<QuestionResult> weekResults = studyDataService.generateWeekQuestionChart(request.getStudenterId());
        // 当天的各科目答题情况
        List<QuestionResult> monthResults = studyDataService.generateMonthQuestionChart(request.getStudenterId());
        // 讲答题情况封装成map集合
        map.put("日",dayResults);
        map.put("周",weekResults);
        map.put("月",monthResults);
        return RespEnum.OK.result(map);
    }

    /**
     * 学习雷达图
     * @param request
     * @return
     */
    @RequestMapping("/learnStatus")
    public AbstractBaseResult learnStatus(@RequestBody RequestDTO request){
        // 先查询redis内是否有存入
        String json = RedisUtils.get("learn"+request.getStudenterId());
        if(json==null||json.isEmpty()){
            // 缓存内没有
            return RespEnum.OK.result(studyDataService.getLearnStatus(request.getStudenterId()));
        }
        // 有缓存直接输出缓存数据
        return RespEnum.OK.result(JSON.parseObject(json, LearnStatus.class));
    }

    /**
     * 知识点状态
     * @param guid 学生编号
     * @param courseId 课程id
     * @param courseName 课程名称
     * @return
     */
    @RequestMapping("/knowledgeStatus")
    public AbstractBaseResult knowledgeStatus(String guid,int courseId,String courseName){
        try{
            KnowledgeStatus data = new KnowledgeStatus();
            // 判断缓存内是否有数据，有就直接取用
            if(RedisUtils.hasKey("knowledge"+courseId+guid)){
                data = JSON.parseObject((String)RedisUtils.get("knowledge"+courseId+guid),KnowledgeStatus.class);
            }else{
                data = studyDataService.knowledgeStatus(guid,courseId,courseName);
            }
            return RespEnum.OK.result(data);
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }

    /**
     * 获取购买的课程列表
     * @param guid 用户id
     * @return
     */
    @RequestMapping("/buyCourse")
    public AbstractBaseResult buyCourse(String guid){
        try{
            return RespEnum.OK.result(studyDataService.buyCourse(guid));
        }catch (Exception e){
            e.printStackTrace();
            return RespEnum.ERROR.result("系统繁忙！");
        }
    }
}
