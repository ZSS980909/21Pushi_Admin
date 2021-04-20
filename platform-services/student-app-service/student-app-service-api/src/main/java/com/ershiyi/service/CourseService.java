package com.ershiyi.service;

import com.ershiyi.domain.*;
import com.ershiyi.domain.Chapter;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.JHZCourseDTO;
import com.ershiyi.dto.RequestDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public PageInfo<CoursePojo> Obscure(RequestDTO request);


    public List<CoursePojo> billboard(Billboardtitle billboard);

    public List<Chapter> chapterById(Course course);

    public Integer courseByBrowse(int courseId, String studenterId);

    public List<CoursePojo> courseByCompetitiv();

    public <T> T  courseByknowledge(Chapter chapter);

    public CoursePojo courseById(Course course);

   /* public List<CommentInfo> courseByAppraise(Course course);*/

    public int appraiseByDiscuss(Thumbs thumbs);

    public Integer appraiseByDiscussStatus(Thumbs thumbs);

    public Integer courseByCollect(String studenterId, Integer courseId);

    public boolean courseByCollectIf(Collect_Course collect_course);

    public List<Notice> courseByNotice(Notice notice);

    public List<Knowledge> courseByKnowledgeAll(Chapter chapter);

    public <T> T courseByStudy(QuestionContent questioncontent);

    public  List<KnowledgeContent> courseByKnowledgeContent(Knowledge knowledge);

    public List<CoursePojo> searchByBusCourse(Common_Search search);

//    public Object selectBySyllabus(Common_Search search);

    public List<CoursePojo> selectCourseBySubject(String studenterId, Integer subjectId);

    public List<LZMDType> LZMDKnowledge(Common_Search search);

    public List<Map<String,Object>> LZMDknowledgeByQuestion(LZMDType lzmdtype);

    public Object LZMDQuestionBySubmit(Map<String, List<Common_StudyrateBy>> list);

    PageInfo<CoursePojo> courseForSubject(String studenterId, Integer subjectId, Integer pageNumber, Integer pageSize);

    PageInfo<CoursePojo> authorAbout(Integer creatorId, Integer courseId, Integer pageNumber, Integer pageSize);

    Integer buyCourse(Integer courseId, String studenterId);

    /**
     * 查询学生当前日期的课程安排
     * @param studenterId 学生编号
     * @param date 日期
     * @return
     */
    List<CoursePlan> queryStudyPlan(String studenterId, String date);

    /**
     * 根据科目来查询错误题目
     * @param request
     * @return
     */
    PageInfo<ResultWrongQuestion> wrongQuestion(RequestDTO request);

    /**
     * 首页课程推荐
     * @param request
     * @return
     */
    List<CoursePojo> courseRecommend(RequestDTO request);

    List<Function_setting> Querytitle();
}
