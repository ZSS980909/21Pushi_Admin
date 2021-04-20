package com.ershiyi.JobDemo;


import com.ershiyi.service.DateTimeJobService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyong
 * @Description: 监听器
 * Quartz的job在项目重启时，job都失效了，把每次启动的job都存放在数据库，然后项目启动时监听器读取数据库的job，然后添加job
 */
@Component
@Order(value = 1)
public class QuartzJobListener implements CommandLineRunner,Job {
    @Autowired
    private QuartzScheduler quartzScheduler;
    @Autowired
    private DateTimeJobService datetimejobservice;
    /**
     * 初始启动quartz
     *
     * @param
     */
    @Override
    public void run(String... args) throws Exception {
        try {
             quartzScheduler.startJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            List<Map<String, Object>> listMap = datetimejobservice.SelectQuestionAndKnowledge();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //long currentTime = System.currentTimeMillis() + 2 * 60 * 1000;
            for(int i=0;i<listMap.size();i++){
                if("1".equals(listMap.get(i).get("pushType"))) {
                    System.out.println("推送知识点中....");
                    System.out.println("应该推送的时间为" + listMap.get(i).get("jobTime"));
//                    Date jobtime =simpleDateFormat.parse(listMap.get(i).get("jobTime").toString());
//                    if(jobtime.getTime()<new Date().getTime()){
//                        System.out.println("123");
//                        listMap.get(i).put("jobTime",new Date(currentTime));
//                    }
//                    Boolean aBoolean = quartzScheduler.notExists((String) listMap.get(i).get("triggerName"), (String) listMap.get(i).get("triggerGroupName"));
//                    if (aBoolean){
//                        System.out.println("已经存在任务");
//                    }else {
                        quartzScheduler.addJob((String) listMap.get(i).get("jobName"), (String) listMap.get(i).get("jobGroupName"),
                                (String) listMap.get(i).get("triggerName"), (String) listMap.get(i).get("triggerGroupName"), (String) listMap.get(i).get("jobClass"),
                                (String) listMap.get(i).get("jobTime"), listMap.get(i));
                   // }
                }
                //推送知识点
                else if("6".equals(listMap.get(i).get("pushType"))){
                    System.out.println("推送题目中....");
                    System.out.println("应该推送的时间为"+listMap.get(i).get("jobTime"));
//                    Boolean aBoolean = quartzScheduler.notExists((String) listMap.get(i).get("triggerName"), (String) listMap.get(i).get("triggerGroupName"));
//                    if (aBoolean){
//
//                    }else{
                        //推送题目
                        quartzScheduler.addJob((String)listMap.get(i).get("jobName"),(String)listMap.get(i).get("jobGroupName"),
                                (String)listMap.get(i).get("triggerName"),(String)listMap.get(i).get("triggerGroupName"),(String)listMap.get(i).get("jobClass"),
                                (String)listMap.get(i).get("jobTime"),listMap.get(i));
                   // }

                }
//                quartzScheduler.addJob((String)listMap.get(i).get("jobName"),(String)listMap.get(i).get("jobGroupName"),
//                        (String)listMap.get(i).get("jobName"),(String)listMap.get(i).get("jobGroupName"),(String)listMap.get(i).get("jobClass"),(String)listMap.get(i).get("jobTime"),listMap.get(i));
            }
//            for (Map<String, Object> map : listMap) {
//                try {
//                    quartzScheduler.addJob((String) map.get("jobName"), (String) map.get("jobGroupName"),
//                     (String) map.get("jobName"), (String) map.get("jobGroupName"), (String) map1.get("jobClass"), (String) map.get("jobTime"), new HashMap<>(16));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println("推送程序已经启动...");
        }catch (Exception e){

        }
    }
}
