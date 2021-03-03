package com.ershiyi.service.impl;

import com.ershiyi.JobDemo.CronUtil;
import com.ershiyi.JobDemo.QuartzScheduler;
import com.ershiyi.dto.QuestionAndKnowledge;
import com.ershiyi.mapper.DateTimeJobMapper;
import com.ershiyi.service.DateTimeJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Service
public class DateTimeJobServiceImpl extends BaseServiceImpl<QuestionAndKnowledge, DateTimeJobMapper> implements DateTimeJobService {
    public static Log log = LogFactory.getLog(DateTimeJobServiceImpl.class);
    @Autowired
    private QuartzScheduler quartzScheduler;

    @Override
    public List<Map<String, Object>> SelectQuestionAndKnowledge() throws Exception {
        /**
         *查找推送知识点
         */
        List<QuestionAndKnowledge> questionAndKnowledge1 = mapper.SelectKnowledge();//查找推送
        List<Map<String, Object>> listMap = new ArrayList<>();//封装数据
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(questionAndKnowledge1.size()!=0){
            System.out.println("进入分发定时任务推送阶段");
            System.out.println("当前推送定时任务知识点数量为"+questionAndKnowledge1.size());
            for(int i=0;i<questionAndKnowledge1.size();i++){
                if("1".equals(questionAndKnowledge1.get(i).getPushType())){
                    /**
                     *计算推送时间
                     */
                    /**
                     * 获取知识点内容接口
                     */
                   // String s = mapper.SelectKnowledgeby(questionAndKnowledge1.get(i).getPlushContentId());
                    //String[] split = s.split(",");
                    //for(int j=0;j<split.length;j++){
                        QuestionAndKnowledge questionAndKnowledge = mapper.SelectKnowledge_Content( questionAndKnowledge1.get(i).getPlushContentId());
                        Map<String, Object> map = new HashMap();
                        Date date = sf.parse(questionAndKnowledge1.get(i).getNextPushDt());
                        String cron= CronUtil.getCron(date);
                        map.put("jobTime", cron);
                        map.put("studenterId",questionAndKnowledge1.get(i).getStudenterId());
                        map.put("static",questionAndKnowledge1.get(i).getStatics());
                        map.put("plushContenId",questionAndKnowledge1.get(i).getPlushContentId());
                        map.put("pushType",questionAndKnowledge1.get(i).getPushType());
                        map.put("chapterId",questionAndKnowledge1.get(i).getChapterId());
                        map.put("courseId",questionAndKnowledge1.get(i).getCourseId());
                        map.put("knowledgeName",questionAndKnowledge.getKnowledgeName());
                        map.put("knowledgeText",questionAndKnowledge.getKnowledgetext());
                        map.put("plushFrequency",questionAndKnowledge1.get(i).getPlushFrequency());
                        map.put("plushId",questionAndKnowledge1.get(i).getPlushId());
                        map.put("nextPushDt",questionAndKnowledge1.get(i).getNextPushDt());
                          map.put("courseName",questionAndKnowledge1.get(i).getCourseName());
                        // map.put("uniqueCode",questionAndKnowledge.get(i).getUniqueCode());
                        map.put("uniqueCode",questionAndKnowledge1.get(i).getUniqueCode());
                        map.put("questionType",questionAndKnowledge1.get(i).getQuestionType());
                        map.put("jobClass", "com.ershiyi.JobDemo.MyJob");
                    map.put("jobName", "知识点-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("jobGroupName", "knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("triggerName","knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("triggerGroupName","知识点-knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
//                        map.put("jobName", "知识点-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"随机编号"+(int)((Math.random()*9+1)*100000));
//                        map.put("jobGroupName", "knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"随机编号"+(int)((Math.random()*9+1)*100000));
//                        map.put("triggerName","knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"随机编号"+(int)((Math.random()*9+1)*100000));
//                        map.put("triggerGroupName","知识点-knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"随机编号"+(int)((Math.random()*9+1)*100000));
                    listMap.add(map);
                }else if("6".equals(questionAndKnowledge1.get(i).getPushType())){
                    /**
                     *计算推送时间
                     */
                    QuestionAndKnowledge questionAndKnowledge = mapper.SelectKnowledge_Content(questionAndKnowledge1.get(i).getPlushContentId());
                    if(questionAndKnowledge!=null){
                    }
                    Map<String, Object> map = new HashMap();
                    Date date = sf.parse(questionAndKnowledge1.get(i).getNextPushDt());
                    String cron= CronUtil.getCron(date);
                    map.put("jobTime", cron);
                    map.put("studenterId",questionAndKnowledge1.get(i).getStudenterId());
                    map.put("static",questionAndKnowledge1.get(i).getStatics());
                    map.put("plushContenId",questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("pushType",questionAndKnowledge1.get(i).getPushType());
                    map.put("chapterId",questionAndKnowledge1.get(i).getChapterId());
                    map.put("courseId",questionAndKnowledge1.get(i).getCourseId());
                    map.put("knowledgeName",questionAndKnowledge.getKnowledgeName());
                    map.put("knowledgeText",questionAndKnowledge.getKnowledgetext());
                    map.put("plushFrequency",questionAndKnowledge1.get(i).getPlushFrequency());
                    map.put("plushId",questionAndKnowledge1.get(i).getPlushId());
                    map.put("nextPushDt",questionAndKnowledge1.get(i).getNextPushDt());
                    map.put("courseName",questionAndKnowledge1.get(i).getCourseName());
                    // map.put("uniqueCode",questionAndKnowledge.get(i).getUniqueCode());
                    map.put("uniqueCode",questionAndKnowledge1.get(i).getUniqueCode());
                    map.put("questionType",questionAndKnowledge1.get(i).getQuestionType());
                    map.put("jobClass", "com.ershiyi.JobDemo.MyJob");
                    map.put("jobName", "知识点-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("jobGroupName", "knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("triggerName","knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    map.put("triggerGroupName","知识点-knowledge-学生编号:"+questionAndKnowledge1.get(i).getStudenterId()+"知识点内容编号"+questionAndKnowledge1.get(i).getPlushContentId());
                    listMap.add(map);
                }

            }
        }else{
            System.out.println("当前无知识点需要推送");
        }
        return listMap;
    }
}

