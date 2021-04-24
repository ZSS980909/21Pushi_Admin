package com.ershiyi.service.Impl;

import com.ershiyi.Utils.DecimalUtils;
import com.ershiyi.Utils.ParentUtils;
import com.ershiyi.Utils.SwitchQuestionUtils;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.*;
import com.ershiyi.mapper.ParentMapper;
import com.ershiyi.service.ParentService;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @author: zss98
 * @date: 2020-12-01 16:32
 * @version: 1.0
 */
@Service
public class ParentServiceImpl implements ParentService {

    private static String PREFIX="https://api.pwmqr.com/qrcode/create/?url=";

    @Autowired
    private ParentMapper mapper;

    /**
     * 根据学生手机号获取学生信息
     * @param request
     * @return
     */
    @Override
    public List<StudentInfo> searchStudent(RequestDTO request) {
        List<StudentInfo> student = mapper.searchStudent(request);
        return student;
    }

    /**
     * 家长关联学生信息
     * @param request
     * @return
     */
    @Override
    public int relationStudent(RequestDTO request) {
        return mapper.relationStudent(request);
    }

    /**
     * 家长个人信息
     * @param request
     * @return
     */
    @Override
    public ParentInfo parentInfo(RequestDTO request) {
        return mapper.parentInfo(request);
    }

    /**
     * 获取家长关联的学生列表
     * @param requestDTO
     * @return
     */
    @Override
    public List<StudentInfo> associateStudents(RequestDTO requestDTO) {
        // 获取所有的学生信息
        List<StudentInfo> students = mapper.associateStudents(requestDTO);
        // 指定学生等级标签
        for (StudentInfo student : students) {
            double accuracy = DecimalUtils.div(mapper.getRightQuestion(student.getStudenterId()),mapper.getQuestions(student.getStudenterId()),2);
            if(accuracy<0.6){
                student.setLevelLabel("努力学习");
            }else if(accuracy<0.9){
                student.setLevelLabel("有待提高");
            }else if(accuracy<0.99){
                student.setLevelLabel("学霸");
            }else{
                student.setLevelLabel("学神");
            }
        }
        return students;
    }

    /**
     * 获取当前学生今日学习的课程信息
     * @param request
     * @return
     */
    @Override
    public List<CourseStudy> toDayStudy(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        // 获取所有待学的课程
        List<CourseStudy> courses = mapper.awaitCourse(request.getStudenterId());
        for (CourseStudy course : courses) {
            // 处理标签
            double accuracy = mapper.getCourseAccuracy(request.getStudenterId(),course.getCourseId());
            if(accuracy<0.6){
                course.setLevelLabel("努力学习 ");
            }else if(accuracy<0.9){
                course.setLevelLabel("有待提高");
            }else if(accuracy<0.99){
                course.setLevelLabel("学霸");
            }else{
                course.setLevelLabel("学神");
            }

        }
        return courses;
    }

    /**
     * 学生荣耀得分
     * @param request
     * @return
     */
    @Override
    public GloryScore gloryScore(RequestDTO request) {
        GloryScore gloryScore = new GloryScore();
        // 获取所有学习的知识点
        gloryScore.setFinishKnow(mapper.queryStudentAllKnows(request));
        gloryScore.setNumberOfQuestions(mapper.getQuestions(request.getStudenterId()));
        // 正确率四舍五入保留两位小数
        gloryScore.setAccuracy(DecimalUtils.div(mapper.getRightQuestion(request.getStudenterId()),gloryScore.getNumberOfQuestions(),2));
        gloryScore.setStudyLength(DecimalUtils.div(mapper.getStudyLength(request),mapper.getStudyDays(request).size()).intValue());
        gloryScore.setRiseFinishKnow(mapper.getRiseKnow(request));
        gloryScore.setRiseStudyLength(mapper.getRiseStudyLength(request));
        // 获取当天的正确率
        double nowAccuracy = mapper.getDateAccuracy(request.getStudenterId(),0);
        // 获取昨天的正确率
        double lastAccuracy = mapper.getDateAccuracy(request.getStudenterId(),1);
        // 正确率四舍五入保留两位小数
        gloryScore.setRiseAccuracy(DecimalUtils.round(nowAccuracy-lastAccuracy,2));
        return gloryScore;
    }

    /**
     * 学生当前课程的错题库
     * @param request
     * @return
     */
    @Override
    public List<ResultWrongQuestion> wrongQuestion(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        String studenterId = request.getStudenterId();
        // 获取该学生当前课程的错题
        List<Integer> ids = mapper.getWrongQuestionId(request);
        List<ResultWrongQuestion> results = new ArrayList<>();
        if(ids.isEmpty()){
            return results;
        }
        for (Integer id : ids) {
            results.add(SwitchQuestionUtils.switchWrongQuestion(mapper.getWrongQuestion(id,studenterId)));
        }
        return results;
    }

    /**
     * 家长先学后教
     * @param request
     * @return
     */
    @Override
    public KnowContent parentLearn(RequestDTO request) {
        return mapper.queryKnow(request.getKnowId());
    }

    /**
     * 获取课程先教后学
     * @param request
     * @return
     */
    @Override
    public CourseInfo CourseInfo(RequestDTO request) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseId(request.getCourseId());
        courseInfo.setCourseName(mapper.getCourseInfo(request));
        // 获取当前课程完成的知识点数量
        courseInfo.setFinishKnows(mapper.getFinishKnow(request.getCourseId(),request.getStudenterId()));
        // 获取当前课程的学习时长
        courseInfo.setStudyLength(mapper.getNowStudyLength(request));
        // 获取完成的题目数量
        courseInfo.setFinishQuestions(mapper.getCourseQuestions(request));
        // 获取当前题目的错误数量
        courseInfo.setWrongQuestions(mapper.getCourseWrongQuestions(request));
        // 获取今天学习的知识点
        courseInfo.setKnows(mapper.nowStudyKnow(request));
        return courseInfo;
    }

    /**
     * 获取学生所有课程
     * @param request
     * @return
     */
    @Override
    public List<CoursePojo> allCourse(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        if(request.getIsHot()!=0){
            // 按热度排序
            return mapper.getHostCourse(request);
        }
        return mapper.getCoursePOJO(request);
    }

    /**
     * 家长收藏当前课程
     * @param request
     * @return
     */
    @Override
    public int collectCourse(RequestDTO request) {
        return mapper.collectCourse(request);
    }

    /**
     * 家长取消收藏当前接口
     * @param request
     * @return
     */
    @Override
    public int cancelCollect(RequestDTO request) {
        return mapper.cancelCollect(request
        );
    }

    /**
     * 家长浏览当前课程章节
     * @param request
     * @return
     */
    @Override
    public List<chapterInfo> chapterInfo(RequestDTO request) {
        // 将当前课程计入浏览历史
        mapper.insertViews(request);
        return mapper.queryChapterInfo(request);
    }

    @Override
    public List<CoursePojo> searchCourse(RequestDTO request) {
        request.setName("%"+request.getName()+"%");
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        return mapper.searchCourse(request);
    }

    /**
     * 当前课程历史学习
     * @param request
     * @return
     */
    @Override
    public CourseInfo historyStudy(RequestDTO request) {
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setCourseId(request.getCourseId());
        courseInfo.setCourseName(mapper.getCourseInfo(request));
        // 获取当前课程完成的知识点数量
        courseInfo.setFinishKnows(mapper.getFinishKnow(request.getCourseId(),request.getStudenterId()));
        // 获取当前课程的学习时长
        courseInfo.setStudyLength(mapper.historyStudyLength(request));
        // 获取完成的题目数量
        courseInfo.setFinishQuestions(mapper.getCourseQuestions(request));
        // 获取当前题目的错误数量
        courseInfo.setWrongQuestions(mapper.getCourseWrongQuestions(request));
        // 获取该课程知识点学习情况
        courseInfo.setKnows(mapper.allStudyKnow(request));
        return courseInfo;
    }


    /**
     * 获取家长端app各平台下载地址
     * @param request
     * @return
     */
    @Override
    public List<HashMap> getDownUrl(RequestDTO request) {
        List<HashMap> results = new ArrayList<>();
        List<ApplicationVersion> downUrl = mapper.getDownUrl();
        if(downUrl.isEmpty()){
            // 集合为空
            return null;
        }
        for (ApplicationVersion applicationVersion : downUrl) {
            HashMap<String, String> map = new HashMap<>();
            map.put("appType",applicationVersion.getAppType());
            map.put("downUrl",PREFIX+applicationVersion.getDownUrl());
            map.put("size",applicationVersion.getSize());
            results.add(map);
        }
        return results;
    }

    @Override
    public List<BannerInfo> banner(RequestDTO request) {
        return mapper.getBanner(request);
    }

    /**
     * 获取课程详细信息
     * @param request
     * @return
     */
    @Override
    public CoursePojo getCourseInfo(RequestDTO request) {
        return mapper.CourseInfo(request);
    }
}
