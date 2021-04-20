package com.ershiyi.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.domain.entity.ResultQuestion;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.dto.QuestionAndKnowledge;
import com.ershiyi.mapper.JpushMapper;
import com.ershiyi.service.JpushService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class JpushServiceImpl  extends BaseServiceImpl<JpushPojo, JpushMapper> implements JpushService {
    public static Log log = LogFactory.getLog(JpushServiceImpl.class);
    // 设置好账号的app_key和masterSecret是必须的
    private static String APP_KEY = "17fa067a86192200faa99509";
    private static String MASTER_SECRET = "da1c9a74226c80b33dce4830";
    private static String APP_KEY_PARENT="d33a222c453a4839a0db0fd6";
    private static String MASTER_SECRET_PARENT ="44f2d5aabd642427d19cb9b1";
    @Override
    public JpushPojo sendJpush(JpushPojo jpush) {
        //jpush.setMsg("{\"sendType\":"+jpush.getSendType()+"}");
        //Msg ="{\"type\":"+Sendtype+"}";
        /**
         * 判断是否是需要图片的推送
         */
        JPushClient jpushClient=null;
        log.info("当前接收到SendType类型为"+jpush.getSendType());
        if("4".equals(jpush.getSendType())||"5".equals(jpush.getSendType())){
            if("4".equals(jpush.getSendType())){
                log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入截屏操作");
            }else if("5".equals(jpush.getSendType())){
                log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入抓拍操作");
            }
            String RegistrationId="";
            if(jpush.getImageUrl()==null||jpush.getImageUrl()==""){
                RegistrationId = mapper.selectbyStudenterId(jpush);
                jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
            }else{
                jpushClient = new JPushClient(MASTER_SECRET_PARENT, APP_KEY_PARENT);
                 RegistrationId = mapper.selectbyParentId(jpush);
            }
            log.info("数据库中查询绑定的RegistrationId为"+RegistrationId);
            jpush.setRegistrationId(RegistrationId);
            jpush.setMsg("{\"imageUrl\":\""+jpush.getImageUrl()+"\",\"sendType\":\""+jpush.getSendType()+"\",\"parenterId\":\""+jpush.getParenterId()+"\"}");
            //创建JPushClient(极光推送的实例)
            log.info("当前推送的目标RegistrationId为"+jpush.getRegistrationId());
            log.info("当前推送的目标消息为"+jpush.getMsg());
        }else if ("2".equals(jpush.getSendType())||"3".equals(jpush.getSendType())){
            if("2".equals(jpush.getSendType())){
                log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入锁屏操作");
            }else if("3".equals(jpush.getSendType())){
                log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入解屏操作");
            }
            jpush.setMsg("{\"sendType\":\""+jpush.getSendType()+"\"}");
            String RegistrationId = mapper.selectbyStudenterId(jpush);
            log.info("数据库中查询绑定的RegistrationId为"+RegistrationId);
            jpush.setRegistrationId(RegistrationId);
            //创建JPushClient(极光推送的实例)
            jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
            log.info("当前推送的目标RegistrationId为"+jpush.getRegistrationId());
        }else if("1".equals(jpush.getSendType())){
                //知识点推送
            log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入知识点推送操作");
            jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        }else if("6".equals(jpush.getSendType())){
                //错题推送
            log.info("当前获取的SendType类型为"+jpush.getSendType()+"进入错题推送操作");
            jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        }
        if(jpush.getRegistrationId()==null||jpush.getRegistrationId()==""){
            return null;
        }

        //推送的关键,构造一个payload
        int result=0;
        try {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())//指定android平台的用户
                .setAudience(Audience.all())    //你项目中的所有用户
                // .setAudience(Audience.alias(parm.get("alias")))     //设置别名发送,单点对点方式
                //.setAudience(Audience.tag("tag1"))        //设置按标签发送，相当于群发
               // .setAudience(Audience.registrationId(jpush.getRegistrationId()))
                 .setAudience(Audience.registrationId(jpush.getRegistrationId()))  //registrationId指定用户

                 //.setNotification(Notification.android(jpush.getMsg(),"推送记录",map))  //发送内容
                .setOptions(Options.newBuilder().setApnsProduction(true).setTimeToLive(7200).build())
                // apnProduction指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式) 不用设置也没关系
                // TimeToLive 两个小时的缓存时间
                .setMessage(Message.content(jpush.getMsg()))//自定义信息
                .build();
                PushResult pu = jpushClient.sendPush(payload);

                if (pu.getResponseCode() == 200) {
                    result = 1;
                }
            System.out.println(result+"=======>1推送成功 0推送失败");
            return jpush;
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return jpush;
    }

    @Override
    public List<ResultQuestion>  questionjpush(QuestionAndKnowledge question) {
        List<ResultQuestion> result =new ArrayList<>();
        question.setPlushContentId(question.getPlushContentId());
        /**
         * 查詢s关联的知识点
         */
        List questionjpushandquestion=mapper.questionjpushbyyes(question);
        if(questionjpushandquestion.size()!=0){
            for(int i=0;i<questionjpushandquestion.size();i++){
                List<QuestionChoice> questionjpush=mapper.questionjpushbyquestion(questionjpushandquestion.get(i).toString());
                if(questionjpush.size()==0){
                        //没有匹配的题目,拿取随机题目
                    List<QuestionChoice> questionjpushNo=mapper.questionjpushbyquestionNo(questionjpushandquestion.get(i).toString());
                    for(int j=0;j<questionjpushNo.size();j++){
                        result.add(SwitchQuestion(questionjpushNo.get(j)));
                    }
                }else{
                    for(int j=0;j<questionjpush.size();j++){
                        result.add(SwitchQuestion(questionjpush.get(j)));
                    }
                }
            }
        }else{
            List questionjpushandquestionbyno=mapper.questionjpushbyquestionbyno(question);
            for(int i=0;i<questionjpushandquestionbyno.size();i++){
//                List<QuestionChoice> questionjpush=mapper.questionjpushbyquestion(questionjpushandquestion.get(i).toString());
//                if(questionjpush.size()==0){
                    //随机抽取,就随机拿
                List<QuestionChoice>   questionjpushNo=mapper.questionjpushbyquestionNo(questionjpushandquestionbyno.get(i).toString());
                   if(questionjpushNo.size()!=0){
                       for(int j=0;j<questionjpushNo.size();j++){
                            result.add(SwitchQuestion(questionjpushNo.get(j)));
                       }
                   }

                }
            }

        //}
//        List<QuestionChoice> questionjpush = mapper.questionjpush(question);
//        for(int i=0;i<questionjpush.size();i++){
//            result.add(SwitchQuestion(questionjpush.get(i)));
//        }
            return result;
    }
    public static ResultQuestion SwitchQuestion(QuestionChoice question){
        List<String> options = new ArrayList<>();
        ResultQuestion result = new ResultQuestion();
        result.setQuestionId(Integer.parseInt(question.getQuestionId()));
        result.setKnowName(question.getKnowledgeName());
        result.setKnowId(question.getKnowledgeId());
        result.setQuestion(question.getQuestion());
        result.setResolving(question.getResolving());
        result.setType(question.getType());
        result.setCorrectOption(question.getCorrectOption());
        options.add("A." +  question.getOptionA());
        options.add("B." +  question.getOptionB());
        options.add("C." +  question.getOptionC());
        options.add("D." +  question.getOptionD());
        result.setOptions(options);
        return result;
    }
}
