package com.ershiyi.mapper;

import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.dto.ScoreDTO;
import com.ershiyi.dto.questionSituationDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BornMapper {
    @Select("select  (select count(id) from  common_course_knowledge_record where  correct =0 and studenterid=#{studenterId} and courseId in (select id from common_course  ))as errorcount,count(id) as perrorcount ,(select  count(id) from  common_course_knowledge_record where correct=1 and studenterid=#{studenterId}  and courseId in (select id from common_course )) as correctcount,(select count(id) FROM  common_course_knowledge_record where correct=1  and courseId in (select id from common_course ))  as pcorrectcount from   common_course_knowledge_record where correct=0")
    questionSituationDTO questionSituation(LocationRequestDTO localtionrequest);

    @Select("select  count(any_value(id)) as PNumberCount,any_value(startTime) as startTime,studenterId from  common_course_knowledge_record where month(starttime)=#{month} and year(starttime)=#{year}  and courseId in (select id from common_course where subjectid =#{subjectId})  group by to_days(starttime),studenterid")
    List<questionSituationDTO>    knowledgeNumber(LocationRequestDTO localtionrequest);

    @Select("select sum(any_value(usetime)) as sumNumber,any_value(studenterId) as studenterId,subjectid,subjectName  from study_length where  month(starttime)=#{month} and year(starttime)=#{year} and courseId in (select id from common_course where subjectid =#{subjectId}) group by  studenterId")
    List<questionSituationDTO> studyDuration(LocationRequestDTO localtionrequest);

    @Select("select sum(any_value(usetime)) as sumNumber,any_value(startTime) as startTime,any_value(studenterId) from  study_length where  month(starttime)=#{month} and year(starttime)=#{year} and courseId in (select id from common_course where subjectid =#{subjectId}) group by to_days(starttime),studenterId")
    List<questionSituationDTO> SMaxandMix(LocationRequestDTO localtionrequest);

    @Select("select avg(any_value(usetime)) as sumNumber,any_value(startTime) as startTime,any_value(studenterId),any_value(courseId) from  study_length where   month(starttime)=#{month} and year(starttime)=#{year}  and courseId in (select id from common_course where subjectid =#{subjectId})  group by to_days(starttime)")
    List<questionSituationDTO> SAvg(LocationRequestDTO localtionrequest);


    @Select("select count(any_value(id)) as NumberCount,any_value(starttime)  as starttime  from  common_course_knowledge_record WHERE  studenterId=#{studenterId} and courseId in (select id from common_course  where subjectid =#{subjectId}) and month(starttime)=#{month} and year(starttime)=#{year} group by to_days(starttime)")
    List<questionSituationDTO> knowledgeNumberByStudenterId(LocationRequestDTO localtionrequest);

    @Select("select  any_value(sum(usetime)) as usetime,(select  subjectname from common_course_subject where id =(select subjectid from common_course where id =  any_value(courseId)) and deleted=0 and  ifuser=1) as subjectName from study_length where TO_DAYS(starttime)= TO_DAYS('2020-12-8 11:33:25')  and studenterId=#{studenterId} GROUP BY courseId")
    List<questionSituationDTO> studyTime(LocationRequestDTO localtionrequest);

    @Select("select  id,subjectname from common_course_subject where deleted=0 and ifuser=1")
    List<questionSituationDTO> SSubject();

    @Insert("insert into common_comprehensive_score (subjectName,score,studenterId,subjectId,lastScore) values(#{subjectName},#{score},#{studenterId},#{subjectId},#{lastScore})")
    Integer score(ScoreDTO scoredto);

    @Select("select * from common_comprehensive_score  where studenterId= #{studenterId} and  subjectId=#{subjectId} and TO_DAYS(createdt) = To_days(now())")
    List<ScoreDTO> Sscore(LocationRequestDTO localtionrequest);

    @Select("select *  from common_comprehensive_score as a where createdt = (select max(createdt) from common_comprehensive_score where a.subjectId=subjectId )")
    List<ScoreDTO> yesscore(LocationRequestDTO local);

//    @Select("select * from common_comprehensive_score where studenterId=#{studenterId} and To_days(now())- TO_DAYS(createdt) =1")
//    List<ScoreDTO> Scoreyesterday(LocationRequestDTO localtionrequest);

//    @Select("select  * from  study_length where year(starttime)=#{STime} group by  month(starttime)")
//    List<questionSituationDTO> SMaxandMixbyYear(LocationRequestDTO localtionrequest);

//    @Select("select  usetime,starttime,studenterId from  study_length where  starttime>=#{startTime} and starttime <=#{endTime}   and studenterId=#{studenterId} group by to_days(starttime)")
//    List<questionSituationDTO> SThis(LocationRequestDTO localtionrequest);
}
