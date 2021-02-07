package com.ershiyi.service.impl;

import com.ershiyi.Utils.DateUtils;
import com.ershiyi.Utils.IdsUtils;
import com.ershiyi.domain.entity.ClassTime;
import com.ershiyi.domain.entity.CourseInfo;
import com.ershiyi.domain.entity.CurriculumPojo;
import com.ershiyi.domain.entity.TimeTable;
import com.ershiyi.mapper.TimeTableMapper;
import com.ershiyi.service.TimeTableService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.Sun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Description: 课表查询实现类
 * @author: zss98
 * @date: 2020-07-31 14:51
 * @version: 1.0
 */
@Service
public class TimeTableServiceImpl implements TimeTableService {
    @Autowired
    private TimeTableMapper mapper;

    /**
     * 根据学生编号查询出学生的课表
     * @param studenterId 学生编号
     * @return
     */
    @Override
    public List<List<TimeTable>> queryCurriculumTable(String studenterId) {
        // 查询出学生所有课表
        List<TimeTable> timeTables = mapper.queryCurriculumTable(studenterId);
        List<List<TimeTable>> result = sortList(timeTables);
        return result;
    }


    /**
     * 添加课程信息
     * @param curriculum 课程计划安排实体类
     * @return
     */
    @Override
    public int addCurriculum(CurriculumPojo curriculum) {
        int result = mapper.addCurriculum(curriculum);
        // 课程计划添加如果不为0则代表安排成功
        if(result!=0){
            // 安排成功修改该学生当前课程计划表状态
            mapper.modifyStatus(curriculum.getStudenterId(), curriculum.getCourseId());
            // 计划插入成功返回课表id
            //result = mapper.queryCurriculumId(curriculum);
        }
        return result;
    }

    /**
     * 添加该课程计划安排
     * @param curriculum
     * @return
     */
    @Override
    public Integer setCoursePlan(CurriculumPojo curriculum) {
        // 判断当前课程是否已经存在
        List<Integer> list = mapper.exitsCoursePlan(curriculum);
        int result = 0;
        if(list.isEmpty()){
            // 当前课程尚未计划，新增
            result = mapper.insertCoursePlan(curriculum);
        }else{
            // 已计划就修改
            result = mapper.modifyCoursePlan(curriculum);
        }
        return result;
    }

    /**
     * 判断当前提交的信息是否冲突
     * @param planTime 计划时间
     * @param planWeek 计划星期
     * @param studenterId 学生编号
     * @param courseId 课程id
     * @return
     */
    @Override
    public Integer checkRepeat(String planTime, String planWeek, String studenterId, Integer courseId){
        String startTime = DateUtils.getAddHour(planTime,"HH:mm",-1);
        String endTime = DateUtils.getAddHour(planTime,"HH:mm",1);
        List<ClassTime> classTimes = mapper.findByTime(studenterId, planWeek,startTime,endTime);
        if(!classTimes.isEmpty()){
            // 如果不为空则代表时间冲突
            return 201;
        }
        List<ClassTime> classTimes1 = mapper.checkCourse(studenterId, planWeek, courseId);
        for (ClassTime classTime : classTimes1) {
            if(classTime.getWeek().equals(planWeek)){
                // 对重置计划进行额外判定，如果有符合的结果并且星期与设定的星期一致 代表设置为当天
                return 203;
            }
        }
        if(!classTimes1.isEmpty()){
            // 如果不为空则代表该课程已经提交
            return 202;
        }
        return 200;
    }
    /**
     * 查询学生该课程的安排
     * @param studenterId 学生编号
     * @param courseId 课程id
     * @return
     *
     */
    @Override
    public HashMap<String,ClassTime> findCurriculumByCourse(String studenterId,Integer courseId){
        HashMap<String,ClassTime> map = new HashMap<>();
        String weeks[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        // 查询一周的课程
        for (String week : weeks) {
            map.put(week,mapper.findByWeek(studenterId,courseId,week));
        }
        return map;
    }
    /**
     * 重置课程计划安排
     * @param id 计划表id
     * @return  修改结果，0为失败，其余为成功
     */
    @Override
    public int resetCurriculum(int id) {
        return mapper.resetCurriculum(id);
    }

    /**
     * 查询当前课程下有多少节
     * @param courseId 课程id
     * @return
     */
    @Override
    public int queryKnowNUmber(Integer courseId) {
        int result = 0;
        List<String> lists = mapper.queryKnowNUmber(courseId);
        HashSet<Integer> set = new HashSet<>();
        for (String list : lists) {
            List<Integer> lis = IdsUtils.getList(list);
            for (Integer li : lis) {
                set.add(li);
            }
        }
        result = set.size();
        return result;
    }

    public static List<List<TimeTable>> sortList(List<TimeTable> timeTables){
        List<List<TimeTable>> result = new ArrayList<>();
        List<TimeTable> MondayList = new ArrayList<>();
        List<TimeTable> TuesdayList = new ArrayList<>();
        List<TimeTable> WednesdayList = new ArrayList<>();
        List<TimeTable> ThursdayList = new ArrayList<>();
        List<TimeTable> FridayList = new ArrayList<>();
        List<TimeTable> SaturdayList = new ArrayList<>();
        List<TimeTable> SundayList = new ArrayList<>();
        for (TimeTable timeTable : timeTables) {
            if(timeTable.getPlanWeek().equals("Monday")){
                MondayList.add(timeTable);
            }else if(timeTable.getPlanWeek().equals("Tuesday")){
                TuesdayList.add(timeTable);
            }else if(timeTable.getPlanWeek().equals("Wednesday")){
                WednesdayList.add(timeTable);
            }else if(timeTable.getPlanWeek().equals("Thursday")){
                ThursdayList.add(timeTable);
            }else if(timeTable.getPlanWeek().equals("Friday")){
                FridayList.add(timeTable);
            }else if(timeTable.getPlanWeek().equals("Saturday")){
                SaturdayList.add(timeTable);
            }else{
                SundayList.add(timeTable);
            }
        }
        // 获得星期集合
        List<String> weekList = DateUtils.getWeekList();
        for (String week : weekList) {
            if(week.equals("Monday")){
                result.add(MondayList);
            }else if(week.equals("Tuesday")){
                result.add(TuesdayList);
            }else if(week.equals("Wednesday")){
                result.add(WednesdayList);
            }else if(week.equals("Thursday")){
                result.add(ThursdayList);
            }else  if(week.equals("Friday")){
                result.add(FridayList);
            }else if(week.equals("Saturday")){
                result.add(SaturdayList);
            }else{
                result.add(SundayList);
            }
        }
        return result;
    }
}
