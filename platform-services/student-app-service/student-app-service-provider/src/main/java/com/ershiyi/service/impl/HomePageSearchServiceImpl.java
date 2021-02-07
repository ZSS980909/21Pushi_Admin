package com.ershiyi.service.impl;

import com.ershiyi.domain.Course_Select;
import com.ershiyi.domain.HomePageSearch;
import com.ershiyi.domain.PlanCourse;
import com.ershiyi.domain.entity.*;
import com.ershiyi.mapper.HomePageSearchMapper;
import com.ershiyi.service.HomePageSearchService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HomePageSearchServiceImpl extends BaseServiceImpl<HomePageSearch, HomePageSearchMapper> implements HomePageSearchService {

    @Override
   // @Cacheable(cacheNames = "Search_Course", key = "#search.studenterId")
    public List<A_KnowContent> searchKnowledge(A_KnowContent search) {
        String content = search.getContent();
        search.setContent("%"+content+"%");
        return mapper.searchKnowledge(search);
    }

    @Override
    public  List<HomePageSearch> waitCourse(HomePageSearch search) {
        /**
         * 获取当天时间
         */
        Date sday = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sday);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        Date s = calendar.getTime();
        Date eday = new Date();
        calendar.setTime(eday);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date e = calendar.getTime();
        SimpleDateFormat fom =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String eformat = fom.format(e);
        String sformat = fom.format(s);
        System.out.println(eformat);
        System.out.println(sformat);
        search.setEndTime(eformat);
        search.setStartTime(sformat);
        List<PlanCourse> planCourse= mapper.waitCourse(search);
        List<HomePageSearch> ZHomePageSearch=new ArrayList<HomePageSearch>();
        for(int i=0;i<planCourse.size();i++){
            String courseId=planCourse.get(i).getCourseid();
            /**
             * 课程轮询
             */
            HomePageSearch homepagesearch=mapper.SearchCourse(courseId);
            homepagesearch.setPlandt(planCourse.get(i).getPlandt());
            homepagesearch.setSplandt(planCourse.get(i).getSplandt());
            ZHomePageSearch.add(homepagesearch);
        }
        System.out.println(ZHomePageSearch);
        return ZHomePageSearch;
    }

    @Override
    public List<Course_Select> knowledgeByCourse(HomePageSearch search) {
        List<Course_Select> knowledgebycourse = mapper.knowledgeByCourse(search);
        /**
         *  查询是否购买
         */
        for(int i=0;i<knowledgebycourse.size();i++){
                Integer  o=mapper.knowledgeByBus(knowledgebycourse.get(i).getCourseId(),search.getStudenterId());
                knowledgebycourse.get(i).setIsbuycourse(o.toString());
        }
        return knowledgebycourse;
    }

    @Override
    public List<Search> search(Search search) {
        /**
         * 查询
         * 先判断是查询课程还是知识点还是题目
         */
        PageHelper.startPage(search.getPageNumber(),search.getPageSize());
        List<Search> searchcourse =null;
        search.setKeyWord("%"+search.getKeyWord()+"%");
        if(1==search.getSearchType()){
            //课程
          searchcourse = mapper.searchcourse(search);
        }else if(2==search.getSearchType()){
            //知识点
             searchcourse =mapper.searchKnowledgeOne(search);
             for(int i=0;i<searchcourse.size();i++){
                 searchcourse.get(i).setConText(searchcourse.get(i).getConText().replace("<p>", "").replace("</p>",""));
             }
        }else if(3==search.getSearchType()){
            //题库
             searchcourse =mapper.searchQuestion(search);
             for (int i=0;i<searchcourse.size();i++){
                 searchcourse.get(i).setConText(searchcourse.get(i).getConText().replace("<sub>","").replace("<p>","").replace("</sub>",""));
             }
        }
        return searchcourse;
    }

    @Override
    public CoursePojo searchContext(Search search) {
        /**
         * 根据课程id查询课程详情
         */
        CoursePojo coursePojo = mapper.searchContext(search);
        return coursePojo;
    }

    @Override
    public SearchByKnowledge searchKnowledgedetails(Search search) {
        SearchByKnowledge searchByKnowledge = mapper.searchKnowledgedetails(search);
        searchByKnowledge.setKnowledgetext(searchByKnowledge.getKnowledgetext().
                replace("<p>","").replace("</p>",""));
        return searchByKnowledge;
    };


    @Override
    public ResultQuestion searchQuestiondetails(Search search) {
        ResultQuestion resultquestion=new ResultQuestion();
        if(1==search.getQuestionType()){
    //单选
            QuestionChoice questionchoice=mapper.searchQuestiondetailsBychoice(search);
            resultquestion.setCorrectOption(questionchoice.getCorrectOption());
            resultquestion.setQuestion(questionchoice.getQuestion());
            resultquestion.setResolving(questionchoice.getResolving());
            List list =new ArrayList();
            list.add("A."+questionchoice.getOptionA().trim());
            list.add("B."+questionchoice.getOptionB().trim());
            list.add("C."+questionchoice.getOptionC().trim());
            list.add("D."+questionchoice.getOptionD().trim());
            resultquestion.setOptions(list);
        }else if(2==search.getQuestionType()){
            QuestionChoice questionChoice = mapper.searchQuestiondetailsBymulti(search);
            resultquestion.setCorrectOption(questionChoice.getCorrectOption());
            resultquestion.setQuestion(questionChoice.getQuestion());
            resultquestion.setResolving(questionChoice.getResolving());
            List list =new ArrayList();
            list.add("A."+questionChoice.getOptionA().trim());
            list.add("B."+questionChoice.getOptionB().trim());
            list.add("C."+questionChoice.getOptionC().trim());
            list.add("D."+questionChoice.getOptionD().trim());
            resultquestion.setOptions(list);
            //多选
        }else if(3==search.getQuestionType()){
            QuestionJudge questionJudge = mapper.searchQuestiondetailsByjudge(search);
            resultquestion.setCorrectOption(questionJudge.getCorrectOption());
            resultquestion.setQuestion(questionJudge.getQuestion());
            resultquestion.setResolving(questionJudge.getResolving());
            //判断
        }else if(10==search.getQuestionType()){
    //填空
            return mapper.searchQuestiondetailsBycompletion(search);
        }
        return resultquestion;
    }
}
