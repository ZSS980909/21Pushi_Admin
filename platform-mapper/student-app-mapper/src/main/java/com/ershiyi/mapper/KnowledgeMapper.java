package com.ershiyi.mapper;

import com.ershiyi.domain.entity.CoursePojo;
import com.ershiyi.domain.entity.KnowContent;
import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.dto.RequestDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 知识点配对持久层操作类
 * @author: zss98
 * @date: 2020-09-18 15:20
 * @version: 1.0
 */
@Repository
public interface KnowledgeMapper {

    /**
     * 获取课程信息列表
     * @return
     */
    @Select("select id as courseId,curriculum as courseName,subjectId,picture from common_course where deleted = 0")
    List<CoursePojo> courseList();

    /**
     * 获取一条当前科目最近的题目
     * @param request
     * @return
     */
    @Select("select id as questionId,optionA,optionB,1 as type,optionC,optionD, answer as correctOption,question,resolving " +
            " from know_copy.common_course_choice where deleted = 0 and isRelevanceFinish = 0 and " +
            " subjectId = #{subjectId} ORDER BY id desc limit 1")
    QuestionChoice getChoiceQuestion(RequestDTO request);

    /**
     *
     * @param knowId
     * @return
     */
    @Select("select knowledgeContentId from common_course_knowledge where id = #{knowId}")
    String getKnowContentId(@Param("knowId") Integer knowId);

    /**
     * 获取知识点内容列表
     * @param ids
     * @return
     */
    List<KnowContent> getKnowList(List<String> ids);

    /**
     * 插入学生题目关联信息
     * @param request
     * @return
     */
    @Insert("insert into common_course_question(questionTypeId,questionId,knowledgeId,studenterId) values(#{type},#{questionId},#{ids},#{studenterId})")
    Integer insertRelation(RequestDTO request);


    /**
     * 获取当前题目已经插入了的信息
     * @param request
     * @return
     */
    @Select("select knowledgeId from common_course_question where questionId = #{questionId} and questionTypeId = #{type} and knowledgeId != '0'" )
    List<String> getRelationInfo(RequestDTO request);

    /**
     * 将当前题目插入问题库
     * @param request
     * @return
     */
    @Insert("insert into common_course_question(studenterId,questionTypeId,questionId,knowledgeId) values(#{studenterId},#{type},#{questionId},'0')")
    Integer insertQuestionStatus(RequestDTO request);

    /**
     * 查询当前题目被标记有问题有多少条
     * @param request
     * @return
     */
    @Select("select count(id) from common_course_question where questionId = #{questionId} and questionTypeId = #{type} and knowledgeId = '0'")
    Integer getErrorQuestionNumber(RequestDTO request);

    /**
     * 当前单选题题目被认为有问题，修改题目状态为删除
     * @param request
     * @return
     */
    @Update("update common_course_choice set deleted = 1 where id = #{questionId}")
    Integer modifyQuestionChoice(RequestDTO request);

    /**
     * 当前单选题题目被认为有问题，修改题目状态为删除
     * @param request
     * @return
     */
    @Update("update common_course_Multi set deleted = 1 where id = #{questionId}")
    Integer modifyQuestionMulti(RequestDTO request);

    /**
     * 当前单选题题目被认为有问题，修改题目状态为删除
     * @param request
     * @return
     */
    @Update("update common_course_Judge set deleted = 1 where id = #{questionId}")
    Integer modifyQuestionJudge(RequestDTO request);

    /**
     * 修改判断题题目状态
     * @param questionId
     * @param list 知识点内容id集合
     * @return
     */
    int modifyQuestionStatus(@Param("questionId") String questionId,@Param("list") List<String> list,@Param("type") Integer type);
}
