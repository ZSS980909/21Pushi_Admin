package com.ershiyi.service;

import com.ershiyi.common.dto.AbstractBaseResult;
import com.ershiyi.domain.Knowledge;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.dto.StudyRecordDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @Description: 学习课堂服务层接口
 * @author: zss98
 * @date: 2020-08-05 11:39
 * @version: 1.0
 */
public interface LearnService {

    /**
     * 根据学生编号查询学生课程列表
     * @param studentId 学生编号
     * @param pageNumber 页码
     * @param pageSize 每页展示的数量
     * @return
     */
    PageInfo<CoursePojo> courseList(String studentId, Integer pageNumber, Integer pageSize);


    /**
     * 根据课程id查询出评论信息
     * @param courseId 课程id studentId 学生编号
     * @return
     */
    PageInfo<CommentInfo> commentInfo(Integer courseId, String guid, Integer pageNumber, Integer pageSize);

    /**
     * 对评论进行点赞
     * @param commentId 评论id
     * @param guid 学生编号
     * @return 插入的结果 0为失败 其余数字为成功
     */
    int giveLike(Integer commentId, String guid);

    /**
     * 发表评论
     * @param message 评论内容
     * @param guid 学生编号
     * @return 插入成功返回当前评论的内容
     */
    CommentInfo publishComment(String message, String guid, Integer courseId);

    /**
     * 当前评论取消点赞
     * @param discussId 评论id
     * @param studentId 学生编号
     * @return 插入的结果 0为失败 其余数字为成功
     */
    int  cancelLike(Integer discussId, String studentId);

    /**
     * 根据节点id以及题目类型查询出所关联的题目
     * @param knowId
     * @return 题目内容
     */
    List<ResultQuestion> knowQuestion(Integer knowId);

    /**
     * 收藏当前知识点
     * @param chapterId  章节id
     * @param courseId  课程id
     * @param studenterId 学生编号
     * @param knowId    知识点id
     * @param courseName 课程名称
     * @param subjectId 科目id
     * @return
     */
    int collectKnow(Integer courseId, Integer chapterId, String studenterId, Integer knowId, String courseName, Integer subjectId);

    int submitQuestion(List<Correct> request);

    /**
     * 查询当前课程下的章节目录以及学习状态
     * @param request
     * @return
     */
    List<ChapterMenu> chapterMenu(RequestDTO request);


    /**
     * 获取下一级的知识点列表
     * @param request
     * @return
     */
    List<ChapterMenu> KnowList(RequestDTO request);

    /**
     * 添加学习记录
     * @param record
     * @return
     */
    int addStudyRecord(StudyRecordDTO record);

    /**
     * 学习下一个知识点
     * @param request
     * @return
     */
    int[] nextKnow(RequestDTO request);

    /**
     *
     * @param request
     * @return
     */
    List<ChapterMenu> firstMenu(RequestDTO request);

    /**
     *
     * @param request
     * @return
     */
    List<ChapterMenu> nextMenu(RequestDTO request);

    /**
     * 获取共享学习笔记内容列表
     * @param request
     * @return
     */
    List<NoteInfo> noteList(RequestDTO request);

    /**
     * 发布学习笔记
     * @param request
     * @return
     */
    NoteInfo pushNote(NoteInfo request);

    /**
     * 对当前学习笔记点赞
     * @param request
     * @return
     */
    int likeNote(NoteInfo request);

    /**
     * 删除学习笔记点赞
     * @param request
     * @return
     */
    int cancelNoteLike(NoteInfo request);
}
