package com.ershiyi.mapper;

import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 家长端持久层接口实现类
 * @author: zss98
 * @date: 2020-12-01 16:35
 * @version: 1.0
 */
@Repository
@Mapper
public interface ParentMapper {

    /**
     * 根据手机号查询学生信息
     * @param request
     * @return
     */

    @Select("select * from student_detailed where loginId = #{loginId}")
    List<StudentInfo> searchStudent(RequestDTO request);

    /**
     * 家长关联学生信息
     * @param request
     * @return
     */
    @Insert("insert into parent_relation_student(parenterId,studenterId) values(#{parenterId},#{studenterId})")
    int relationStudent(RequestDTO request);

    /**
     * 获取家长信息
     * @param request
     * @return
     */
    @Select("select a.*,b.parenterid from sys_user a INNER JOIN common_parent_user b on a.guid = b.parentuserid where b.parenterId = #{parenterId}")
    ParentInfo parentInfo(RequestDTO request);


    /**
     * 获取家长绑定的学生信息
     * @param requestDTO
     * @return
     */
    @Select("select * from student_detailed where studenterId in (select studenterId from parent_relation_student where parenterId = #{parenterId} and deleted=0) ")
    List<StudentInfo> associateStudents(RequestDTO requestDTO);


    /**
     * 获取今日待学的课程
     * @param studenterId 学生编号
     * @return
     */
    @Select("select id as courseId,curriculum as courseName,picture,h_picture as image,(select count(id) from common_course_knowledge where courseId = a.id and isLast = 1) as countKnow,(select count(DISTINCT knowledgeId) from common_study_record where studenterId = #{studenterId} and courseId = a.id) as finishKnow from common_course a where id in ( select DISTINCT courseId from common_course_purchase where studenterId = #{studenterId} and status = 1 ) and deleted = 0 ")
    List<CourseStudy> awaitCourse(@Param("studenterId") String studenterId);

    /**
     * 获取学生学完的所有科目
     * @param courseId
     * @param studenterId
     * @return
     */
    @Select("select ifnull(count(knowledgeId),0) from common_study_record where studenterId = #{studenterId} and courseId = #{courseId} and isFirst = 1")
    int getFinishKnow(@Param("courseId") int courseId, @Param("studenterId") String studenterId);

    /**
     * 获取学生所有学的知识点
     * @param request
     * @return
     */
    @Select("select ifnull(count(knowledgeId),0) from common_study_record where studenterId = #{studenterId} and isFirst = 1")
    int queryStudentAllKnows(RequestDTO request);

    /**
     * 获取到做的所有的题目数量
     * @param studenterId 学生编号
     * @return
     */
    @Select("select ifnull(count(*),0) from common_course_knowledge_record where studenterId=#{studenterId}")
    int getQuestions(@Param("studenterId") String studenterId);

    /**
     * 获取到学生正确的题目
     * @param studenterId 学生编号
     * @return
     */
    @Select("select ifnull(count(*),0) from common_course_knowledge_record where studenterId=#{studenterId} and correct = 1")
    int getRightQuestion(@Param("studenterId") String studenterId);

    /**
     * 获取学生所有的学习时间
     * @param request
     * @return
     */
    @Select("select ifnull(sum(useTime),0) from study_length where studenterId=#{studenterId}")
    int getStudyLength(RequestDTO request);

    /**
     * 获取对比昨天提升的数量
     * @param request
     * @return
     */
    int getRiseKnow(RequestDTO request);

    /**
     * 获取对比昨天学习时长提升
     * @param request
     * @return
     */
    int getRiseStudyLength(RequestDTO request);

    /**
     * 获取正确率
     * @param studenterId 学生编号
     * @param number 获取几天前的正确率
     * @return
     */
    @Select("select ifNull((select ifnull(count(id),0) from common_course_knowledge_record where correct = 1 and studenterId = #{studenterId} and TO_DAYS(NOW())-TO_DAYS(starttime) = #{number})/count(id),0) from common_course_knowledge_record where studenterId = #{studenterId} and TO_DAYS(NOW())-TO_DAYS(starttime) = #{number}")
    double getDateAccuracy(@Param("studenterId") String studenterId, @Param("number") int number);

    /**
     * 获取错题id和相关信息
     * @param request
     * @return
     */
    @Select("select DISTINCT questionType,questionId from common_course_wrongquestions where courseId = #{courseId} and studenterId = #{studenterId}")
    List<QuestionType> getWrongQuestion(RequestDTO request);

    /**
     * 获取单选题内容
     * @param choices
     * @return
     */
    ArrayList<QuestionWrongChoice> queryChoiceQuestion(List<String> choices);

    /**
     * 获取多选题内容
     * @param multiples
     * @return
     */
    ArrayList<QuestionWrongChoice> queryMultipleQuestion(List<String> multiples);

    /**
     * 获取判断题内容
     * @param judges
     * @return
     */
    List<QuestionWrongJudge> queryJudgeQuestion(List<String> judges);

    /**
     * 获取知识点内容
     * @param
     * @return
     */
    KnowContent queryKnow(String knowId);

    /**
     * 获取课程名称
     * @param request
     * @return
     */
    @Select("select curriculum from common_course where id = #{courseId}")
    String getCourseInfo(RequestDTO request);


    /**
     * 获取该课程当天的学习时间
     * @param request
     * @return
     */
    @Select("select ifnull(sum(useTime),0) from study_length where studenterId = #{studenterId} and courseId = #{courseId} and TO_DAYS(startTime)=TO_DAYS(NOW())")
    Long getNowStudyLength(RequestDTO request);

    /**
     * 获取当前课程学习时间
     * @param request
     * @return
     */
    @Select("select ifnull(sum(useTime),0) from study_length where studenterId = #{studenterId} and courseId = #{courseId}")
    Long historyStudyLength(RequestDTO request);

    /**
     * 获取当前课程做的题目数量
     * @param request
     * @return
     */
    @Select("select ifnull(count(*),0) from common_course_knowledge_record where studenterId = #{studenterId} and courseId = #{courseId}")
    int getCourseQuestions(RequestDTO request);

    /**
     * 获取当前课程题目错误数量
     * @param request
     * @return
     */
    @Select("select count(*) from common_course_knowledge_record where studenterId = #{studenterId} and courseId = #{courseId} and correct = 0")
    int getCourseWrongQuestions(RequestDTO request);

    /**
     * 获取今天学习的知识点
     * @param request
     * @return
     */
//    @Select("select DISTINCT knowledgeId as knowId,(select knowledgeName as knowName from common_course_knowledge where id = a.knowledgeId ) as knowName from common_study_record a where studenterId = #{studenterId} and To_Days(now())=To_Days(createTime)")
//    List<Know> nowStudyKnow(RequestDTO request);
    @Select("select DISTINCT knowledgeId as knowId,(select knowledgeName as knowName from common_course_knowledge where id = a.knowledgeId ) as knowName from common_study_record a where studenterId = #{studenterId} and To_Days(now())=To_Days(createTime) and courseId = #{courseId}")
    List<Know> nowStudyKnow(RequestDTO request);
    /**
     * 获取课程详细信息
     * @param request
     * @return
     */
    List<CoursePojo> getCoursePOJO(RequestDTO request);

    /**
     * 收藏当前课程
     * @param request
     * @return
     */
    @Insert("insert into common_collect_course(courseId,studenterId) values(#{courseId},#{parenterId})")
    int collectCourse(RequestDTO request);

    /**
     * 取消收藏当前课程
     * @param request
     * @return
     */
    @Update("update common_collect_course set deleted = 1 where studenterId = #{parenterId} and courseId = #{courseId}")
    int cancelCollect(RequestDTO request);

    /**
     * 增加浏览历史
     * @param request
     * @return
     */
    @Insert("insert into common_student_browsing_history(studenterId,courseId) values(#{parenterId},#{courseId})")
    int insertViews(RequestDTO request);

    /**
     * 获取章节信息
     * @param request
     * @return
     */
    @Select("select id as chapterId,knowledgeName as chapterName from common_course_knowledge where courseId =#{courseId} and level = 2")
    List<chapterInfo> queryChapterInfo(RequestDTO request);

    /**
     * 获取当前科目的正确率
     * @param studenterId
     * @param courseId
     * @return
     */
    @Select("select ifnull((select count(id) from common_course_knowledge_record where studenterId =#{studenterId} and courseId = #{courseId} and correct = 1)/count(id),0) from common_course_knowledge_record where studenterId =#{studenterId} and courseId = #{courseId}")
    double getCourseAccuracy(@Param("studenterId") String studenterId, @Param("courseId") int courseId);

    /**
     * 模糊搜索课程
     * @param request
     * @return
     */
    @Select("select a.id as courseId,a.curriculum as courseName,a.author,a.synopsis,a.biography,a.picture,a.subjectid,(select subjectName from common_course_subject where id = a.subjectId) as subjectName,(select count(*) from common_student_browsing_history where courseid = a.id) as views,(select count(*) from common_course_knowledge where courseId = a.id and isLast = 1) as knowNumber,(select count(*) from common_course_purchase where courseid = a.id and studenterId = #{studenterId} and status = 1 limit 1) as isPay,(select count(*) from common_collect_course where courseId=a.id and studenterId =  #{parenterId} and deleted = 0 limit 1) as isCollection from common_course a where a.curriculum like #{name} ")
    List<CoursePojo> searchCourse(RequestDTO request);

    /**
     * 获取所有知识点学习情况
     * @param request
     * @return
     */
    //@Select("select DISTINCT knowledgeId as knowId,(select knowledgeName as knowName from common_course_knowledge where id = a.knowledgeId ) as knowName from common_study_record a where studenterId = #{studenterId}")
    @Select("select knowledgeId as knowId,(select knowledgeName as knowName from common_course_knowledge where id = a.knowledgeId ) as knowName from common_study_record a where studenterId = #{studenterId} and isFirst = 1 and courseId = #{courseId}")
    List<Know> allStudyKnow(RequestDTO request);

    /**
     * 按照热度排序
     * @param request
     * @return
     */
    List<CoursePojo> getHostCourse(RequestDTO request);

    /**
     * 获取学生端各平台app下载链接
     * @return
     */
    @Select("select appType, url as downUrl,size  from sys_application_version as a where version = (select max(version) from sys_application_version where a.appType=appType) and appPlatform = 1")
    List<ApplicationVersion> getDownUrl();

    /**
     * 获取轮播图
     * @return
     */
    @Select("select * from sys_banner_img where deleted = 0 and isUse = 1 and type = #{type}")
    List<BannerInfo> getBanner(RequestDTO request);

    /**
     * 获取课程详细信息
     * @param request
     * @return
     */
    CoursePojo CourseInfo(RequestDTO request);

    /**
     * 获取学习天数
     * @param request
     * @return
     */
    @Select("select startTime from study_length where studenterId = #{studenterId} GROUP BY day(starttime)")
    List<String> getStudyDays(RequestDTO request);
    @Select("select 1 from parent_relation_student where studenterId=#{studenterId} and parenterId=#{parenterId}  limit 1;")
    Integer isrelation(RequestDTO request);
}
