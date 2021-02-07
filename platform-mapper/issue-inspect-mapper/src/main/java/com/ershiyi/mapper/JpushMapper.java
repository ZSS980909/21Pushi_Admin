package com.ershiyi.mapper;

import com.ershiyi.domain.Common_Choice;
import com.ershiyi.domain.entity.A_KnowContent;
import com.ershiyi.domain.entity.Correct;
import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.domain.entity.ResultQuestion;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.dto.QuestionAndKnowledge;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

@Repository
public interface JpushMapper extends AbstractMapper<JpushPojo> {
    String selectbyParentId(JpushPojo jpush);

    String selectbyStudenterId(JpushPojo jpush);

    /**
     * --                (select knowledgeName from common_course_knowledge_content where id =#{}) as knowledgeName,
     --              (select knowledgetext from common_course_knowledge_content where id =#{}) as knowledgetext
     * @param question
     * @return
     */
    List<QuestionChoice> questionjpush(QuestionAndKnowledge question);
}
