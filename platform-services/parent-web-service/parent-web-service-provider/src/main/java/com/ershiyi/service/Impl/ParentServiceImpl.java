package com.ershiyi.service.Impl;

import com.ershiyi.Utils.DecimalUtils;
import com.ershiyi.Utils.ParentUtils;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.entity.*;
import com.ershiyi.mapper.ParentMapper;
import com.ershiyi.service.ParentService;
import com.github.pagehelper.PageHelper;
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

    @Override
    public ParentInfo parentInfo(RequestDTO request) {
        return mapper.parentInfo(request);
    }

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
        gloryScore.setStudyLength((int)DecimalUtils.div(mapper.getStudyLength(request),mapper.getStudyDays(request).size()));
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
        // 获取该学生当前课程的错题
        List<QuestionType> ids = mapper.getWrongQuestion(request);
        List<String> choices = new ArrayList<>();
        List<String> multiples = new ArrayList<>();
        List<String> judges = new ArrayList<>();
        List<ResultWrongQuestion> results = new ArrayList<>();
        for (QuestionType id : ids) {
            if(id.getQuestionType()==1){
                // 单选题
                choices.add(id.getQuestionId());
            }else if(id.getQuestionType()==2){
                // 多选题
                multiples.add(id.getQuestionId());
            }else{
                // 判断题
                judges.add(id.getQuestionId());
            }
        } if(!choices.isEmpty()){
            results.addAll(QuestionSwitch(mapper.queryChoiceQuestion(choices)));
        }
        if(!multiples.isEmpty()){
            results.addAll(QuestionSwitch(mapper.queryMultipleQuestion(multiples)));
        }
        if(!judges.isEmpty()) {
            results.addAll(QuestionSwitch(mapper.queryJudgeQuestion(judges)));
        }
        return results;
    }

    /**
     * 家长先学后教
     * @param request
     * @return
     */
    @Override
    public List<KnowContent> parentLearn(RequestDTO request) {
        List<String> ids = ParentUtils.getListString(mapper.getKnowledgeIds(request));
        if(ids==null||ids.isEmpty()){
            return new ArrayList<>();
        }
        return mapper.queryKnow(ids);
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
        courseInfo.setStudyLength(mapper.getCourseStudyLength(request));
        // 获取完成的题目数量
        courseInfo.setFinishQuestions(mapper.getCourseQuestions(request));
        // 获取当前题目的错误数量
        courseInfo.setWrongQuestions(mapper.getCourseWrongQuestions(request));
        // 获取今天学习的知识点
        courseInfo.setKnows(mapper.getKnowInfo(request));
        return courseInfo;
    }

    /**
     * 学习情况折线图
     * @param request
     * @return
     */
    @Override
    public StudyData studyData(RequestDTO request) {
        StudyData data = new StudyData();
        data.setStudyData(mapper.studyData(request));
        data.setAllStudyLength(mapper.getCourseStudyLength(request));
        data.setNowStudyLength(mapper.getNowStudyLength(request));
        return data;
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
        courseInfo.setKnows(mapper.queryKnowStatus(request));
        return courseInfo;
    }

    /**
     * 选择题转换成返回类
     * @param lists
     * @return
     */
    public static List<ResultWrongQuestion> QuestionSwitch(ArrayList<QuestionWrongChoice> lists){
        List<ResultWrongQuestion> results = new ArrayList<>();
        for (QuestionWrongChoice list : lists) {
            ResultWrongQuestion question = new ResultWrongQuestion();
            question.setQuestion(list.getQuestion());
            question.setCourseName(list.getCourseName());
            question.setSubjectId(list.getSubjectId());
            question.setKnowId(list.getKnowId());
            question.setCorrectOption(list.getCorrectOption());
            question.setResolving(list.getResolving());
            question.setType(list.getType());
            question.setQuestionId(list.getQuestionId());
            List<String> options = new ArrayList<>();
            options.add("A."+list.getOptionA());
            options.add("B."+list.getOptionB());
            options.add("C."+list.getOptionC());
            options.add("D."+list.getOptionD());
            question.setFillAnswer(list.getFillAnswer());
            question.setKnowName(list.getKnowName());
            question.setStudyTime(list.getStudyTime());
            question.setOptions(options);
            results.add(question);
        }
        return results;
    }

    /**
     * 判断题转换成返回类
     * @param lists
     * @return
     */
    public static List<ResultWrongQuestion> QuestionSwitch(List<QuestionWrongJudge> lists){
        List<ResultWrongQuestion> results = new ArrayList<>();
        for (QuestionWrongJudge list : lists) {
            ResultWrongQuestion question = new ResultWrongQuestion();
            question.setCourseName(list.getCourseName());
            question.setSubjectId(list.getSubjectId());
            question.setQuestion(list.getQuestion());
            question.setKnowId(list.getKnowId());
            question.setCorrectOption(list.getCorrectOption());
            question.setResolving(list.getResolving());
            question.setType(list.getType());
            question.setQuestionId(list.getQuestionId());
            question.setFillAnswer(list.getFillAnswer());
            question.setKnowName(list.getKnowName());
            question.setStudyTime(list.getStudyTime());
            results.add(question);
        }
        return results;
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
