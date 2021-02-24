package com.ershiyi.service;

import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.*;
import java.util.HashMap;
import java.util.List;

public interface ParentService {

    /**
     * 根据学生手机号获取学生信息
     * @param request
     * @return
     */
    List<StudentInfo> searchStudent(RequestDTO request);

    /**
     * 家长关联学生信息
     * @param request
     * @return
     */
    int relationStudent(RequestDTO request);

    /**
     * 获取家长信息
     * @param request
     * @return
     */
    ParentInfo parentInfo(RequestDTO request);

    /**
     * 家长关联的学生信息
     * @param requestDTO
     * @return
     */
    List<StudentInfo> associateStudents(RequestDTO requestDTO);

    /**
     * 学生今日所有待学的课程
     * @param request
     * @return
     */
    List<CourseStudy> toDayStudy(RequestDTO request);

    /**
     * 学生荣耀得分
     * @param request
     * @return
     */
    GloryScore gloryScore(RequestDTO request);

    /**
     * 学生当前课程的错题库
     * @param request
     * @return
     */
    List<ResultWrongQuestion> wrongQuestion(RequestDTO request);

    /**
     * 家长先学后教
     * @param request
     * @return
     */
    KnowContent parentLearn(RequestDTO request);

    /**
     * 学生当前课程学习情况
     * @param request
     * @return
     */
    CourseInfo CourseInfo(RequestDTO request);


    /**
     * 所有课程
     * @param request
     * @return
     */
    List<CoursePojo> allCourse(RequestDTO request);

    /**
     * 家长收藏当前课程
     * @param request
     * @return
     */
    int collectCourse(RequestDTO request);

    /**
     * 家长取消收藏当前接口
     * @param request
     * @return
     */
    int cancelCollect(RequestDTO request);

    /**
     * 家长查看当前课程详细信息
     * @param request
     * @return
     */
    List<chapterInfo> chapterInfo(RequestDTO request);

    /**
     * 模糊搜索课程
     * @param request
     * @return
     */
    List<CoursePojo> searchCourse(RequestDTO request);

    /**
     * 历史学习
     * @param request
     * @return
     */
    CourseInfo historyStudy(RequestDTO request);

    /**
     * 获取家长端app各平台下载链接
     * @param request
     * @return
     */
    List<HashMap> getDownUrl(RequestDTO request);

    List<BannerInfo> banner(RequestDTO request);

    /**
     * 获取课程详情
     * @param request
     * @return
     */
    CoursePojo getCourseInfo(RequestDTO request);
}
