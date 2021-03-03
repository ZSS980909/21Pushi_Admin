package com.ershiyi.mapper;

import com.ershiyi.domain.Chapter;
import com.ershiyi.domain.*;
import com.ershiyi.domain.Collect_Course;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.JHZCourseDTO;

import com.ershiyi.dto.RequestDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CourseMapper extends AbstractMapper<JHZCourseDTO> {

    List<JHZCourseDTO> queryCourse(@Param("studenterId") String studenterId, @Param("courseWeek") String week);
    /**
     * 知识主页的待学课程查询
     */
    CoursePlan planCourse(JHZCourseDTO course);
    /**
     * 课堂id查询章节
     */
    Integer courseChapter(String Courseid);

    // 查询当前学生没有计划的课程
    @Select("select courseId,studenterId from common_course_plan where studenterId = #{studenterId} and planType = 0 and deleted = 0")
    List<JHZCourseDTO> queryNotPlanCourse(@Param("studenterId") String studenterId);
    List<Integer> Obscure(String courseName);

    List<subjectInfo> title();  //select billboardname,billboardkey,billboartype from  common_billboard_title
        //热门
    List<Integer> billboardbillboardByHOT();
        //推荐
    List<Integer> billboardByRECOMMEND(Billboardtitle billboard);
       //最新
    List<Integer> billboardbillboardByNEWEST(Billboardtitle billboard);
       //助学
    List<Integer> billboardbillboardBySTUDYAID(Billboardtitle billboard);
        //根据课程id查询章节信息
    List<Chapter> chapterById(Course course);
        //增加浏览数
    @Insert("insert into common_student_browsing_history(studenterId,courseId) values(#{studenterId},#{courseId})")
    Integer courseByBrowse(@Param("courseId") int courseId, @Param("studenterId") String studenterId);
        //榜单排名课程内容信息  查询视图
    List<Integer> courseByCompetitiv();
            //课程所有信息查询
    CoursePojo courseById(int courseId);
            //课程评论查
    List<CommentInfo> courseByAppraise(Course course);
     // 搜索课程查询
    @Select("call student_search_course(#{courseId},#{studenterId})")
    CoursePojo searchCourseInfo(@Param("courseId") Integer courseId, @Param("studenterId") String studenterId);
                //评论点赞
    Integer appraiseByDiscuss(Thumbs thumbs);
                //评论点赞状态修改
    Integer appraiseByDiscussStatus(Thumbs thumbs);
            //收藏课程
    @Insert("insert into common_collect_course(studenterId,courseId) values(#{studenterId},#{courseId})")
    Integer courseByCollect(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId);
            //查询是否收藏该课程
    Collect_Course courseByCollectIf(Collect_Course collect_course);
            //查询该课程的公告
    List<Notice> courseByNotice(Notice notice);
            //知识点查询
   // Knowledge courseByKnowledgeAll(String knowledge);
        //查询该章节下所有的知识点标题
    Knowledge courseByKnowledgeAll(@Param("id") String id);
        //返回题目
    <T> T courseByStudy(QuestionContent questioncontent);
    //查询所有知识点的内容
    List<KnowledgeContent> courseByKnowledgeContent(Knowledge knowledge);
    //根据id查询的课程信息
    CoursePojo searchCourse(int courseId);
    // 查询当前课程是否已经收藏
    @Select("select id from common_collect_course where courseId = #{courseId} and studenterId = #{studenterId} and deleted = 0")
    List<Integer> queryIsCollect(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId);
    //根据科目查询课堂
    @Select("select id from common_course where subjectId = #{subjectId} and id not in(select courseId from common_collect_course where studenterId = #{studenterId} and deleted = 0) and  deleted=0 order by   createdt desc ")
    List<Integer> selectCourseBySubject(@Param("studenterId") String studenterId, @Param("subjectId") Integer subjectId);
    // 查询已购买的课程id
    List<Integer> searchByBusCourseIds(Common_Search search);
    /**
     * 根据课程id查询出课程信息和评论者id
     * @param courseId 课程id
     * @return
     */
    @Select("select * from comment_info where courseId = #{courseId} and deleted = 0")
    public List<CommentInfo> queryComment(@Param("courseId") Integer courseId);
    /**
     * 查询评论点赞信息
     */
    @Select("select studenterId from common_course_discuss_thumbs where discussId = #{discussId} and deleted = 0")
    public List<String> queryLikeInfo(@Param("discussId") Integer discussId);

    String selectDiscussByCourseid(@Param("id") Integer id);
        //查询临阵磨刀模式 完成了多少个知识点
    List<Common_Return> LZMDKnowledge(Common_Search search);
            //查询临该学生已购买的课程总共多少个知识点
    List<Common_Return> CountKnowledge(Common_Search search);
        //查询
    void LZMDknowledgeByQuestionone(LZMDType lzmdtype);
    //随机获取目前开放题型
    List SelectQuestionType();

//    List<Map> randoquestion(List<Common_Question> questionlist);
    //单选题
    List<Common_Choice> ChoiceQuestion(List choiceList);
    //判断题
    List<Common_Judge> JudgeQuestion(List judgeList);
//    /*select * from (select t1.id as questionId,t1.knowledgeid as knowId,t1.title as question,t1.resolving,t1.rightaws as correctOption,t1.isgb,t1.schoolId,t1.subjectid from common_course_judge AS t1 JOIN (SELECT ROUND(RAND() *
//            ((SELECT MAX(id) FROM common_course_judge where   knowledgeid =#{item.knowId})-
//            (SELECT MIN(id) FROM common_course_judge where   knowledgeid =#{item.knowId}))+
//            (SELECT MIN(id) FROM common_course_judge where  knowledgeid =#{item.knowId})) AS id) AS t2
//            WHERE t1.id >= t2.id
//            ORDER BY  t1.id LIMIT 1) as a1*/
    //多选题
    List<Common_Multi> MultiQuestion(List multiList);

    // 查询当前科目下的所有所有未购买课程
    @Select("select id from common_course where subjectId =#{subjectId} and  id not in (select courseId from common_course_purchase where studenterId = #{studenterId} and status = 1) and deleted = 0 order by   createdt desc")
    List<Integer> courseForSubject(@Param("studenterId") String studenterId, @Param("subjectId") Integer subjectId);
    //查询当前科目下的所有所有未购买课程 subject=0 查询全部
    @Select("select id from common_course where  id not in (select courseId from common_course_purchase where studenterId = #{studenterId} and status = 1) and deleted = 0 order by   createdt desc")
    List<Integer> courseForSubjectone(@Param("studenterId")  String studentId);

    // 查询出当前作者下的所有id 不包括当前课程
    @Select("select id from common_course where creatorId = #{creatorId} and id != #{courseId}")
    List<Integer> queryAboutCourse(@Param("creatorId") Integer creatorId, @Param("courseId") Integer courseId);

    // 查询出学生目前的积分值
    @Select("select integralvalue from sys_user_integral where studenterId = #{studenterId}")
    Integer queryStudentIntegral(@Param("studenterId") String studenterId);

    // 购买成功修改学生的积分表值
    @Update("update sys_user_integral set integralvalue = integralvalue-#{integral},modifydt = now() where studenterId = #{studenterId}")
    Integer coursePaySuccess(@Param("studenterId") String studenterId, @Param("integral") Integer integral);

    // 添加当前课程到学生的购买课程表中
    @Insert("insert into common_course_purchase(studenterId,courseId,integralvalue,status) values(#{studenterId},#{courseId},#{integral},#{status})")
    Integer insertCoursePay(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId, @Param("integral") Integer integral, @Param("status") Integer status);

    // 将学生购买成功的课程添加到计划表内
    @Insert("insert into common_course_plan(studenterId,courseId,planType) values (#{studenterId},#{courseId},0)")
    Integer insertCoursePlan(@Param("courseId") Integer courseId, @Param("studenterId") String studenterId);
    // 判断当前课程是否已经购买，避免重复购买
    @Select("select id from common_course_purchase where studenterId = #{studenterId} and courseId = #{courseId} and status = 1")
    List<Integer> queryCourseExists(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId);


    void ZSubmit(Common_StudyrateBy common_studyrateBy);

    void NSubmit(Common_StudyrateBy common_studyrateBy);

    /**
     * 查询当前星期的待学课程
     * @param studenterId   学生编号
     * @param week 星期
     * @return
     */
    @Select("select courseId from common_course_timetable where studenterId = #{studenterId} and courseWeek = #{week} and deleted = 0")
    List<Integer> queryStudyPlan(@Param("studenterId") String studenterId, @Param("week") String week);

    @Select("call common_jhz_course(#{studenterId},#{courseId},#{week})")
    CoursePlan queryCourseForWeek(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId, @Param("week") String week);

    /**
     * 插入学生购买课程消费记录
     * @param studentId 学生编号
     * @param courseId  课程id
     * @param deduct    消费积分
     * @param studentIntegral   学生原始积分
     * @param surplus   学生当前积分
     * @return
     */
    @Insert("insert into sys_user_integral_record(studenterId,courseId,keyword,IntegralId,changeintegral,rawintegral,integralvalue) values(#{studentId},#{courseId},'PURCHASECOURSE',1,#{deduct},#{studentIntegral},#{surplus})")
    Integer insertRecord(@Param("studentId") String studentId, @Param("courseId") Integer courseId, @Param("deduct") int deduct, @Param("studentIntegral") Integer studentIntegral, @Param("surplus") int surplus);
    /**
     * 获取错误题目id和类型
     * @param request
     * @return
     */
    List<QuestionType> getWrongQuestionId(RequestDTO request);

    /**
     * 获取单选题内容
     * @param choiceIds
     * @return
     */
    ArrayList<WrongQuestionChoice> queryChoiceQuestion(@Param("list")List<Integer> choiceIds);

    /**
     * 获取多选题内容
     * @param choiceIds
     * @return
     */
    ArrayList<WrongQuestionChoice> queryMultipleQuestion(@Param("list")List<Integer> choiceIds);

    /**
     * 获取判断题内容
     * @param choiceIds
     * @return
     */
    List<WrongQuestionJudge> queryJudgeQuestion(@Param("list")List<Integer> choiceIds);

    /**
     * 获取所有推荐课程id
     * @param studenterId 学生编号
     * @param size 数量
     * @return
     */
    @Select("select id from common_course where id not in (select courseId from common_course_purchase where studenterId = #{studenterId}) and deleted = 0 order by createdt desc limit #{size}")
    List<Integer> queryRecommendCourse(@Param("studenterId") String studenterId,@Param("size") int size);

    /**
     * 查询系统设置的大小
     * @param key
     * @return
     */
    @Select("select ifNull(setSize,6) from sys_setting where key_word = #{key} and deleted = 0 and isUse = 1")
    Integer querySystemSettingSize(@Param("key")String key);

    @Select("select key_word as keyword, functionName, isUse, deleted, createdt, image, sort, subtitle from sys_function_setting where isUse=1 and deleted=0 ")
    List<Function_setting> Querytitle();

   // @Select("SELECT * from  (select questionId from  common_knowledge_question where common_knowledge_question.knowledgeId=#{knowledgeId}) a order by  rand() limit 1")
    @Select("select  questionId from  `know_copy`.common_knowledge_question where knowledgeId=#{knowledgeId} order by rand() limit 1")
    String SelectKnowledgeBylimit(@Param("knowledgeId")String knowledgeId);

    //@Select("select  id as questionId, knowledgeid as knowId, choicea as optionA, choiceb as optionB, choicec as optionC, choiced as optionD, rightaws as correctOption, resolving as resolving, title as question,isgb, schoolid, subjectid,(select courseId from  common_course_knowledge where  id =(select  knowledgeId from common_knowledge_question where  questionId=#{questionId} limit  1)) as courseId  from common_course_choice  where id=#{questionId}")
    @Select("select id as questionId,(select knowledgeId from  `know_copy`.common_knowledge_question where questionId=#{questionId} limit 1) as knowId,optionA,optionB,optionC,optionD,answer as correctOption,resolving,question,subjectid from  common_course_choice where id=#{questionId}")
    Common_Choice SelectQuestionBylimit(@Param("questionId") String questionId);

    @Select("select   id as questionId, knowledgeid as knowId, title as question, resolving as correctOption, rightaws, isgb, schoolid, subjectid,(select courseId from  common_course_knowledge where  id = (select  knowledgeId from common_knowledge_question where  questionId=#{questionId} limit  1)) as courseId from common_course_judge where id =#{questionId}")
    Common_Judge SelectQuestionBylimitone(@Param("questionId")String questionId);
}
