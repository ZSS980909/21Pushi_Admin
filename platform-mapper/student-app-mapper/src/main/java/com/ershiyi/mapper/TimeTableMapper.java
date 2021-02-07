package com.ershiyi.mapper;

import com.ershiyi.domain.entity.ClassTime;
import com.ershiyi.domain.entity.CourseInfo;
import com.ershiyi.domain.entity.CurriculumPojo;
import com.ershiyi.domain.entity.TimeTable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @Description: 课表持久层方法
 * @author: zss98
 * @date: 2020-07-31 11:58
 * @version: 1.0
 */
@Mapper
@Repository
public interface TimeTableMapper {
    /**
     * 查询这个学生所有的课表
     * @param studenterId 学生编号
     * @return
     */
    @Select("select *  from student_time_table where studenterId = #{studenterId} and deleted = 0")
    public List<TimeTable> queryCurriculumTable(@Param("studenterId") String studenterId);

    /**
     * 查询所有的课程
     * @return
     */
    @Select("select id as courseId,curriculum as courseName from common_course")
    public List<CourseInfo> queryCourse();

    /**
     * 根据课程和时间判断当天是否已经有该课程
     * @param studenterId 学生编号
     * @param planWeek 星期
     * @param courseId 课程id
     * @return
     */
    @Select("select courseWeek as week,coursetimedt as time from common_course_timetable where studenterId = #{studenterId} and courseWeek = #{planWeek} and courseId = #{courseId} and deleted = 0")
    public List<ClassTime> checkCourse(@Param("studenterId")String studenterId,@Param("planWeek")String planWeek,@Param("courseId")Integer courseId);

    /**
     * 查询该课程一周的安排
     * @param studenterId 学生编号
     * @param courseId 课程id
     * @param planWeek 星期
     * @return
     */
    @Select("select coursetimedt as time,id as curriculumId,courseWeek as week from common_course_timetable where studenterId = #{studenterId} and courseId = #{courseId} and courseWeek=#{planWeek} and deleted = 0")
    public ClassTime findByWeek(@Param("studenterId") String studenterId,@Param("courseId")Integer courseId,@Param("planWeek")String planWeek);

    /**
     * 查询当前时间段是否有课程安排
     * @param studenterId 学生编号
     * @param planWeek 计划星期
     * @param startTime 计划时间
     * @param endTime 计划结束时间
     * @return
     */
    @Select("select courseWeek as week,coursetimedt as time from common_course_timetable where studenterId = #{studenterId} and courseWeek = #{planWeek} and coursetimedt > #{startTime} and coursetimedt < #{endTime} and deleted = 0")
    public List<ClassTime> findByTime(@Param("studenterId") String studenterId,@Param("planWeek") String planWeek, @Param("startTime") String startTime,@Param("endTime") String endTime);

    /**
     * 当前时间段未被使用，添加到数据库
     * @param curriculum 课程计划信息实体类
     * @return
     */
    @Insert("insert into common_course_timetable(courseWeek,courseTimeDT,courseId,studenterId) values(#{planWeek},#{planTime},#{courseId},#{studenterId})")
    public int addCurriculum(CurriculumPojo curriculum);

    /**
     * 获取到刚刚添加的计划表id
     */
    @Select("select id from common_course_timetable where courseWeek = #{planWeek} and courseTimeDT = #{planTime} and studenterId = #{studenterId} and courseId = #{courseId} ")
    public Integer queryCurriculumId(CurriculumPojo curriculum);
    /**
     * 添加计划的课程改变状态为未完成并且计划安排已完成
     * @param studenterId
     * @param courseId
     * @return
     */
    @Update("update common_course_plan set planType = 1 ,planFinish = 0 where courseId = #{courseId} and studenterId = #{studenterId}")
    public int modifyStatus(@Param("studenterId")String studenterId,@Param("courseId")int courseId);
    /**
     * 重置课程计划安排
     * @param id 计划表id
     * @return 修改结果，0为失败，其余为成功
     */
    @Update("update common_course_timetable set deleted = 1 where id = #{id}")
    public int resetCurriculum(@Param("id") int id);


    /**
     * 查询当前课程下的章节id
     * @param courseId 课程id
     * @return
     */
    @Select("select knowledgeId from common_course_chapter where courseId = #{courseId}")
    List<String> queryKnowNUmber(Integer courseId);

    // 判断学生当前课程是否已经安排了课程计划
    @Select("select id from common_study_plan where courseId = #{courseId} and studenterId = #{studenterId} and deleted = 0")
    public List<Integer> exitsCoursePlan(CurriculumPojo curriculum);


    // 修改学生课程计划安排
    @Update("update common_study_plan set planNumber = #{planNumber},set knowledgeNumber = #{knowledgeNumber} where " +
            "studenterId = #{studenterId} and courseId = #{courseId} and deleted = 0")
     public Integer modifyCoursePlan(CurriculumPojo curriculum);

    /**
     * 添加学生课程计划安排
     * @param curriculum
     * @return
     */
    @Insert("insert into" +
            " common_study_plan(courseId,studenterId,knowledgeNumber,planNumber) " +
            "values(#{courseId},#{studenterId},#{knowledgeNumber},#{planNumber})")
    public Integer insertCoursePlan(CurriculumPojo curriculum);
}
