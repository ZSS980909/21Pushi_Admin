package com.ershiyi.mapper;

import com.ershiyi.domain.Course_Select;
import com.ershiyi.domain.HomePageSearch;
import com.ershiyi.domain.PlanCourse;
import com.ershiyi.domain.entity.*;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface HomePageSearchMapper extends AbstractMapper<HomePageSearch> {
    /**
     * 首页搜索查询
     * @param search
     * @return
     */
    List<A_KnowContent> searchKnowledge(A_KnowContent search);

    List<PlanCourse> waitCourse(HomePageSearch search);

    HomePageSearch SearchCourse(String courseid);

    List<Course_Select> knowledgeByCourse(HomePageSearch search);

    Integer knowledgeByBus(String courseid,String studenterId);

    List<Search> searchcourse(Search search);

    List<Search> searchKnowledgeOne(Search search);

    List<Search> searchQuestion(Search search);

    CoursePojo searchContext(Search search);

    SearchByKnowledge searchKnowledgedetails(Search search);

    QuestionChoice searchQuestiondetailsBychoice(Search search);

    QuestionJudge searchQuestiondetailsByjudge(Search search);

    QuestionChoice searchQuestiondetailsBymulti(Search search);

    ResultQuestion searchQuestiondetailsBycompletion(Search search);
}
