package com.ershiyi.service.Impl;

import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.dto.ScoreDTO;
import com.ershiyi.dto.questionSituationDTO;
import com.ershiyi.mapper.BornMapper;
import com.ershiyi.service.BornService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BornServiceImpl implements BornService {
    public static Log log = LogFactory.getLog(LocationServiceImpl.class);
    @Autowired
    private BornMapper mapper;

    @Override
    public questionSituationDTO questionSituation(LocationRequestDTO localtionrequest) {
      return mapper.questionSituation(localtionrequest);

    }

    @Override
    public Map <String,List<questionSituationDTO>> knowledgeNumber(LocationRequestDTO localtionrequest) {
//        List<questionSituationDTO> question=new ArrayList<questionSituationDTO>();
//        for(int i=0;i<7;i++){
//            localtionrequest.setId(i);
           // question.add(mapper.knowledgeNumber(localtionrequest));
      //  }
        List<questionSituationDTO> questionSituationDTOS = mapper.knowledgeNumberByStudenterId(localtionrequest); //该学生的
        List<questionSituationDTO> questionSituationDTOS1 = mapper.knowledgeNumber(localtionrequest); //平均值
        for(int i=0;i<questionSituationDTOS.size();i++){
            String starttime = questionSituationDTOS.get(i).getStartTime();
            String substring = starttime.substring(0, 10);
            questionSituationDTOS.get(i).setStartTime(substring);
        }

        List<questionSituationDTO> questionSituationDTOS2= new ArrayList<>();

        List listq=new ArrayList();
        for(int j=0;j<questionSituationDTOS1.size();j++){
            //计算平均分值
            for(int o=0;o<questionSituationDTOS1.size();o++){
                String starttime = questionSituationDTOS1.get(j).getStartTime();
                String substring = starttime.substring(0, 10);
                boolean checkDeviceCollectCode = listq.contains(substring);
                if(checkDeviceCollectCode ==false) {
                    listq.add(substring);
                }
            }
            String starttime = questionSituationDTOS1.get(j).getStartTime();
            String substring = starttime.substring(0, 10);



//            for(int l=0;l<questionSituationDTOS1.size();l++){
//                if (substring.equals(questionSituationDTOS1.get(l).getStartTime().substring(0,10))){
//                    PNumberCount+= Double.parseDouble(questionSituationDTOS1.get(l).getpNumberCount());
//                    ques.setStartTime(questionSituationDTOS1.get(l).getStartTime().substring(0,10));
//                }
//                if(l==questionSituationDTOS1.size()-1){
//                    ques.setpNumberCount(PNumberCount.toString());
//                    questionSituationDTOS2.add(ques);
//                }


          //  }
           // questionSituationDTOS1.get(j).setStartTime(substring);


//            PNumberCount+= Double.parseDouble(questionSituationDTOS1.get(j).getpNumberCount());
//            if (j==questionSituationDTOS1.size()-1){
//                //最后一个计算出平均值
//                Double pNumberCount =PNumberCount/questionSituationDTOS1.size();
//                questionSituationDTOS1.get(j).setpNumberCount(pNumberCount.toString());
//            }

        }
        for(int p=0;p<listq.size();p++){
            Double PNumberCount=0.0;
            questionSituationDTO ques =new questionSituationDTO();
            for(int l=0;l<questionSituationDTOS1.size();l++){
                if(listq.get(p).equals(questionSituationDTOS1.get(l).getStartTime().substring(0,10))){
                    PNumberCount+= Double.parseDouble(questionSituationDTOS1.get(l).getpNumberCount());
                    ques.setStartTime(questionSituationDTOS1.get(l).getStartTime().substring(0,10));
                }
                if(l==questionSituationDTOS1.size()-1){

                    ques.setpNumberCount(PNumberCount.toString());
                    questionSituationDTOS2.add(ques);
                }
            }

        }



        Map <String,List<questionSituationDTO>> map =new HashMap<String,List<questionSituationDTO>>();
        map.put("knowledgeNumber",questionSituationDTOS);
        map.put("sknowledgeNumber",questionSituationDTOS2);
        return map;
    }

    @Override
    public Map<String,List<questionSituationDTO>> studyDuration(LocationRequestDTO localtionrequest) {
        Map<String,List<questionSituationDTO>> map =new HashMap<String,List<questionSituationDTO>>();
        //if("1".equals(localtionrequest.getType())){
//            String firstandEnd = DateUtils.getFirstandEnd();
//            String[] ands = firstandEnd.split("and");
//            localtionrequest.setStartTime(ands[0]+" 00:00:00");
//            localtionrequest.setEndTime(ands[1]+" 23:59:59");
             // 月季度
             // 1.获取当月第一天跟最后一天
             // 2.查询最大,最小,平均,该学生的数组数据  (以日为基础)
            String minstudenterId="";
            String maxstudenterId="";
            List<questionSituationDTO> questionSituationDTOS = mapper.studyDuration(localtionrequest);
            List a =new ArrayList();
            for(int i=0;i<questionSituationDTOS.size();i++){
                a.add(Integer.parseInt(questionSituationDTOS.get(i).getSumNumber()));
            }
            /**
             * 对比
             */
            if(!a.isEmpty()) {
                Comparable min = Collections.min(a);
                Comparable max = Collections.max(a);
                for (int j = 0; j < questionSituationDTOS.size(); j++) {
                    if (questionSituationDTOS.get(j).getSumNumber().equals(min.toString())) {
                        log.info("最低的学生编号为" + questionSituationDTOS.get(j).getStudenterId());
                        minstudenterId = questionSituationDTOS.get(j).getStudenterId();
                    }
                    if (questionSituationDTOS.get(j).getSumNumber().equals(max.toString())) {
                        log.info("最高的学生编号为" + questionSituationDTOS.get(j).getStudenterId());
                        maxstudenterId = questionSituationDTOS.get(j).getStudenterId();
                    }
                }
            }
            //该学生
            List<questionSituationDTO> questionSituationDTOS1 = mapper.SMaxandMix(localtionrequest);
            //最小值
            localtionrequest.setStudenterId(minstudenterId);
            List<questionSituationDTO> questionSituationDTOS2 = mapper.SMaxandMix(localtionrequest);
            //最大值
            localtionrequest.setStudenterId(maxstudenterId);
            List<questionSituationDTO> questionSituationDTOS3 = mapper.SMaxandMix(localtionrequest);

            //平均值
            List<questionSituationDTO> questionSituationDTOS4 = mapper.SAvg(localtionrequest);
           // List<questionSituationDTO> questionSituationDTOS3 = mapper.SThis(localtionrequest);
        if(questionSituationDTOS1.size()!=0){
            for(int i=0;i<questionSituationDTOS1.size();i++){
                String starttime = questionSituationDTOS1.get(i).getStartTime();
                    String substring = starttime.substring(0, 10);
                questionSituationDTOS1.get(i).setStartTime(substring);
            }
        }
        if(questionSituationDTOS2.size()!=0) {
            for (int j = 0; j < questionSituationDTOS2.size(); j++) {
                String starttime = questionSituationDTOS2.get(j).getStartTime();
                String substring = starttime.substring(0, 10);
                questionSituationDTOS2.get(j).setStartTime(substring);
            }
        }
        if(questionSituationDTOS3.size()!=0) {
            for (int k = 0; k < questionSituationDTOS3.size(); k++) {
                String starttime = questionSituationDTOS3.get(k).getStartTime();
                String substring = starttime.substring(0, 10);
                questionSituationDTOS3.get(k).setStartTime(substring);
            }
        }
        if(questionSituationDTOS4.size()!=0) {
            for (int l = 0; l < questionSituationDTOS4.size(); l++) {
                String starttime = questionSituationDTOS4.get(l).getStartTime();
                String substring = starttime.substring(0, 10);
                questionSituationDTOS4.get(l).setStartTime(substring);
            }
        }
            map.put("sThis",questionSituationDTOS1);
            map.put("sMin",questionSituationDTOS2);
            map.put("sMax",questionSituationDTOS3);
            map.put("sAvg",questionSituationDTOS4);
      //  }else{
//            String firstandEnd = DateUtils.getFirstandEndYear();
//            String[] ands = firstandEnd.split("and");
//
//            // 年季度
//            // 1.获取当年第一天跟最后一天
//            // 2.查询最大,最小,平均,该学生的数组数据 (以月为基础)
//
//            localtionrequest.setStartTime(ands[0]+" 00:00:00");
//            localtionrequest.setEndTime(ands[1]+" 23:59:59");
//
//            // 月季度
//            // 1.获取当月第一天跟最后一天
//            // 2.查询最大,最小,平均,该学生的数组数据  (以日为基础)
//
//            String minstudenterId="";
//            String maxstudenterId="";
//            List<questionSituationDTO> questionSituationDTOS = mapper.studyDuration(localtionrequest);
//            List a =new ArrayList();
//            for(int i=0;i<questionSituationDTOS.size();i++){
//                a.add(Integer.parseInt(questionSituationDTOS.get(i).getSumNumber()));
//            }
//            /**
//             * 对比
//             */
//            Comparable min = Collections.min(a);
//            Comparable max = Collections.max(a);
//            for(int j=0;j<questionSituationDTOS.size();j++){
//                if(questionSituationDTOS.get(j).getSumNumber().equals(min.toString())){
//                    log.info("最低的学生编号为"+questionSituationDTOS.get(j).getStudenterId());
//                    minstudenterId=questionSituationDTOS.get(j).getStudenterId();
//                }
//                if(questionSituationDTOS.get(j).getSumNumber().equals(max.toString())){
//                    log.info("最高的学生编号为"+questionSituationDTOS.get(j).getStudenterId());
//                    maxstudenterId=questionSituationDTOS.get(j).getStudenterId();
//                }
//            }
//            //该学生
//            List<questionSituationDTO> questionSituationDTOS1 = mapper.SMaxandMix(localtionrequest);
//            //最小值
//            localtionrequest.setStudenterId(minstudenterId);
//            List<questionSituationDTO> questionSituationDTOS2 = mapper.SMaxandMix(localtionrequest);
//            //最大值
//            localtionrequest.setStudenterId(maxstudenterId);
//            List<questionSituationDTO> questionSituationDTOS3 = mapper.SMaxandMix(localtionrequest);
//
//            //平均值
//            List<questionSituationDTO> questionSituationDTOS4 = mapper.SAvg(localtionrequest);
//            // List<questionSituationDTO> questionSituationDTOS3 = mapper.SThis(localtionrequest);
//
//            map.put("SThis",questionSituationDTOS1);
//            map.put("SMin",questionSituationDTOS2);
//            map.put("SMax",questionSituationDTOS3);
//            map.put("SAvg",questionSituationDTOS4);



  //      }
        return map;
    }

    @Override
    public List<questionSituationDTO> studyTime(LocationRequestDTO localtionrequest) {
        return mapper.studyTime(localtionrequest);
    }

    @Override
    public Object syntheticalScore(LocationRequestDTO localtionrequest) {
        /**
         * 查询各科的综合得分
         * 1.查询各个科目的id
         * 2.查询各个科目该学生的学习时长比值跟平均值
         * 3.查询各个科目该学生的学习知识点数量比值跟平均值
         * 4.查询各个科目该学生的做题情况比值跟平均值
         */
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());// new Date()为获取当前系统时间
        String year = format.substring(0, 4);
        String month = format.substring(5, 7);
        localtionrequest.setMonth(month);
        localtionrequest.setYear(year);
        List list =new ArrayList<>();
        List<questionSituationDTO> questionSituationDTO = mapper.SSubject();
        List<ScoreDTO> yesscore = mapper.yesscore(localtionrequest); //上一次数据
        for(int i=0;i<questionSituationDTO.size();i++){
            /**
             * 查询今天是否已经计算过
             */
            localtionrequest.setSubjectId(questionSituationDTO.get(i).getId());
            List<ScoreDTO> sscore = mapper.Sscore(localtionrequest);
            boolean empty = sscore.isEmpty();
            if(empty) {
                Map<String, Object> map = new HashMap<>();
                log.info("科目" + questionSituationDTO.get(i).getSubjectName() + "分值计算中");
                log.info("学习知识点数量计算中");
                int fenzhi = 0;  //总共分值
                int numberCount = 0;  //学习知识点数量
                int pnumberCount = 0;
               // localtionrequest.setSubjectId(questionSituationDTO.get(i).getId());
                List<com.ershiyi.dto.questionSituationDTO> questionSituationDTOS = mapper.knowledgeNumberByStudenterId(localtionrequest); //该学生的
                List<com.ershiyi.dto.questionSituationDTO> questionSituationDTOS1 = mapper.knowledgeNumber(localtionrequest); //平均值
                for (int a = 0; a < questionSituationDTOS.size(); a++) {
                    int v = Integer.parseInt(questionSituationDTOS.get(a).getNumberCount());
                    numberCount = numberCount+v;
                 //   numberCount += Double.parseDouble((questionSituationDTOS.get(a).getNumberCount()) + numberCount);
                }
                for (int b = 0; b < questionSituationDTOS1.size(); b++) {
                    int i1 = Integer.parseInt(questionSituationDTOS1.get(b).getpNumberCount());
                    pnumberCount=pnumberCount+i1;
                 //   pnumberCount += Double.parseDouble((questionSituationDTOS1.get(b).getpNumberCount()) + pnumberCount);
                }
                if (numberCount == 0 | pnumberCount == 0) {
                    log.info("当前为0,无数据");
                } else {
                    Double avg = (double) numberCount / pnumberCount * 100;
                    log.info("当前科目为" + questionSituationDTO.get(i).getSubjectName());
                    log.info("计算的百分比为" + avg + "%");
                    if(avg>=100){
                        fenzhi=fenzhi+34;
                        log.info("获得学习知识点数量总和得分为" + 34);
                    }else {
                        Double fen = (double) avg / 100 * 34;
                        int ceil = (int) Math.ceil(fen);
                        fenzhi = fenzhi + ceil;
                        log.info("获得学习知识点数量总和得分为" + ceil);
                    }
                }
                /**
                 *
                 */
                log.info("学习知识点时长计算中");
                int sumnumber = 0;  //时长
                int psumnumber = 0;
                List<com.ershiyi.dto.questionSituationDTO> questionSituationDTOS4 = mapper.SMaxandMix(localtionrequest);
                List<com.ershiyi.dto.questionSituationDTO> questionSituationDTOS5 = mapper.SAvg(localtionrequest);
                questionSituationDTOS4.remove(null);  //移除第一个null
                questionSituationDTOS4.removeAll(Collections.singleton(null)); //移除所有的null元素
                questionSituationDTOS5.remove(null);  //移除第一个null
                questionSituationDTOS5.removeAll(Collections.singleton(null)); //移除所有的null元素
                if(questionSituationDTOS4.size()!=0){
                    for (int q = 0; q < questionSituationDTOS4.size(); q++) {
                        int i1= (int) Math.ceil( Double.parseDouble(questionSituationDTOS4.get(q).getSumNumber()));
                        //  Double i1 =  Double.parseDouble(questionSituationDTOS4.get(q).getSumNumber());
                        sumnumber = (int) Math.ceil(sumnumber+i1);
                        //  sumnumber += Double.parseDouble(questionSituationDTOS4.get(q).getSumNumber());
                    }
                }else{
                    sumnumber=0;
                }
                if(questionSituationDTOS5.size()!=0){
                    for (int w = 0; w < questionSituationDTOS5.size(); w++) {
                        int i1= (int) Math.ceil( Double.parseDouble(questionSituationDTOS5.get(w).getSumNumber()));
                        //int i1 = (int) Double.parseDouble(questionSituationDTOS5.get(w).getSumNumber());
                        psumnumber = (int) Math.ceil(psumnumber+i1);
                        //   psumnumber += Double.parseDouble(questionSituationDTOS5.get(w).getSumNumber());
                    }
                }else{
                    psumnumber=0;
                }
                if (sumnumber == 0 | psumnumber == 0) {
                    log.info("当前为0,无数据");
                } else {
                    Double avg1 = (double) sumnumber / psumnumber * 100;
                    log.info("计算的百分比为" + avg1 + "%");
                    if(avg1>=100){
                        fenzhi=fenzhi+33;
                        log.info("获得学习知识点时长总和得分为" + 33);
                    }else{
                        Double fen = (double) avg1 / 100 * 33;
                        int ceil = (int) Math.ceil(fen);
                        fenzhi = fenzhi + ceil;
                        log.info("获得学习知识点时长总和得分为" + ceil);
                    }

                }
                log.info("做题情况分值计算中");

                com.ershiyi.dto.questionSituationDTO questionSituationDTO1 = mapper.questionSituation(localtionrequest);
                int i4 = Integer.parseInt(questionSituationDTO1.getCorrectCount());
                int i1 = Integer.parseInt(questionSituationDTO1.getErrorCount());
                int i5 = Integer.parseInt(questionSituationDTO1.getPcorrectCount());
                int i2 = Integer.parseInt(questionSituationDTO1.getPerrorCount());
                Double i6 =(double)i4/(i4+i1);
                Double i7 = (double)i5/(i5+i2);
                if (i6-i7 > 0) {
                    if(i6-i7>=100){
                        fenzhi = fenzhi + 33;
                        log.info("获得学习知识点时长总和得分为" + 33);
                    }else{
                        Double fen = (i6-i7) * 33;
                        int ceil = (int) Math.ceil(fen);
                        fenzhi = fenzhi + ceil;
                        log.info("获得学习知识点时长总和得分为" + ceil);
                    }

                } else {
                    log.info("做题情况得0分");
                }
                ScoreDTO socre= new ScoreDTO();
                socre.setScore(fenzhi);
                socre.setStudenterId(localtionrequest.getStudenterId());
                socre.setSubjectId(localtionrequest.getSubjectId());
                socre.setSubjectName(questionSituationDTO.get(i).getSubjectName());
                for(int k=0;k<yesscore.size();k++){
                    if(yesscore.get(k).getSubjectId().equals(socre.getSubjectId())){
                        socre.setLastScore(yesscore.get(k).getLastScore());
                    }
                }
                list.add(socre);
                Integer score = mapper.score(socre);
                if (score == 1) {
                    log.info("记录成功");
                }
            }else{
                /**
                 * 装载上一次数据
                 */
                for(int v=0;v<sscore.size();v++){
                    for (int g=0;g<yesscore.size();g++){
                        if(sscore.get(v).getSubjectId().equals(yesscore.get(g).getSubjectId())){
                            sscore.get(v).setLastScore(yesscore.get(g).getLastScore());
                        }
                    }
                }
                for(int  l=0;l<sscore.size();l++){
                    list.add(sscore.get(l));
                }
            }
        }
        return list;
    }
}
