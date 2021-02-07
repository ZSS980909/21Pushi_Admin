package com.ershiyi.mapper;

import com.ershiyi.domain.Common_Choice;
import com.ershiyi.domain.Common_Judge;
import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.dto.ExamDTO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.AbstractMapper;

import java.util.ArrayList;
import java.util.List;

public interface ExamMapper extends AbstractMapper<ExamDTO> {
    void randomExam(ExamDTO examdto);

    List<ExamDTO> SchapterByKnowledge(List<ExamDTO> examdto);

    List<ExamDTO> SknowledgeByContentId(List listknowledge);

    List SchoiceByknowledge(@Param("list") List ids,@Param("number") Integer  number);

    List SjudgeByKnowledge(List list);

    Integer insertQuestion(List<Common_Choice> list1);

    Common_Choice SchoiceByknowledgelimit1(@Param("knowledge")String knowledge);

    Common_Judge SjudgeByKnowledgelimit1(@Param("knowledge")String knowledge);

    List SelectQuestionId(@Param("list")List listRandombyChoice);
}
