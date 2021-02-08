package com.ershiyi.mapper;

import com.ershiyi.domain.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询学生学习数据
 * @author zss98909
 */

@Repository
@Mapper
public interface StudyDataMapper {
    /**
     *
     * @param studenterId 学生编号
     * @return 当日学习情况占比
     */
    @Select("call common_subject_time_today(#{studenterId,mode=IN,jdbcType=VARCHAR})")
    @Options(useCache = true,flushCache = Options.FlushCachePolicy.TRUE,timeout = 10000)
    public String find_ToDay(String studenterId);

    /**
     *
     * @param studenterId 学生编号
     * @return 当周学习情况占比
     */
    @Select("call common_subject_time_week(#{studenterId,mode=IN,jdbcType=VARCHAR})")
    @Options(useCache = true,flushCache = Options.FlushCachePolicy.TRUE,timeout = 10000)
    public String find_Week(String studenterId);

    /**
     *
     * @param studenterId 学生编号
     * @return 当月学习情况占比
     */
    @Select("call common_subject_time_month(#{studenterId,mode=IN,jdbcType=VARCHAR})")
    @Options(useCache = true,flushCache = Options.FlushCachePolicy.TRUE,timeout = 10000)
    public String find_Month(String studenterId);

    /**
     * 查询学生的每天学习情况排名
     * @param studenterId  学生编号
     * @return 学生每天的学习情况排名
     */
    @Select("call common_result_slogan (#{studenterId,mode=IN,jdbcType=VARCHAR})")
    public StudyRank get_StudyRanking(String studenterId);

    /**
     * 查询学生一周内的学习时间和平均学习时间
     * @param studenterId
     * @return 时间的集合
     */
    @Select("call common_student_studytime (#{studenterId,mode=IN,jdbcType=VARCHAR})")
    @ResultType(StudentStudyTime.class)
    public StudentStudyTime getStudayTime(String studenterId);

    /**
     * 根据学生编号查询到当天学生所有的试题
     * @param studenterId 学生编号
     * @return
     */
    @Select("select *from common_question_Accuracy a where studenterId = #{studenterId} and TO_DAYS(a.starttime) = TO_DAYS(now())")
    public List<QuestionsAccuracy> findToDayQuestion(String studenterId);

    /**
     * 根据学生编号查询到7天内学生所有的试题
     * @param studenterId 学生编号
     * @return
     */
    @Select("select *from common_question_Accuracy a where studenterId = #{studenterId} and TO_DAYS(now())-TO_DAYS(a.starttime) <= 7")
    public List<QuestionsAccuracy> findWeekQuestion(String studenterId);
    /**
     * 根据学生编号查询到30天内学生所有的试题
     * @param studenterId 学生编号
     * @return
     */
    @Select("select *from common_question_Accuracy a where studenterId = #{studenterId} and TO_DAYS(now())-TO_DAYS(a.starttime) <= 30")
    public List<QuestionsAccuracy> findMonthQuestion(String studenterId);

    /**
     * 查询所有的科目情况
     * @return
     */
    @Select("select id as subjectId,subjectName,deleted,ifuser from common_course_subject")
    public List<subjectInfo> findAllSubject();

    /**
     * 得到正确率
     * @param studenterId
     * @return
     */
    @Select("select getAccuracy(#{studenterId})")
    double Accuracy(@Param("studenterId") String studenterId);

    /**
     * 做题正确用时时间和总用时时间的比值
     * @param studenterId 学生编号
     * @return
     */
    @Select("select ifnull(sum(useTime),0)/ (select ifnull(sum(useTime),0) from common_question_Accuracy where studenterId = #{studenterId} )   from common_question_Accuracy where studenterId = #{studenterId} and correct =1")
    double studyLength(@Param("studenterId") String studenterId);

    /**
     * 获取所有学习时长中知识点学习时长占比
     * @param studentId
     * @return
     */
    @Select("select ifnull(sum(useTime)/(select sum(useTime) from study_length where studenterId = #{studenterId}),0) from common_course_studyknowledge_record where studenterId = #{studenterId}")
    double getLearnLength(@Param("studenterId") String studentId);

    /**
     * 获取学习进度
     * @param studentId
     * @return
     */
    @Select("select count(DISTINCT knowledgeId)/(select count(*) from common_course_knowledge where courseId in (select courseId from common_course_purchase where studenterId = #{studenterId} and status = 1)) from common_study_record where studenterId = #{studenterId}")
    double getProgress(@Param("studenterId") String studentId);

    /**
     * 获取平均时间比值
     * @param studentId
     * @return
     */
    @Select("select ifNull(avg(useTime)/(select avg(useTime) from study_length where TO_days(now())-to_days(startTime)<31),0) from study_length where studenterId = #{studentId} and TO_days(now())-to_days(startTime)<31")
    double getAvgStudyTime(String studentId);

    /**
     * 获取所有学完的章节
     * @param courseId 课程id
     * @return
     */
    @Select("SELECT DISTINCT chapterId FROM `common_study_record` where surplusNumber = 0 and studenterId = #{studentId} and courseId = #{courseId}")
    List<Integer> getFinishChapter(@Param("courseId") int courseId,@Param("studentId") String studentId);

    /**
     * 获取所有学完的知识点
     */
    @Select("SELECT DISTINCT knowledgeId FROM `common_study_record` where studenterId = #{studentId} and courseId = #{courseId}")
    List<Integer> getFinishKnow(@Param("courseId") int courseId,@Param("studentId") String studentId);

    /**
     * 获取当前课程所有的知识点id
     */
    @Select("select id as knowId,knowledgeContent as knowName,knowledgeContentId as knowContentIds from common_course_knowledge where pid = #{chapterId} and deleted = 0")
    List<KnowledgeInfo> getAllKnow(@Param("chapterId") Integer chapterId);

    /**
     * 获取当前课程所有的章节id
     */
    @Select("select id as chapterId,chapterName,serialId from common_course_chapter where courseId = #{courseId} and deleted = 0")
    List<ChapterInfo> getAllChapter(@Param("courseId") int courseId);

    /**
     * 获取当前知识点下的所有知识点内容
     * @param list
     * @return
     */
    List<String> getKnowContent(List<String> list);

    /**
     * 根据guid获取学生编号
     * @param guid
     * @return
     */
    @Select("select studenterId from common_student_user where studentUserId = #{guid}")
    String getStudentId(@Param("guid") String guid);

    /**
     * 获取学生所有购买的课程
     * @param guid
     * @return
     */
    @Select("select * from buy_course where guid = #{guid}")
    List<CourseInfo> buyCourse(String guid);
}
