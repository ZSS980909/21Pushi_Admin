package com.ershiyi.cron;

import com.ershiyi.Utils.DecimalUtils;
import com.ershiyi.domain.entity.FractionRecord;
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

    /**
     * 计算学生的天才程度
     */
    @Scheduled(cron = "0 00 2 * * ?") // 每日凌晨两点点执行一次
    public void calculateIQ(){
        log.info("开始计算学生的得分");
        // 获取所有的学生列表
        List<String> students = mapper.getAllStudent();
        log.info("共拿到"+students.size()+"名用户");
        for (String student : students) {
            log.info("开始为"+student+"学生计算");
            // 获取当前做过的所有的知识点
            List<Integer> knows = mapper.getStudyKnowledge(student);
            for (Integer know : knows) {
                // 获取当前知识点的次数和时长
                List<Integer> lengths = mapper.getKnowledgeLength(student,know);
                if(lengths.isEmpty()||lengths.size()==1){
                    log.info("当前知识点不足以计算，跳过");
                    continue;
                }
                List<Double> records = mapper.isAddRecord(student,know);
                if(records.isEmpty()){
                    log.info("当前知识点没有学习记录");
                    continue;
                }
                // 当前已经计算了多少次 只计算后面的
                for (int i = records.size(); i < 7; i++) {
                    if(lengths.size()>i+1){
                        double count = DecimalUtils.div(lengths.get(i)-lengths.get(i+1),lengths.get(i),2);
                        count = count*i*10;
                        if(i==6){
                            for (Double record : records) {
                                count += record;
                            }
                            mapper.insertRecord(DecimalUtils.div(count,6,2), student, 1,know);
                        }else {
                            mapper.insertRecord(count, student, 0,know);
                        }
                    }else{
                        break;
                    }
                }
            }
            double finalFraction = mapper.getFinalFraction(student);
            if(finalFraction==0){
                log.info("目前该学生没有成绩可以计算，跳过");
                continue;
            }
            log.info("计算完成,最终得分为："+finalFraction);
            // 获取当前平均分和人数
            FractionRecord record = mapper.getAvgFraction();
            if(record.getNumber()==0){
                finalFraction = 100 + DecimalUtils.div(finalFraction-100,10,2);
                log.info("当前没有平均值");
            }else{
                log.info("目前的平均分为:"+record.getFraction());
                finalFraction = 100 + DecimalUtils.div((finalFraction-record.getFraction()),record.getNumber(),2);
                log.info(student+"学生IQ为:"+finalFraction);
            }
            // 查询数据库是否有该学生记录
            List<Integer> fraction = mapper.getStudentFraction(student);
            if(fraction.isEmpty()){
                mapper.insertFraction(student,finalFraction);
            }else{
                mapper.updateFraction(fraction.get(0),finalFraction);
            }
            log.info(student+"该学生操作完成，继续下一个学生");
        }
    }


    @Scheduled(cron = "0 0 3 * * ?") // 每日凌晨三点执行一次
    public void calculateNext(){
        // 标准值
        List<Double> fraction  = mapper.getAvgLastFraction();
        double all = fraction.stream().mapToDouble(f -> f).sum();
        double b = DecimalUtils.div(all,fraction.size());
        log.info("当前的学生标准值为："+b);
        double s = 0;
        for (Double r : fraction) {
            s += (r-b)*(r-b);
        }
        s = Math.sqrt(s/fraction.size());
        log.info("标砖差："+s);
        mapper.insertTeamAvg(b,s);
    }
}
