package com.ershiyi.mapper;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 学习课堂模块持久层交互
 * @author: zss98
 * @date: 2020-08-05 11:46
 * @version: 1.0
 */
@Mapper
@Repository
public interface LearnMapper{
    /**
     * 根据学生编号查询出学生所有后购买的课程
     * @param studenterId 学生编号
     * @return
     */
    @Select("select * from purchase_course_info where studenterid = #{studenterId}  order by id desc ")
    public List<CoursePojo> findAllCourse(@Param("studenterId") String studenterId);


    /**
     * 根据课程id查询出章节信息
     * @param courseId 课程id
     * @return
     */
    @Select("select id as chapterId ,chapterName,knowledgeid as knowIds from common_course_chapter where courseId = #{courseId} and deleted = 0 ")
    public List<ChapterInfo> findChapter(@Param(("courseId")) Integer courseId);

    /**
     * 根据课程id查询出课程信息和评论者id
     * @param courseId 课程id
     * @return
     */
    @Select("select * from comment_info where courseId = #{courseId} and deleted = 0 order by commentId desc")
    public List<CommentInfo> queryComment(@Param("courseId") Integer courseId);

    /**
     * 查询评论点赞信息
     */
    @Select("select guid from common_course_discuss_thumbs where discussId = #{discussId} and deleted = 0")
    public List<String> queryLikeInfo(@Param("discussId") Integer discussId);

    /**
     * 对当前评论进行点赞
     * @param commentId 评论id
     * @param guid 学生编号
     * @return 点赞的结果 0为成功 1为失败
     */
    @Insert("insert into common_course_discuss_thumbs(guid,discussId) values(#{guid},#{commentId})")
    public int giveLike(@Param("commentId") Integer commentId, @Param("guid") String guid);

    /**
     * 取消当前评论的点赞
     *@param discussId 评论id
     * @param guid 学生编号
     * @return 取消点赞的结果 0为成功 1为失败
     */
    @Update("update common_course_discuss_thumbs set deleted = 1 where guid = #{guid} and discussId = #{discussId}")
    public int cancelLike(@Param("discussId") Integer discussId, @Param("guid") String guid);

    /**
     * 用户发布评论
     * @param message 评论内容
     * @param guid 用户编号
     * @param courseId 课程id
     * @return
     */
    @Insert("insert into common_course_discuss(discuss,courseId,guid) values(#{message},#{courseId},#{guid})")
    public int publishComment(@Param("message") String message, @Param("guid") String guid, @Param("courseId") Integer courseId);

    /**
     * 单选题
     * @param question  题目id
     * @return
     */
    public QuestionChoice choiceSQuestion(QuestionType question);

    /**
     * 多选题
     * @param question  题目id
     * @return
     */
    public QuestionChoice choiceMQuestion(QuestionType question);

    /**
     * 判断题
     * @param question  题目id
     * @return
     */
    public QuestionJudge judgeQuestion(QuestionType question);


    /**
     * 收藏当前知识点
     * @param knowId 知识点nid
     * @param studenterId 学生编号
     * @param subjectId 科目id
     * @param courseName 课程名称
     * @return 0为插入失败，其余为成功
     */
    @Insert("insert into common_collect_knowledge(courseId,chapterId,knowledgeId,studenterid,subjectid,courseName) values(#{courseId},#{chapterId},#{knowId},#{studenterId},#{subjectId},#{courseName})")
    public int collectKnow(@Param("courseId") Integer courseId, @Param("chapterId") Integer chapterId, @Param("courseName") String courseName, @Param("subjectId") Integer subjectId, @Param("studenterId") String studenterId, @Param("knowId") Integer knowId);

    /**
     * 提交学生答题情况
     * @param
     * @return
     */
    public int  submitQuestion(List<Correct> list);

    /**
     * 查询当前知识点学生是否已经收藏
     */
    @Select("select id from common_collect_knowledge where deleted = 0 and studenterid = #{studenterId} and knowledgeId = #{knowId} and courseId = #{courseId} and chapterId = #{chapterId}")
    public List<Integer> queryCollect(@Param("chapterId") Integer chapterId, @Param("courseId") Integer courseId, @Param("studenterId") String studenterId, @Param("knowId") int knowId);

    /**
     * 浏览课程后添加当前课程到该学生的历史记录
     * @param studenterId
     * @param courseId
     * @return
     */
    @Select("insert into common_student_browsing_history(studenterid,courseId) values(#{studenterId},#{courseId})")
    Integer addHistory(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId);

    /**
     * 记录学生课程学习结束位置
     * @param
     * @return
     */
    @Insert("insert into know_copy.common_knowledge_study_record(studenterId,courseId,chapterId,knowledgeId) values(#{studenterId},#{courseId},#{chapterId},#{knowId}) ")
    Integer addStudyRecord(@Param("courseId") Integer courseId,@Param("chapterId")Integer chapterId,@Param("studenterId")String studenterId);

    /**
     * 获取这个学生刚刚发布的评论信息
     * @param courseId
     * @param guid
     * @return
     */
    @Select("select * from comment_info where courseId = #{courseId} and guid = #{guid} and deleted = 0 order by commentId desc limit 1 ")
    CommentInfo getLastComment(@Param("courseId") Integer courseId, @Param("guid") String guid);

    /**
     * 将学生错误的题目插入到错题库
     * @param correct
     * @return
     */
    @Insert("insert into common_course_wrongquestions(knowledgeId,questionType,studenterId,courseId,doQuestionType,questionId,fillAnswer) values(#{knowId},#{questionType},#{studenterId},#{courseId},1,#{questionId},#{fillAnswer})")
    int insertWrongQuestion(Correct correct);

    /**
     * 判断当前章节是否已经学习了
     * @param request
     * @return
     */
    @Select("select id from know_copy.common_knowledge_study_record where courseId = #{courseId} and chapterId = #{chapterId} and knowledgeId = #{knowId} and studenterId = #{studenterId}")
    List<Integer> queryChapterIsStudy(RequestDTO request);

    /**
     * 将当前题目插入难题库
     * @param correct
     * @return
     */
    @Insert("insert into common_course_difficult(knowledgeId,questionType,questionId,studenterId,difficultType) values(#{knowId},#{questionType},#{questionId},#{studenterId},0)")
    Integer insertDifficultyQuestion(Correct correct);



    @Update("update common_Ipush_record set static =2 where plushContentId =#{knowContentId} and plushFrequency=#{plushFrequency} ")
    Integer updatepushstatic(Correct correct);

    /**
     * 查詢是否有这条知识点内容推送存在
     * @param correct
     */
    @Select("select count(id) from common_Ipush_record where plushContentId =#{knowContentId} and pushType=#{sendType} and static =0")
    int selectplush(Correct correct);

    @Select("select  count(id) from  common_course_knowledge_content where  id=#{knowContentId}  and deleted=0")
    int selectpushquestionby(Correct correct);

    @Select("select usetime from common_course_knowledge_record where knowledgeid=#{knowContentId}  order by id  desc  limit 1")
    String SelectStudyTimeBy(Correct correct);

    @Select("select  usetime from common_course_studyknowledge_record where knowledgecontentid=#{knowContentId} order by id desc  limit 1")
    String SelectStudyTimeByknowledge(Correct correct);

    /**
     * 获取目录信息
     * @param request
     * @return
     */
    @Select("select id as chapterId,knowledgeName as chapterName,isLast,subjectId,courseId,level from know_copy.common_course_knowledge where courseId = #{courseId} and level = 2")
    List<ChapterMenu> knowledgeMenu(RequestDTO request);

    /**
     * 获取下级的知识点信息
     * @param request
     * @return
     */
    @Select("select id as chapterId,pid,(select if(count(id)>0,1,0) from know_copy.common_knowledge_study_record where studenterId = #{studenterId} and knowledgeId = a.id) as isStudy,knowledgeName as chapterName,isLast,subjectId,courseId,knowledgeContent as knowContent,level from know_copy.common_course_knowledge a where pid = #{chapterId}")
    List<ChapterMenu> nextKnow(RequestDTO request);

    /**
     * 查询学习过的章节id
     * @param request
     * @return
     */
    @Select("select DISTINCT chapterId from know_copy.common_knowledge_study_record where studenterId = #{studenterId} and courseId = #{courseId}")
    List<Integer> getStudyChapter(RequestDTO request);

    /**
     * 从当前知识点下随机获取题目
     * @param knowId
     * @param number
     * @return
     */
    List<QuestionType> getQuestionId(@Param("knowId") Integer knowId,@Param("number")int number);

    /**
     * 随机获取当前科目的题目
     * @param courseId
     * @param number
     * @return
     */
    List<QuestionChoice> getRandom(@Param("courseId") int courseId,@Param("number") int number);
}

