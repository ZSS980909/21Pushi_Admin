package com.ershiyi.mapper;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.dto.StudyRecordDTO;
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
     * 根据学生编号查询出学生所有购买的课程
     * @param studenterId 学生编号
     * @return
     */
    @Select("select * from purchase_course_info where studenterid = #{studenterId}  order by id desc ")
    public List<CoursePojo> findAllCourse(@Param("studenterId") String studenterId);

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
     * 浏览课程后添加当前课程到该学生的历史记录
     * @param studenterId
     * @param courseId
     * @return
     */
    @Select("insert into common_student_browsing_history(studenterid,courseId) values(#{studenterId},#{courseId})")
    Integer addHistory(@Param("studenterId") String studenterId, @Param("courseId") Integer courseId);


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
    List<ChapterMenu> knowledgeMenu(RequestDTO request);

    /**
     * 获取下级的知识点信息
     * @param request
     * @return
     */
    @Select("select id as chapterId,pid,left_value as leftValue,right_value as rightValue,knowledgeName as chapterName,isLast,subjectId,courseId,knowledgeContent as knowContent,level from know_copy.common_course_knowledge a where pid = #{chapterId}")
    List<ChapterMenu> nextKnow(RequestDTO request);

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

    /**
     * 判断是否为首次插入
     * @return
     */
    @Select("select id from know_copy.common_study_record where knowledgeId = #{knowId} and studenterId=#{studenterId}")
    List<String> isFirstStudy(@Param("knowId")int knowId,@Param("studenterId")String studenterId);

    /**
     * 插入学习记录
     * @param record
     * @return
     */
    int insertStudyRecord(StudyRecordDTO record);

    /**
     * 获取当前章节下的知识点内容数量
     * @param menu
     * @return
     */
    @Select("select count(id) from know_copy.common_course_knowledge where left_value >= #{leftValue} and right_value <= #{rightValue} and isLast = 1 and courseId = #{courseId}")
    int getKnowContentNumber(ChapterMenu menu);

    /**
     * 查询完成的知识点数量
     * @param studenterId
     * @param courseId
     * @param leftValue
     * @param rightValue
     * @return
     */
    @Select("select count(id) from know_copy.common_study_record where studenterId = #{studenterId} and courseId = #{courseId} and isFirst = 1 and left_Value >= #{leftValue} and right_value <= #{rightValue}")
    int getCompleteKnow(@Param("studenterId") String studenterId,@Param("courseId") int courseId, @Param("leftValue") int leftValue,@Param("rightValue") int rightValue);

    /**
     * 获取当前课程最后一次学习的位置
     * @param request
     * @return
     */
    @Select("select  courseId,left_value as leftValue,right_value as rightValue,knowledgeId as knowId,level from know_copy.common_study_record where studenterId = #{studenterId} and courseId = #{courseId} order by createTime desc limit 1")
    StudyRecordDTO getLastStudy(RequestDTO request);

    /**
     * 获取当前课程的第一个知识点ID
     * @param courseId
     * @return
     */
    @Select("select id as knowId,level,left_value as leftValue,right_value as rightValue,pid from know_copy.common_course_knowledge where courseId = #{courseId} and isLast = 1 order by left_value limit 1")
    StudyRecordDTO getFirstKnow(Integer courseId);

    /**
     * 获取当前课程下一个知识点id
     * @param leftValue
     * @param courseId
     * @return
     */
    @Select("select id as knowId,level,left_value as leftValue,right_value as rightValue,pid from know_copy.common_course_knowledge where courseId = #{courseId} and isLast = 1 and left_value > #{leftValue} order by left_value limit 1")
    StudyRecordDTO getNextKnow(@Param("leftValue") int leftValue,@Param("courseId") Integer courseId);

    /**
     * 获取当前知识点的根节点id
     * @param courseId 课程id
     * @param leftValue 左编码
     * @param rightValue 右编码
     * @param level 级别
     * @return
     */
    @Select("select id from know_copy.common_course_knowledge where left_value < #{leftValue} and right_Value > #{rightValue} and level = #{level} and courseId = #{courseId}")
    int getUpLevelId(@Param("leftValue") int leftValue,@Param("rightValue") int rightValue,@Param("level") int level,@Param("courseId")int courseId);
}

