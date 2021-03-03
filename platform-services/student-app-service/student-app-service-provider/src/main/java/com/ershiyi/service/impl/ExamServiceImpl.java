package com.ershiyi.service.impl;

import com.ershiyi.domain.Common_Choice;
import com.ershiyi.domain.Common_Judge;
import com.ershiyi.domain.Common_StudyrateBy;
import com.ershiyi.domain.entity.QuestionChoice;
import com.ershiyi.dto.ExamDTO;
import com.ershiyi.mapper.ExamMapper;
import com.ershiyi.service.ExamService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamServiceImpl extends BaseServiceImpl<ExamDTO,ExamMapper> implements ExamService {
    public static Log log = LogFactory.getLog(ExamServiceImpl.class);
    private static Random random = new Random();
    @Override
    public List randomExam(List<ExamDTO> examdto) {
        /**
         * 根据考试题目出题
         * 1.根据所有章节的左右编码查询出知识点信息
         * 2.根据知识点随机题目
         */
        List list =new ArrayList(); //最终数据
        List countlist=new ArrayList(); //最下层级知识点 出题依据
        for(int i=0;i<examdto.size();i++){
            List list1=mapper.SelectValue(examdto.get(i));
            countlist.addAll(list1);
        }
        //获取到了知识点id
        //
        List list2 = mapper.SelectKnowledgeWithQuestion(countlist.get(random.nextInt(countlist.size())).toString());
        for(int i=0;i<=15;i++){
            int i1 = random.nextInt(list2.size());
            Common_Choice common_choice = mapper.SelectQuestion( list2.get(random.nextInt(list2.size())).toString());
            if(common_choice==null){
                 common_choice = mapper.SelectQuestion(list2.get(random.nextInt(list2.size())).toString());
            }
            List Choicelist =new ArrayList();
            if(common_choice!=null){
                Choicelist.add("A." + common_choice.getOptionA());
                Choicelist.add("B." + common_choice.getOptionB());
                Choicelist.add("C." + common_choice.getOptionC());
                Choicelist.add("D." + common_choice.getOptionD());
                common_choice.setOptions(Choicelist);
                list.add(common_choice);
            }
            common_choice.setResolving(common_choice.getResolving());
        }
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
        //1.查询所有勾选章节下面的所有知识点
//        List<ExamDTO> listsplie=new ArrayList();
//        String[] split = examdto.getChapterpinjieId().split(",");
//        for(int j=0;j<split.length;j++){
//            ExamDTO exam=new ExamDTO();
//            exam.setChapterId(split[j]);
//            exam.setCourseId(examdto.getCourseId());
//            listsplie.add(exam);
//        }
//        List<ExamDTO> examDTOS = mapper.SchapterByKnowledge(listsplie);
//        List listknowledge = hashlistBylist(examDTOS);
//        //查询知识点内容id
//        List list =new ArrayList();
//        if(listknowledge.size()!=0){
//            List<ExamDTO> examDTOS1 = mapper.SknowledgeByContentId(listknowledge);
//            List listknowledge1 = hashlistBylist1(examDTOS1);
//            List listRandombyChoice =new ArrayList();
//            List listRandombyjudge =new ArrayList();
//
//            for(int i=0;i<listknowledge1.size();i++){
//                if(i<5){
//                    List Choicelist =new ArrayList();
//                    Random ra =new Random();
//                    int randNum = ra.nextInt(listknowledge1.size());
//                    listRandombyChoice.add(listknowledge1.get(randNum));
//                    Common_Choice questionChoice=mapper.SchoiceByknowledgelimit1(listknowledge1.get(randNum).toString());
//                    if(questionChoice!=null){
//                        Choicelist.add("A." + questionChoice.getOptionA());
//                        Choicelist.add("B." + questionChoice.getOptionB());
//                        Choicelist.add("C." + questionChoice.getOptionC());
//                        Choicelist.add("D." + questionChoice.getOptionD());
//                        questionChoice.setOptions(Choicelist);
//                        list.add(questionChoice);
//                    }
//                }else if(i>=5&&i<7){
//                    Random ra =new Random();
//                    int randNum = ra.nextInt(listknowledge1.size());
//                    listRandombyjudge.add(listknowledge1.get(randNum));
//                    Common_Judge common_judge = mapper.SjudgeByKnowledgelimit1(listknowledge1.get(randNum).toString());
//                    if(null == common_judge){
//                        List Choicelist =new ArrayList();
//                        Common_Choice questionChoice=mapper.SchoiceByknowledgelimit1("%"+listknowledge1.get(randNum).toString()+"%");
//                        //Common_Choice questionChoice=mapper.SchoiceByknowledgelimit1("%1277922673367756802%");
//                        if(questionChoice!=null){
//                            Choicelist.add("A." + questionChoice.getOptionA());
//                            Choicelist.add("B." + questionChoice.getOptionB());
//                            Choicelist.add("C." + questionChoice.getOptionC());
//                            Choicelist.add("D." + questionChoice.getOptionD());
//                            questionChoice.setOptions(Choicelist);
//                            list.add(questionChoice);
//                        }
//                    }else{
//
//                        list.add(common_judge);
//                    }
//
//
//                }else{
//                    break;
//                }
//        }
//        }
//        HashSet h = new HashSet(list);
//        list.clear();
//        list.addAll(h);
//        return list;
    }

    @Override
    public Integer submitExam(Map<String, List<Common_Choice>> list) {
        List<Common_Choice> common_studyrateBIES = list.get("data");
        return mapper.insertQuestion(common_studyrateBIES);
    }

    public  List hashlistBylist(List<ExamDTO>  list){
        List listknowledge =new ArrayList();
        for(int i=0;i<list.size();i++){
            String knowledgeid = list.get(i).getKnowledgeId();
            String[] split1 = knowledgeid.split(",");
            for(int k=0;k<split1.length;k++){
                listknowledge.add(split1[k]);
            }
        }
        HashSet h = new HashSet(listknowledge);
        listknowledge.clear();
        listknowledge.addAll(h);
            return  listknowledge;
    }
    public  List hashlistBylist1(List<ExamDTO>  list){
        List listknowledge =new ArrayList();
        for(int i=0;i<list.size();i++){
            String knowledgeid = list.get(i).getKnowledgeContentId();
            String[] split1 = knowledgeid.split(",");
            for(int k=0;k<split1.length;k++){
                listknowledge.add(split1[k]);
            }
        }
        HashSet h = new HashSet(listknowledge);
        listknowledge.clear();
        listknowledge.addAll(h);
        return  listknowledge;
    }
}
