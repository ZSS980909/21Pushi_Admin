package com.ershiyi.JobDemo;

import com.ershiyi.controller.JPushController;
import com.ershiyi.dto.JpushPojo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

/**
 * @Author: liyong
 * @Description: 自定义定时任务类
 */
public class MyJob implements Job {
    @Autowired
    private JPushController jPushController;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String jobName = jobExecutionContext.getJobDetail().getKey().toString();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobName + "推送开始：" + dateformat.format(System.currentTimeMillis()));
        System.out.println("map内容为"+jobDataMap.toString());
        System.out.println("推送内容id为"+jobDataMap.get("plushContenId"));
        System.out.println("学生编号为"+jobDataMap.get("studenterId"));
        System.out.println("状态"+jobDataMap.get("static"));
        System.out.println("推送类型"+jobDataMap.get("pushType"));
        System.out.println("推送章节为"+jobDataMap.get("chapterId"));
        System.out.println("推送课程为"+jobDataMap.get("courseId"));
        System.out.println("推送对象为"+jobDataMap.get("uniqueCode"));
        System.out.println("推送知识点标题为"+jobDataMap.get("knowledgeName"));
        System.out.println("推送知识点内容为"+jobDataMap.get("knowledgeText"));
        System.out.println("推送知识点次数为"+jobDataMap.get("plushFrequency"));
        System.out.println("推送知识点Id为"+jobDataMap.get("plushId"));
        System.out.println("推送知识时间为"+jobDataMap.get("nextPushDt"));
        jobDataMap.put("nextPushDt",jobDataMap.get("nextPushDt").toString().substring(0,19));
        System.out.println(jobDataMap.get("nextPushDt"));
        System.out.println("推送课程名为"+jobDataMap.get("courseName"));
        /**
         * 调用极光
         */
        JpushPojo jpush =new JpushPojo();
        jpush.setMsg("{\"courseId\":\""+jobDataMap.get("courseId")+"\",\"chapterId\":\""+
                jobDataMap.get("chapterId")+"\",\"sendType\":\""+jobDataMap.get("pushType")
                +"\",\"static\":\""+jobDataMap.get("static")+"\",\"studenterId\":\""+jobDataMap.get("studenterId")+
                "\",\"plushContenId\":\""+jobDataMap.get("plushContenId")+"\",\"questionType\":\""+jobDataMap.get("questionType")+"\",\"knowledgeName\":\""+
                jobDataMap.get("knowledgeName")+"\",\"knowledgeText\":\""+jobDataMap.get("knowledgeText")+"\",\"plushFrequency\":\""+
                jobDataMap.get("plushFrequency")+"\",\"plushId\":\""+
                jobDataMap.get("plushId")+"\",\"nextPushDt\":\""+jobDataMap.get("nextPushDt")+"\",\"courseName\":\""+jobDataMap.get("courseName")+"\"}");
        jpush.setStudenterId(jobDataMap.get("studenterId").toString());
        jpush.setSendType(jobDataMap.get("pushType").toString());
        jpush.setRegistrationId(jobDataMap.get("uniqueCode").toString());
        jPushController.jpushAndroidcall(jpush);
        System.out.println(jobName + "推送完成：" + dateformat.format(System.currentTimeMillis()));
    }
}