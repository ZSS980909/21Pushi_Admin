package com.ershiyi.mapper;

import com.ershiyi.dto.QuestionAndKnowledge;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.AbstractMapper;

import java.util.List;

public interface DateTimeJobMapper  extends AbstractMapper<QuestionAndKnowledge> {

    @Select("select  studenterId, thisPushDt, nextPushDt, static as statics, plushContentId, pushType, chapterId, courseId,(select uniqueCode from common_student_user where studenterid=common_Ipush_record.studenterId  and deleted=0 ) as uniqueCode from common_Ipush_record where static=0   and  pushType=6 and nextPushDt <(select date_add(now(),interval 20 minute)) and  nextPushDt >= sysdate()")
    List<QuestionAndKnowledge> SelectQuestion();
//    @Select("select  studenterId, thisPushDt, nextPushDt, static as statics, plushContentId, pushType, chapterId, courseId,(select  knowledgetext from  common_course_knowledge_content where id =plushContentId) as knowledgetext,(select  knowledgeName from  common_course_knowledge_content where id =plushContentId) as knowledgeName, (select uniqueCode from common_student_user where studenterid=common_Ipush_record.studenterId  and deleted=0 ) as uniqueCode from common_Ipush_record where static=0  and  pushType=1 and nex]xPushDt <(select date_add(now(),interval 20 minute)) and  nextPushDt >= sysdate()")
//    List<QuestionAndKnowledge> SelectKnowledge();
//    @Select("select   id as plushId,a.studenterId, a.thisPushDt, a.nextPushDt, a.static as statics, a.createDt, a.plushContentId, a.plushFrequency, a.pushType,a.chapterId, a.courseId, a.questionType,(select curriculum from common_course where id =a.courseId) as courseName,(select uniqueCode from common_student_user where studenterid=a.studenterId) as uniqueCode  from common_Ipush_record a where a.static=0  and a.nextPushDt <(select date_add(now(),interval 5 minute)) and  a.nextPushDt >= sysdate()")
    @Select("select   id as plushId,a.studenterId, a.thisPushDt, a.nextPushDt, a.static  as statics, a.createDt,a.plushContentId, a.plushFrequency, a.pushType,a.chapterId, a.courseId, a.questionType,(select curriculum from common_course where id =a.courseId) as courseName,(select uniqueCode from common_student_user where studenterid=a.studenterId) as uniqueCode from common_Ipush_record a where a.static=0  and a.nextPushDt  <=#{netime}")
    List<QuestionAndKnowledge> SelectKnowledge(@Param("netime") String netime);
    @Select("select knowledgeContentId from  common_course_knowledge where  id =#{knowledgeContentId}")
    String SelectKnowledgeby(@Param("knowledgeContentId") String knowledgeContentId);
    //@Select("select knowledgetext,knowledgeName from  common_course_knowledge_content where id like #{plushContentId}")
    @Select("select knowledgeName as knowledgeName,knowledgeContent as knowledgetext from `21db_test`.common_course_knowledge where id=#{plushContentId}")
    QuestionAndKnowledge SelectKnowledge_Content(@Param("plushContentId") String plushContentId);
}
