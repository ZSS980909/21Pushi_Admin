package com.ershiyi.Utils;

import com.ershiyi.entity.ResultWrongQuestion;
import com.ershiyi.entity.WrongQuestionChoice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 题目转换接口
 * @author: zss98
 * @date: 2021-02-07 10:12
 * @version: 1.0
 */
public class SwitchQuestionUtils {

    /**
     * 错题转换
     * @param question
     * @return
     */
    public static ResultWrongQuestion switchWrongQuestion(WrongQuestionChoice question){
        ResultWrongQuestion result = new ResultWrongQuestion();
        result.setQuestionId(question.getQuestionId());
        result.setType(question.getType());
        result.setQuestion(question.getQuestion());
        result.setQuestionId(question.getQuestionId());
        result.setResolving(question.getResolving());
        result.setCorrectOption(question.getCorrectOption());
        result.setKnowId(question.getKnowId());
        result.setKnowName(question.getKnowName());
        result.setFillAnswer(question.getFillAnswer());
        result.setStudyTime(question.getStudyTime());
        result.setCourseName(question.getCourseName());
        result.setSubjectId(question.getSubjectId());
        result.setSubjectImgUrl(question.getSubjectImgUrl());
        if(question.getType()==1||question.getType()==2){
            result.setOptions(Arrays.asList("A."+question.getOptionA(),"B."+question.getOptionB(),"C."+question.getOptionC(),"D."+question.getOptionD()));
        }
        return result;
    }

    /**
     * 错题转换-list
     * @param questions
     * @return
     */
    public static List<ResultWrongQuestion> switchWrongQuestion(List<WrongQuestionChoice> questions){
        List<ResultWrongQuestion> results = new ArrayList<>();
        for (WrongQuestionChoice question : questions) {
            results.add(switchWrongQuestion(question));
        }
        return results;
    }
}
