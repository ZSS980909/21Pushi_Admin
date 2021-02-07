package com.ershiyi.mapper;

import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.CommentInfo;
import com.ershiyi.entity.SubjectInfo;
import com.ershiyi.entity.TeacherInfo;
import com.ershiyi.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 家长端补教系统持久层
 * @author: zss98
 * @date: 2020-12-23 09:33
 * @version: 1.0
 */
@Repository
@Mapper
public interface TutoringMapper {

    /**
     * 获取老师信息
     * @param request
     * @return
     */
    public List<TeacherInfo> TeacherInfo(RequestDTO request);

    /**
     * 根据姓名搜索老师信息
     * @param realName
     * @return
     */
    public List<TeacherInfo> SearchTeacher(@Param("realName") String realName);

    /**
     * 获取老师可以补习的学科
     * @param result
     * @return
     */
    @Select("select period,subjectName,subjectId from common_tutoring_subject where teacherId = #{teacherId}")
    List<SubjectInfo> queryPossibleSubjects(TeacherInfo result);

    /**
     * 获取老师的标签信息
     * @param result
     * @return
     */
    @Select("select label from common_tutoring_label where teacherId = #{teacherId}")
    List<String> queryLabel(TeacherInfo result);

    /**
     * 记录当前老师的浏览记录
     * @param request
     * @return
     */
    int insertView(RequestDTO request);

    /**
     * 获取当前老师的评论列表
     * @param request
     * @return
     */
    List<CommentInfo> getCommentInfo(RequestDTO request);

    /**
     * 获取学生信息
     * @param result
     * @return
     */
    @Select("select nickName,userImage from sys_user where guid = (select studentUserId from common_student_user where studenterId = #{userId})")
    UserInfo getStudentUser(CommentInfo result);

    /**
     * 获取家长信息
     * @param result
     * @return
     */
    @Select("select nickName,userImage from sys_user where guid = (select parentUserId from common_parent_user where parenterId = #{userId})")
    UserInfo getParentUser(CommentInfo result);

    /**
     * 获取学生学习时间
     * @param result
     * @return
     */
    @Select("select ifnull(sum(classHour),0) from common_tutoring_purchase where studenterId = #{userId}")
    int getStudentLearnTime(CommentInfo result);

    /**
     * 获取家长学习时间
     * @param result
     * @return
     */
    @Select("select ifnull(sum(classHour),0) from common_tutoring_purchase where parenterId = #{userId}")
    int getParentLearnTime(CommentInfo result);
}
