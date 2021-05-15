package com.ershiyi.cron;

import com.ershiyi.mapper.BestQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;;
import java.util.ArrayList;
import java.util.List;

/**
 * 每天定时计算最优题目题目
 */
@Component
@EnableScheduling
public class QuestionCronTask {
    private Logger log = LoggerFactory.getLogger(QuestionCronTask.class);

    @Resource
    private BestQuestionMapper mapper;

    // 获取每个知识点的最优题目
    @Scheduled(cron = "0 0 1 * * ?") // 每日凌晨一点执行一次
    public void getBestQuestion(){
        // 先查询是否有题目可以继续操作
        List<Integer> knowIds = mapper.getQualifiedKnowId();
        if(knowIds.isEmpty()){
            log.info("当前题库的所有题目已经完成！当天跳过计算");
            return;
        }
        // 拿到正确率在85的以上的所有学生
        List<String> students = mapper.getBestStudent();
        if(students.isEmpty()){
            log.info("没有合适的学生，当天跳过计算");
            return;
        }
        // 去拿他们做的题目id 唯一的
        List<Integer> questionId = mapper.getQuestionIdByStudent(students);
        if(questionId.isEmpty()){
            log.info("没有合适的题目，跳过计算");
            return;
        }
        // 将他们的题目作为最优题目存入数据库
        log.info("当天总共有"+questionId.size()+"道题目被加入最优题目");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < questionId.size(); i+=500) {
                if(i+500>=questionId.size()){
                    result = questionId.subList(i,questionId.size());
                }else{
                    result = questionId.subList(i,i+500);
                }
                mapper.updateQuestion(result);
        }
        log.info("完成最优题目优选");
    }
}
