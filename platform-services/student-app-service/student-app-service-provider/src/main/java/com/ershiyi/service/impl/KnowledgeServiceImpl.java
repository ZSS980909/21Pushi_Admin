package com.ershiyi.service.impl;

import com.ershiyi.Utils.IdsUtils;
import com.ershiyi.Utils.StringReplaceUtil;
import com.ershiyi.Utils.SwitchQuestionUtils;
import com.ershiyi.domain.entity.*;
import com.ershiyi.dto.RequestDTO;
import com.ershiyi.mapper.KnowledgeMapper;
import com.ershiyi.service.KnowledgeService;
import io.swagger.models.auth.In;
import org.bouncycastle.cert.ocsp.Req;
import org.bouncycastle.pqc.crypto.gmss.Treehash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 知识点配对服务层实现类
 * @author: zss98
 * @date: 2020-09-18 15:20
 * @version: 1.0
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeMapper mapper;

    /**
     * 获取课程列表信息
     * @return
     */
    @Override
    public List<CoursePojo> courseList(RequestDTO request) {
        return mapper.courseList(request);
    }


    /**
     * 获取当前科目下未完成匹配的科目
     * @param request
     * @return
     */
    @Override
    public ResultQuestion getQuestion(RequestDTO request) {
        ResultQuestion result = new ResultQuestion();
        // 目前只做返回单选题
        QuestionChoice choiceQuestion = mapper.getChoiceQuestion(request);
        // 将题目转换成通用的题目类
        if(choiceQuestion!=null){
            result = SwitchQuestionUtils.switchQuestion(choiceQuestion);
        }
        return result;
    }


    /**
     * 获取知识点信息列表
     * @param request
     * @return
     */
    @Override
    public List<KnowContent> getKnowList(RequestDTO request) {
        // 获取当前id下所有的知识点
        String knowContentId = mapper.getKnowContentId(request.getKnowId());
        if(knowContentId==null){
            return new ArrayList<>();
        }
        List<String> ids = IdsUtils.getListString(knowContentId);
        // 根据节点id查询所有的知识点信息
        return mapper.getKnowList(ids);
    }

    /**
     * 提交学生的关联结果并判断是否插入了30行
     * @param request
     * @return
     */
    @Override
    public Integer submitRelation(RequestDTO request) {
        // 将学生关联的结果插入到表内
        Integer result = mapper.insertRelation(request);
        List<String> list = mapper.getRelationInfo(request);
        // 插入成功，判断当前题目插入的数量是否达到了30
        if(list.size()>=3){
            // 获取所有的题目关联到的所有知识点
            List<List<String>> knowList = new ArrayList<>();
            list.forEach(str -> {
                List<String> listString = IdsUtils.getListString(str);
                Collections.sort(listString);
                knowList.add(listString);
            });
            // 将出现频率最高的一组关联知识点
            HashMap<List<String>,Integer> map = new HashMap<>();
            for (List<String> strings : knowList) {
                if(map.get(strings)!=null){
                    map.put(strings,map.get(strings)+1);
                }else {
                    map.put(strings,1);
                }
            }
            List<Integer> values = new ArrayList<>(map.values());
            // 获取最大值的索引
            int maxValue = Collections.max(values);
            // 判断集合中是否存在多个最大值,存在就继续出题
            int count = 0;
            for (Integer value : values) {
                if(value==maxValue){
                    count++;
                }
            }
            if(count>1){
                // 代表存在不止一个最大值，继续出题
                return 1;
            }
            // 根据得到的最大值获取键
            List<String> knowIds = getMaxKey(map,maxValue);
            // 根据知识点内容id获取知识点id
            // 去掉前后的括号就是知识点集合
            // 将关联的知识点id插入到题目表
            mapper.insertQuestionRelated(request.getQuestionId(),knowIds,request.getType());
            // 修改题目为已关联
            mapper.modifyQuestionStatus(request.getQuestionId());
        }
        return result;
    }

    /**
     * 用户刷新题目
     * @param request
     * @return
     */
    @Override
    public Integer restQuestion(RequestDTO request) {
        int result =  mapper.insertQuestionStatus(request);
        int errorNumber= mapper.getErrorQuestionNumber(request);
        if(errorNumber>30){
            // 大多数人都觉得题目存在问题，则数据库将题目状态改为删除
            // 判断题目类型
            if(request.getType()==1){
                // 当前题目是单选题
                mapper.modifyQuestionChoice(request);
            }else if(request.getType()==2){
                // 当前题目是多选题
                mapper.modifyQuestionMulti(request);
            }else if(request.getType()==3){
                // 当前题目是判断题
                mapper.modifyQuestionJudge(request);
            }
        }
        return result;
    }


    /**
     * 根据获取到的最大值获取键
     * @param map
     * @param maxValue
     * @return
     */
    public static List<String> getMaxKey(HashMap<List<String>,Integer> map,Integer maxValue){
        for (Map.Entry<List<String>, Integer> entry : map.entrySet()) {
            if (entry.getValue().equals(maxValue)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
