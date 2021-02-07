package com.ershiyi.service;

import com.ershiyi.domain.entity.ClassTime;
import com.ershiyi.domain.entity.CurriculumPojo;
import com.ershiyi.domain.entity.TimeTable;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 课表查询服务层接口
 * @author: zss98
 * @date: 2020-07-31 14:51
 * @version: 1.0
 */
public interface TimeTableService {
    /**
     * 查询学生课表
     * @param studenterId 学生编号
     * @return
     */
    public List<List<TimeTable>> queryCurriculumTable(String studenterId);

    /**
     * 判断当前提交的信息是否冲突
     * @param planTime 计划时间
     * @param planWeek 计划星期
     * @param studenterId 学生编号
     * @return
     */
    public Integer checkRepeat(String planTime,String planWeek,String studenterId,Integer courseId);

    /**
     * 添加课表计划安排
     * @param curriculum
     * @return
     */
    public int  addCurriculum(CurriculumPojo curriculum);


    /**
     * 添加该课程计划安排
     * @param curriculum
     * @return
     */
    public Integer setCoursePlan(CurriculumPojo curriculum);

    /**
     * 查询学生该课程的安排
     * @param studenterId 学生编号
     * @param courseId 课程id
     * @return
     */
    public HashMap<String,ClassTime> findCurriculumByCourse(String studenterId, Integer courseId);

    /**
     * 重置课程计划安排
     * @param id 计划表id
     * @return 修改结果，0为失败，其余为成功
     */
    public int resetCurriculum(int id);

    /**
     * 查询当前课程下有多少节
     * @param courseId 课程id
     * @return
     */
    int queryKnowNUmber(Integer courseId);
}
