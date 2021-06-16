package com.ershiyi.service.impl;

import com.alibaba.fastjson.JSON;
import com.ershiyi.Utils.DateUtils;
import com.ershiyi.Utils.DecimalUtils;
import com.ershiyi.Utils.StrUtils;
import com.ershiyi.domain.entity.*;
import com.ershiyi.mapper.StudyDataMapper;
import com.ershiyi.service.StudyDataService;
import com.ershiyi.utils.RedisUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 学习数据 数据操作层
 * @author zss98
 */

@Service
public class StudyDataServiceImpl implements StudyDataService {

    public static Log log = LogFactory.getLog(StudyDataServiceImpl.class);

    @Autowired
    private StudyDataMapper studyDataMapper;

    /**
     *
     * @param studenterId 学生编号
     * @return 返回学生的科目学习详情
      */
    @Override
    public HashMap<String, List> getSubjectData(String studenterId) {
        HashMap<String, List> map = new HashMap<>();
        log.info("开始查询学生学习情况");
        List toDay = JSON.parseObject(studyDataMapper.find_ToDay(studenterId),List.class);
        List week = JSON.parseObject(studyDataMapper.find_Week(studenterId),List.class);
        List month = JSON.parseObject(studyDataMapper.find_Month(studenterId),List.class);
        log.info("将学生学习数据封装成集合");
        map.put("日",toDay);
        map.put("周",week);
        map.put("月",month);
        return map;
    }
    /**
     * 查询学生学习时间情况，生成折线图
     * @param studenterId
     * @return 学生学习数据
     */
    @Override
    public StudentStudyTime getStudentStudyTime(String studenterId) {
        return studyDataMapper.getStudayTime(studenterId);
    }
    /**
     * 查询学生学习情况排名
     * @param studenterId
     * @return
     */
    @Override
    public StudyRank getStudentRank(String studenterId) {
        return studyDataMapper.get_StudyRanking(studenterId);
    }

    /**
     * @Description: 查询学生当天的答题情况
     * @Param: studenterId 学生编号
     * @return: 学生各科目答题情况集合
     * @Date: 2020/7/28
     *
     */
    @Override
    public List<QuestionResult> generateToDayQuestionChart(String studenterId) {
        // 查询所有的学科信息
        List<subjectInfo> subjects = studyDataMapper.findAllSubject();
        List<QuestionsAccuracy> questionsToDay = studyDataMapper.findToDayQuestion(studenterId);
        List<QuestionResult> results = new ArrayList<>();
        subjects.forEach(subject ->
                results.add(questionsLoop(subject.getSubjectId(),subject.getSubjectName(),questionsToDay))
        );
        return results;
    }

    /**
     * @Description: 查询学生7天内的答题情况
     * @Param: studenterId 学生编号
     * @return: 学生各科目答题情况集合
     * @Date: 2020/7/28
     *
     */
    @Override
    public List<QuestionResult> generateWeekQuestionChart(String studenterId) {
        // 查询所有的学科信息
        List<subjectInfo> subjects = studyDataMapper.findAllSubject();
        List<QuestionsAccuracy> questionsWeek = studyDataMapper.findWeekQuestion(studenterId);
        List<QuestionResult> results = new ArrayList<>();
        subjects.forEach(subject ->
                results.add(questionsLoop(subject.getSubjectId(),subject.getSubjectName(),questionsWeek))
                );
        return results;
    }

    /**
     * @Description: 查询学生30天内的答题情况
     * @Param: studenterId 学生编号
     * @return: 学生各科目答题情况集合
     * @Date: 2020/7/28
     *
     */
    @Override
    public List<QuestionResult> generateMonthQuestionChart(String studenterId) {
        // 查询所有的学科信息
        List<subjectInfo> subjects = studyDataMapper.findAllSubject();
        List<QuestionsAccuracy> questionsMonth = studyDataMapper.findMonthQuestion(studenterId);
        List<QuestionResult> results = new ArrayList<>();
        subjects.forEach(subject ->
                results.add(questionsLoop(subject.getSubjectId(),subject.getSubjectName(),questionsMonth))
        );
        return results;
    }

    /**
     * 学习雷达图
     * @param studentId 学生编号
     * @return
     */
    @Override
    public LearnStatus getLearnStatus(String studentId) {
        // 学习情况
        LearnStatus results = new LearnStatus();
        // 计算学习力
        int studyScore = 0;
        // 学习时长比率
        int learnLength = 0;
        // 关注度 默认得分加权20
        int attention = 20;
        // 知识点学习进度
        int progress = 0;
        // 效率
        int efficiency = 0;
        // 得到当前学生考试正确率
        double accuracy = studyDataMapper.Accuracy(studentId);
        double avgStudyTime = studyDataMapper.getAvgStudyTime(studentId);
        studyScore = DecimalUtils.mul(accuracy,70);
        studyScore = studyScore+DecimalUtils.mul(studyDataMapper.studyLength(studentId),30);
        learnLength = DecimalUtils.mul(studyDataMapper.getLearnLength(studentId),100);
        progress = DecimalUtils.mul(studyDataMapper.getProgress(studentId),100);
        efficiency = DecimalUtils.mul(accuracy,60);
        efficiency = efficiency+DecimalUtils.mul(avgStudyTime,40);
        results.setLearnLength(learnLength);
        attention = attention+DecimalUtils.mul(avgStudyTime,80);
        results.setAttention(attention);
        results.setProgress(progress);
        results.setEfficiency(efficiency);
        results.setStudyScore(studyScore);
        // 将结果存入redis
        RedisUtils.set("learn"+studentId,JSON.toJSONString(results),3600*24);
        return results;
    }

    @Override
    public KnowledgeStatus knowledgeStatus(String guid, int courseId, String courseName) {
        // 定义返回类
        KnowledgeStatus results = new KnowledgeStatus();
        // 节点信息
        List<Node> nodes = new ArrayList<>();
        // 节点关系
        List<Link> links = new ArrayList<>();
        // 设置最终根节点信息
        nodes.add(new Node(0,courseName,1,35));
        int count = 1;
        String studentId = studyDataMapper.getStudentId(guid);
        // 获取学生所有完成的章节
        List<Integer> finishChapterIds = studyDataMapper.getFinishChapter(courseId,studentId);
        // 获取所有完成的知识点
        List<Integer> finishKnow = studyDataMapper.getFinishKnow(courseId,studentId);
        List<ChapterInfo> allChapter = studyDataMapper.getAllChapter(courseId);
        for (ChapterInfo chapter: allChapter) {
            // 判断当前节点是否已经学习
            nodes.add(new Node(count,chapter.getChapterName(),finishChapterIds.contains(chapter.getChapterId())? 1:0,25));
            // 将当前节点加入关系图
            links.add(new Link(0,count));
            // 获取当前章节下的知识点
            List<KnowledgeInfo> allKnow = studyDataMapper.getAllKnow(chapter.getChapterId());
            int parentId = count;
            for (KnowledgeInfo know : allKnow) {
                count++;
                links.add(new Link(parentId,count));
                int status = finishKnow.contains(know.getKnowId()) ? 1:0;
                nodes.add(new Node(count,know.getKnowName(),status,20));
                // 获取知识点下的知识点内容
                List<String> contents = studyDataMapper.getKnowContent(StrUtils.StringToList(know.getKnowContentIds()));
                int knowId = count;
                for (String content : contents) {
                    count++;
                    nodes.add(new Node(count,content,status,10));
                    links.add(new Link(knowId,count));
                }
            }
            count++;
        }
        results.setNodes(nodes);
        results.setLinks(links);
        // 将redis存入redis
        RedisUtils.set("knowledge"+courseId+guid,JSON.toJSONString(results), DateUtils.daySurplusTime());
        return results;
    }

    /**
     * 获取购买的课程列表
     * @param guid 用户id
     * @return
     */
    @Override
    public List<CourseInfo> buyCourse(String guid) {
        return studyDataMapper.buyCourse(guid);
    }

    /**
     * 将科目答题情况循环查询抽取出来
     */
    private static QuestionResult questionsLoop(int subjectId,String subjectName,List<QuestionsAccuracy> questions){
        int correct = 0;
        int wrong = 0;
        for (QuestionsAccuracy question : questions) {
            // 获取语文的答题数量
            if(question.getSubjectId() == subjectId){
                if(question.getCorrect()==0){
                    wrong += 1;
                }else{
                    correct += 1;
                }
            }
        }
        QuestionResult results = new QuestionResult(subjectName,correct,wrong);
        return  results;
    }

    /**
     * 根据科目查询出对应的学科ID
     */
    private static int getSujectId(List<subjectInfo> subjects, String subjectName){
        int subjectId = 0;
        for (subjectInfo subject : subjects) {
            if(subject.getSubjectName().equals(subjectName)){
                subjectId = subject.getSubjectId();
            }
        }
        return subjectId;
    }

}
