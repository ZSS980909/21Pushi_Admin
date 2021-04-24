package com.ershiyi.service.impl;

import com.ershiyi.Utils.DateUtils;
import com.ershiyi.Utils.SwitchQuestionUtils;
import com.ershiyi.domain.Chapter;
import com.ershiyi.domain.*;
import com.ershiyi.domain.Collect_Course;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.mapper.CourseMapper;
import com.ershiyi.service.CourseService;
import com.ershiyi.utils.TimeCompute;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper mapper;

    public static Log log = LogFactory.getLog(CourseServiceImpl.class);

    /**
     * 添加课程  搜索课程
     * @param request
     * @return
     */
    @Override
    public PageInfo<CoursePojo> Obscure(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        request.setCourseName("%"+request.getCourseName()+"%");
        List<Integer> courseIds =mapper.Obscure(request.getCourseName());
        List<CoursePojo> courses = new ArrayList<>();
        for (Integer courseId : courseIds) {
            courses.add(mapper.searchCourseInfo(courseId,request.getStudenterId()));
        }
        return new PageInfo<>(courses);
    }

    /**
     * RECOMMEND 推荐课堂
     * STUDYAID  助学课堂
     * NEWEST  最新课堂
     * HOT  热门课堂
     * @param billboard
     * @return
     */
    @Override
    public List<CoursePojo> billboard(Billboardtitle billboard) {
        List<CoursePojo> course = new ArrayList<>();
        if("RECOMMEND".equals(billboard.getBillboardkey())){
            List<Integer> ids = mapper.billboardByRECOMMEND(billboard);
            for (Integer id : ids) {
                course.add(mapper.searchCourse(id));
            }
            return course;
        }else if("STUDYAID".equals(billboard.getBillboardkey())){
            List<Integer> ids = mapper.billboardbillboardBySTUDYAID(billboard);
            for (Integer id : ids) {
                course.add(mapper.searchCourse(id));
            }
            return course;
        }else if("NEWEST".equals(billboard.getBillboardkey())){
            List<Integer> ids = mapper.billboardbillboardByNEWEST(billboard);
            for (Integer id : ids) {
                course.add(mapper.searchCourse(id));
            }
            return course;
        }else if("HOT".equals(billboard.getBillboardkey())){
            List<Integer> ids = mapper.billboardbillboardByHOT();
            for (Integer id : ids) {
                course.add(mapper.searchCourse(id));
            }
            return course;
        }
        return  course;
    }

    @Override
    public List<Chapter> chapterById(Course course) {
        return mapper.chapterById(course);
    }

    @Override
    public Integer courseByBrowse(int courseId,String studentId) {
        return  mapper.courseByBrowse(courseId,studentId);
    }

    @Override
    public List<CoursePojo> courseByCompetitiv() {
        List<Integer> ids =  mapper.courseByCompetitiv();
        List<CoursePojo> courses = new ArrayList<>();
        for (Integer id : ids) {
            courses.add(mapper.searchCourse(id));
        }
        return courses;
    }

    @Override
    public <T> T courseByknowledge(Chapter chapter) {
        /**
         * 步骤一
         * 循环查出该知识点复习资料
         * 步骤二
         * 判断是否有文字说明资料
         * 步骤三
         * 判断是否有视频说明资料  --暂时先不做
         * 步骤四
         * 取出文字说明资料返回
         */
        //Knowledge knowledge=null;
//        String[] split = chapter.getKnowledgeid().split(",");
//        if(!chapter.getKnowledgeid().contains(",")){
//                knowledge = mapper.courseByknowledge(chapter.getKnowledgeid());
//        }else{
//            for(int i=0;i<split.length;i++){
//                 knowledge = mapper.courseByknowledge(split[i]);
//            }
//        }
        return null;
    }

    @Override
    public CoursePojo courseById(Course course) {
        return mapper.courseById(course.getCourseId());
    }

    @Override
    public int appraiseByDiscuss(Thumbs thumbs) {
        Integer result = mapper.appraiseByDiscuss(thumbs);
        return result;
    }

    @Override
    public Integer appraiseByDiscussStatus(Thumbs thumbs) {
        return mapper.appraiseByDiscussStatus(thumbs);
    }

    @Override
    public Integer courseByCollect(String studenterId, Integer courseId) {
        return mapper.courseByCollect(studenterId, courseId);
    }

    @Override
    public boolean courseByCollectIf(Collect_Course collect_course) {
        Collect_Course collect_course1 = mapper.courseByCollectIf(collect_course);
       if(collect_course1==null)
           return true;
       else
            return false;
    }

    @Override
    public List<Notice> courseByNotice(Notice notice) {
        return mapper.courseByNotice(notice);
    }

    @Override
    public List<Knowledge> courseByKnowledgeAll(Chapter chapter) {
        String[] split = chapter.getKnowId().split(",");
      //System.out.println(split);
        List list=new ArrayList();
        for(int i =0;i<split.length;i++){
            Knowledge knowledge = mapper.courseByKnowledgeAll(split[i]);
            list.add(knowledge);
        }
        return list;
    }

    @Override
    public <T> T courseByStudy(QuestionContent questioncontent) {
        /**
         * 1.获取该知识点随机出的题型
         *
         */
        return mapper.courseByStudy(questioncontent);
    }

    @Override
    public List<KnowledgeContent> courseByKnowledgeContent(Knowledge knowledge) {
        /**
         *获取知识点的学习资料 ----文字
         */
        return mapper.courseByKnowledgeContent(knowledge);
    }

    @Override
    public List<CoursePojo> searchByBusCourse(Common_Search search) {
        List<Integer> ids = mapper.searchByBusCourseIds(search);
        List<CoursePojo> courses = new ArrayList<>();
        for (Integer id : ids) {
            courses.add(mapper.searchCourse(id));
        }
        return courses;
    }

//    @Override
//    public Object selectBySyllabus(Common_Search search) {
//
//
//        return null;
//    }

    @Override
    public List<CoursePojo> selectCourseBySubject(String studenterId,Integer subjectId) {
        List<Integer> ids = mapper.selectCourseBySubject(studenterId, subjectId);
        List<CoursePojo> courses = new ArrayList<>();
        for (Integer id : ids) {
            CoursePojo coursePojo = mapper.searchCourse(id);
            // 查询当前课程是否已经收藏
            coursePojo.setIsCollect(!mapper.queryIsCollect(studenterId,id).isEmpty() ? 0:1);
            courses.add(coursePojo);
        }
        return courses;
    }

    @Override
    public List<LZMDType> LZMDKnowledge(Common_Search search) {
        //开启分页
        PageHelper.startPage(search.getPageNumber(),search.getPageSize());
        List<Common_Return> searchone =mapper.LZMDKnowledge(search); //1.查询已购买课程总共多少个知识点
        List<LZMDType> lzmdtype =new ArrayList<>();
            if(searchone.size()==0){
                return lzmdtype;
            }else{
            //List<LZMDType> lzmdtype =new ArrayList<>();
         //   List<Common_Return> searchone =mapper.LZMDKnowledge(search); //1.查询已购买课程总共多少个知识点
            List<Common_Return> searchtwo =mapper.CountKnowledge(search);//2.查询每个已购买的课程已完成多少个知识点
            if(searchone.size()==0){
                /**
                 * 没有已经购买的课程
                 */
                return null;
            }
            /**
             * 计算该学生的已购买课程的所有知识点个数
             */
            if(searchtwo.size()!=0){
                for(int i=0;i<searchone.size();i++){
                    LZMDType lzmd =new LZMDType();
                    for(int j=0;j<searchtwo.size();j++){
                        if (searchone.get(i).getCourseId().equals(searchtwo.get(j).getCourseId())){
                            lzmd.setSubjectId(searchone.get(i).getSubjectId());
                            lzmd.setPicture(searchone.get(i).getPicture());
                            lzmd.setKnowledgeCountNumber(searchone.get(i).getKnowIdNumber());
                            lzmd.setCourseId(searchone.get(i).getCourseId());
                            // lzmd.setCountknowledgeId(listQC.toString());
                            lzmd.setKnowledgeStudyNumber(searchtwo.get(j).getKnowIdNumber());
                            String baifenbi="";//接收百分比值
                            double studytime =Integer.parseInt(searchtwo.get(j).getKnowIdNumber())*1.0;
                            double counttime =Integer.parseInt(searchone.get(i).getKnowIdNumber())*1.0;
                            double fen = studytime / counttime;
                            DecimalFormat df1 = new DecimalFormat("0.##");
                            baifenbi = df1.format(fen);
                            lzmd.setPercentage(baifenbi);
                            lzmd.setCurriculum(searchone.get(i).getCourseName());
                            lzmd.setSubjectId(searchone.get(i).getSubjectId());
                            lzmdtype.add(lzmd);
                        }
                        if(j==searchtwo.size()-1){
                            if(lzmd.getCourseId()==null){
                                lzmd.setSubjectId(searchone.get(i).getSubjectId());
                                lzmd.setPicture(searchone.get(i).getPicture());
                                lzmd.setKnowledgeCountNumber(searchone.get(i).getKnowIdNumber());
                                lzmd.setCourseId(searchone.get(i).getCourseId());
                                // lzmd.setCountknowledgeId(listQC.toString());
                                lzmd.setKnowledgeStudyNumber("0");
                                String baifenbi="";//接收百分比值
                                double studytime =0*1.0;
                                double counttime =Integer.parseInt(searchone.get(i).getKnowIdNumber())*1.0;
                                double fen = studytime / counttime;
                                DecimalFormat df1 = new DecimalFormat("0.##");
                                baifenbi = df1.format(fen);
                                lzmd.setPercentage(baifenbi);
                                lzmd.setCurriculum(searchone.get(i).getCourseName());
                                lzmd.setSubjectId(searchone.get(i).getSubjectId());
                                lzmdtype.add(lzmd);
                            }
                        }
                    }

                }
            }else{
                for(int i=0;i<searchone.size();i++){
                    LZMDType lzmd =new LZMDType();
                    lzmd.setSubjectId(searchone.get(i).getSubjectId());
                    lzmd.setPicture(searchone.get(i).getPicture());
                    lzmd.setKnowledgeCountNumber(searchone.get(i).getKnowIdNumber());
                    lzmd.setCourseId(searchone.get(i).getCourseId());
                    // lzmd.setCountknowledgeId(listQC.toString());
                    lzmd.setKnowledgeStudyNumber("0");
                    String baifenbi="";//接收百分比值
                    double studytime =0*1.0;
                    double counttime =Integer.parseInt(searchone.get(i).getKnowIdNumber())*1.0;
                    double fen = studytime / counttime;
                    DecimalFormat df1 = new DecimalFormat("0.##");
                    baifenbi = df1.format(fen);
                    lzmd.setPercentage(baifenbi);
                    lzmd.setCurriculum(searchone.get(i).getCourseName());
                    lzmd.setSubjectId(searchone.get(i).getSubjectId());
                    lzmdtype.add(lzmd);
                }
            }
        return lzmdtype;
            }
    }

    @Override
    public List LZMDknowledgeByQuestion(LZMDType lzmdtype) {
        /**
         *        临阵磨刀出题
         *     1.查询该课程所有的已经做过的知识点信息
         *     2.查询该课程所有的知识点信息
         *     3.对比出没有学习过的知识点,然后去拿2道题目  题型目前先不做考虑
         *     4.添加到数组返回
         */
         List knowledgemessage = mapper.knowledgeMessage(lzmdtype.getCourseId());//该课程所有知识
         List knowledgestudymessage =mapper.knowledgeStudyMessage(lzmdtype);//该课程该学生已学知识点
         List Clist =new ArrayList(); //题目集合 题目数量达到20道题就停止
        List  nostudymessage=new ArrayList();  //未学习的知识点
        HashSet hs1 = new HashSet(knowledgemessage);
        HashSet hs2 = new HashSet(knowledgestudymessage);
        hs1.removeAll(hs2);
        nostudymessage.addAll(hs1);
        for(int i=0;i<nostudymessage.size();i++){
            if(Clist.size()==20){
                return Clist;
            }
           List list =mapper.toquestion(lzmdtype.getCourseId(),nostudymessage.get(i).toString()); //题目id
            if(list.size()==0){
                continue;
            }
            for(int j=0;j<list.size();j++){
                Common_Choice common_choice =mapper.SelectQuestionBylimit(list.get(j).toString());
                if(common_choice!=null){
                    common_choice.setType(1);
                    List Choicelist =new ArrayList();
                    Choicelist.add("A."+common_choice.getOptionA());
                    Choicelist.add("B."+common_choice.getOptionB());
                    Choicelist.add("C."+common_choice.getOptionC());
                    Choicelist.add("D."+common_choice.getOptionD());
                    common_choice.setOptions(Choicelist);
                    Clist.add(common_choice);
                }else{
                  log.info("未找到相关题目");
                }
            }

        }
        return Clist;
    }

    @Override
    public Object LZMDQuestionBySubmit(Map<String, List<Common_StudyrateBy>> list) {
       // log.info(list);
        /**
         * 1.先确定多少题目
         * 2.判断题目答题时间是否属于正常范围  0-3s属于不认真答题,不记录题目,3-120属于正常,120-600以后属于超时,加入疑难题,600以后属于不认真答题,不记录题目
         * 3.根据答题时间,题目内容分别插入正常答题表,错误答题表,疑难答题表
         * 3.错题插入错题库
         * 4.正确题目插入  common_lzmd_studyrate
         */
        List<Common_StudyrateBy> common_studyrateBIES = list.get("data");
        for(int i=0;i<common_studyrateBIES.size();i++){
                log.info(common_studyrateBIES.get(i).getQuestionId());
                Long day = TimeCompute.getDay(common_studyrateBIES.get(i).getStartdt(), common_studyrateBIES.get(i).getEnddt());
                log.info("使用时间为"+day);
                common_studyrateBIES.get(i).setUserdt(day.toString());
                //if(day>=0&day<=2){
                            //0-3s属于不认真答题,先不做处理
              //  if(day>=0&day<=120) {
                    //3-120s属于正常,记录答题表
                    if(common_studyrateBIES.get(i).getAnswer().equals(common_studyrateBIES.get(i).getFillAnswer())){
                        //正确
                        common_studyrateBIES.get(i).setIsright(1);
                    }else{
                        //错误
                        common_studyrateBIES.get(i).setIsright(0);
                    }
                    String knowId = common_studyrateBIES.get(i).getKnowId();
                    if(knowId.contains(",")){
                        String[] split = knowId.split(",");
                        if("".equals(split[0])){
                            String substring = knowId.substring(1);
                            common_studyrateBIES.get(i).setKnowId(substring);
                        }
                    }

                    mapper.ZSubmit(common_studyrateBIES.get(i));
                  if(day>=120&day<=600) {
                    //120-600s属于疑难题,加入难题表
                    mapper.NSubmit(common_studyrateBIES.get(i));
                }
// else{
//                    //600属于不认真答题,先不做处理
//                }

        }
        return null;
    }

    /**
     * 查询学生当前科目下未购买的课程
     * @param subjectId 学科id
     * @param pageNumber 页码
     * @param pageSize 每页展示的数量
     * @return
     */
    @Override
    public PageInfo<CoursePojo> courseForSubject(String studentId,Integer subjectId, Integer pageNumber, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);

        // 查询学生当前科目下所有未购买的课程
        List<Integer> courseIds =null;
        if(subjectId==0){
            courseIds = mapper.courseForSubjectone(studentId);
        }else{
             courseIds = mapper.courseForSubject(studentId,subjectId);
        }
        // 根据课程id查询出课程信息
        List<CoursePojo> courses = new ArrayList<>();
        for (Integer courseId : courseIds) {
            CoursePojo coursePojo = mapper.courseById(courseId);
            // 查询当前课程是否已经收藏
            coursePojo.setIsCollect(mapper.queryIsCollect(studentId,courseId).isEmpty() ? 0:1);
            courses.add(coursePojo);
        }
        return new PageInfo<>(courses);
    }

    /**
     * 查询作者名下的其他课程
     * @param creatorId 作者id
     * @param courseId 课程id
     * @param pageNumber 页码
     * @param pageSize 每页展示的数量
     * @return
     */
    @Override
    public PageInfo<CoursePojo> authorAbout(Integer creatorId, Integer courseId, Integer pageNumber, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(pageNumber,pageSize);
        List<Integer> courseIds = mapper.queryAboutCourse(creatorId,courseId);
        List<CoursePojo> courses = new ArrayList<>();
        // 查询出当前作者下的所有id 不包括当前课程
        courseIds.forEach(id -> courses.add(mapper.courseById(id)) );
        return new PageInfo<>(courses);
    }

    /**
     * 学生发起请求购买当前课程
     * @param courseId 课程id
     * @param studentId 学生编号
     * @return
     */
    @Override
    // 当前注解表示发生错误数据就回滚
    @Transactional(rollbackFor = Exception.class)
    public Integer buyCourse(Integer courseId, String studentId) {
        int result = 0;
        // 先查询出学生积分
        Integer studentIntegral = mapper.queryStudentIntegral(studentId);
        if(studentIntegral==null){
            studentIntegral = 0;
        }
        // 查询出课程所需的积分
        Integer courseIntegral = mapper.courseById(courseId).getIntegral();
        if(courseIntegral==null){
            courseIntegral = 0;
        }
        // 判断当前课程是否已经购买
        // 得到当前的时间
        if(!mapper.queryCourseExists(studentId,courseId).isEmpty()){
            // 当前课程已经购买
            return  result = 203;
        }
        if(studentIntegral<courseIntegral){
            // 学生积分不足以购买当前课程
            return  result = 201;
        }else{
            // 学生积分足够购买课程
            result = mapper.coursePaySuccess(studentId, courseIntegral);
            if(result!=0){
                // 插入成功
                result = 200;
            }else{
                result = 202;
            }
        }
        // 将购买的课程插入到表中
        mapper.insertCoursePay(studentId,courseId,courseIntegral,result==200 ? 1:0);
        // 将购买成功的课程插入到计划表中
        if(result==200){
            mapper.insertCoursePlan(courseId, studentId);
            // 将学生购买情况插入到学生消费表中
            mapper.insertRecord(studentId,courseId,courseIntegral*-1,studentIntegral,(studentIntegral-courseIntegral));
        }
        return result;
    }

    /**
     * 查询学生当前日期的课程安排
     * @param studenterId 学生编号
     * @param date 日期
     * @return
     */
    @Override
    public List<CoursePlan> queryStudyPlan(String studenterId, String date) {
        // 获取当前时间的星期
        String week = DateUtils.getWeekDay(date);
        // 根据当前星期和时间查询符合条件的课程id
        List<Integer> courseIds = mapper.queryStudyPlan(studenterId,week);
        ArrayList<CoursePlan> results = new ArrayList<>();
        for (Integer courseId : courseIds) {
            // 设置上课时间
            CoursePlan course = mapper.queryCourseForWeek(studenterId, courseId, week);
            String planTime = course.getPlanTime();
            if(planTime!=null&&planTime!=""){
                course.setPlanType(1);
                course.setPlanTime(planTime + "-" + DateUtils.getAddHour(planTime ,"HH:mm", 1));
            }
            results.add(course);
        }
        return results;
    }

    /**
     * 根据科目来查询错误题目
     * @param request
     * @return
     */
    @Override
    public PageInfo<ResultWrongQuestion> wrongQuestion(RequestDTO request) {
        // 开启分页
        PageHelper.startPage(request.getPageNumber(),request.getPageSize());
        List<Integer> ids = mapper.getWrongQuestionId(request);
        List<ResultWrongQuestion> results = new ArrayList<>();
        if(ids.isEmpty()){
            return new PageInfo<>(new ArrayList<>());
        }
        for (Integer id : ids) {
            WrongQuestionChoice choice = mapper.queryChoiceQuestion(id);
            results.add(SwitchQuestionUtils.switchWrongQuestion(choice));
        }
        return new PageInfo<>(results);
    }

    /**
     * 首页课程推荐
     * @param request
     * @return
     */
    @Override
    public List<CoursePojo> courseRecommend(RequestDTO request) {
        // 得到所有的推荐课程id
        List<Integer> ids = mapper.queryRecommendCourse(request.getStudenterId(),mapper.querySystemSettingSize(request.getKey()));
        List<CoursePojo> results = new ArrayList<>();
        for (Integer id : ids) {
            results.add(mapper.searchCourse(id));
        }
        return results;
    }

    @Override
    public List<Function_setting> Querytitle() {
        return  mapper.Querytitle();
    }

}
