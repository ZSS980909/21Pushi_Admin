package com.ershiyi.job;//package com.ershiyi.job;
//
//import com.ershiyi.service.DateTimeJobService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.quartz.QuartzJobBean;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateTimeJob extends QuartzJobBean {
//    @Autowired
//    private DateTimeJobService datetimejobservice;
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        //获取JobDetail中关联的数据
//        /**
//         * 1.开始进入数据库查询半个小时需要推送的编号,跟内容
//         * 2.对半个小时内推送的时间安排定时任务
//         * 3.进行推送
//         */
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println("目前时间为"+df.format(new Date())+"开始拉取知识点推送中....");
//        try {
//            datetimejobservice.SelectQuestionAndKnowledge();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
