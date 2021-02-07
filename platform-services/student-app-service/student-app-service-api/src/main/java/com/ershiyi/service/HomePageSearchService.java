package com.ershiyi.service;

import com.ershiyi.domain.Course_Select;
import com.ershiyi.domain.HomePageSearch;
import com.ershiyi.domain.entity.*;

import java.util.List;

public interface HomePageSearchService extends BaseService<HomePageSearch>  {

    public List<A_KnowContent> searchKnowledge(A_KnowContent search);

    public List<HomePageSearch> waitCourse(HomePageSearch search);

    public List<Course_Select> knowledgeByCourse(HomePageSearch search);

    public List<Search> search(Search search);

    public CoursePojo searchContext(Search search);

    public SearchByKnowledge searchKnowledgedetails(Search search);

    public ResultQuestion searchQuestiondetails(Search search);
}
