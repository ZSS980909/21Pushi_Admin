package com.ershiyi.service;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;

import java.util.HashMap;
import java.util.List;

/**
 * 查询学生学习数据
 * @author zss98909
 */
public interface StudyDataService{
    /**
     *查询学生的科目占比
     * @param studenterId 学生编号
     * @return 学生的学习数据
     */
    public HashMap<String,List> getSubjectData(String studenterId);

    /**
     * 查询学生学习时间情况，生成折线图
     * @param studenterId
     * @return 学生学习数据
     */
    public StudentStudyTime getStudentStudyTime(String studenterId);

    /**
     * 查询学生学习情况排名
     * @param studenterId
     * @return
     */
    public StudyRank getStudentRank(String studenterId);
    /**
     * 查询学生答题情况柱状图
     * @param studenterId 学生编号
     * @return  学生当天科目答题情况折线图
     */
    public List<QuestionResult> generateToDayQuestionChart(String studenterId);

    /**
     * 查询学生答题情况柱状图
     * @param studenterId 学生编号
     * @return  学生七天内科目答题情况折线图
     */
    public List<QuestionResult> generateWeekQuestionChart(String studenterId);

    /**
     * 查询学生答题情况柱状图
     * @param studenterId 学生编号
     * @return  学生30天内科目答题情况折线图
     */
    public List<QuestionResult> generateMonthQuestionChart(String studenterId);

    /**
     * 学习雷达图
     * @param studentId 学生编号
     * @return
     */
    public LearnStatus getLearnStatus(String studentId);

    /**
     * 学习情况
     * @param guid
     * @param courseId
     * @param courseName
     * @return
     */
    KnowledgeStatus knowledgeStatus(String guid, int courseId, String courseName);

    /**
     * 获取购买的课程列表
     * @param guid 用户id
     * @return
     */
    List<CourseInfo> buyCourse(String guid);
}
