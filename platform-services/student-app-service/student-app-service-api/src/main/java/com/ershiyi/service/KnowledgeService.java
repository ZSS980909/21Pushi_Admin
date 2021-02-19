package com.ershiyi.service;

import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;

import java.util.List;

/**
 * @Description: 知识点配对想关信息服务层接口
 * @author: zss98
 * @date: 2020-09-18 15:18
 * @version: 1.0
 */
public interface KnowledgeService {

    /**
     * 课程信息列表
     * @return
     */
    public List<CoursePojo> courseList();

    /**
     * 给当前学生分配一条当前节目下的题目
     * @param request
     * @return
     */
   public ResultQuestion getQuestion(RequestDTO request);


    /**
     * 获取知识点信息列表
     * @param request
     * @return
     */
   public List<KnowContent> getKnowList(RequestDTO request);


    /**
     * 学生提交关联结果
     * @param request
     * @return
     */
    Integer submitRelation(RequestDTO request);

    /**
     * 用户刷新题目
     * @param request
     * @return
     */
   Integer restQuestion(RequestDTO request);
}
