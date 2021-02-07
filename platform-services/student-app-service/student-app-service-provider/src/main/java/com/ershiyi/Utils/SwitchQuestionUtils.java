package com.ershiyi.Utils;

import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.domain.entity.QuestionJudge;
import com.ershiyi.domain.entity.ResultQuestion;

import java.lang.reflect.Field;
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

    private static List results = new ArrayList();

    /**
     *  最基本类型转换
     * @param question
     * @return
     */
    public static ResultQuestion judgeQuestion(Object question){
        ResultQuestion result = new ResultQuestion();
        if(question instanceof QuestionChoice){
            QuestionChoice choice =(QuestionChoice) question;
            result.setResolving(choice.getResolving());
            result.setType(choice.getType());
            result.setQuestion(choice.getQuestion());
            result.setQuestionId(choice.getQuestionId());
            result.setCorrectOption(choice.getCorrectOption());
            result.setKnowName(choice.getKnowName());
            result.setKnowId(choice.getKnowId());
        }else{
            QuestionJudge judge = (QuestionJudge) question;
            result.setResolving(judge.getResolving());
            result.setType(judge.getType());
            result.setQuestion(judge.getQuestion());
            result.setQuestionId(judge.getQuestionId());
            result.setCorrectOption(judge.getCorrectOption());
            result.setKnowName(judge.getKnowName());
            result.setKnowId(judge.getKnowId());
        }
        return result;
    }

    /**
     * 判断题集合转换
     * @param questions
     * @return
     */
    public static List<ResultQuestion> judgeQuestion(List<QuestionJudge> questions){
        results.removeAll(results);
        for (QuestionJudge question : questions) {
            results.add(judgeQuestion(questions));
        }
        return results;
    }

    /**
     * 选择题集合转换
     * @param questions
     * @return
     */
    public static List<ResultQuestion> choiceQuestion(List<QuestionChoice> questions){
        results.removeAll(results);
        for (QuestionChoice question : questions) {
            ResultQuestion option = judgeQuestion(question);
            option.setOptions(Arrays.asList("A."+question.getOptionA(),"B."+question.getOptionB(),"C."+question.getOptionC(),"D."+question.getOptionD()));
            results.add(option);
        }
        return results;
    }

    /**
     * 选择题转换
     * @param question
     * @return
     */
    public static ResultQuestion choiceQuestion(QuestionChoice question){
        ResultQuestion option = judgeQuestion(question);
        option.setOptions(Arrays.asList("A."+question.getOptionA(),"B."+question.getOptionB(),"C."+question.getOptionC(),"D."+question.getOptionD()));
        return option;
    }

    public static void main(String[] args) {
        QuestionChoice question = new QuestionChoice();
        question.setQuestion("sadadad");
        question.setQuestionId(15);
        question.setKnowName("hello");
        System.out.println(judgeQuestion(question));
    }
}
