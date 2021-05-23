package com.ershiyi.mapper;

import com.ershiyi.domain.entity.FractionRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 最优题目
 */
@Repository
@Mapper
public interface BestQuestionMapper {

    /**
     * 获取合格的题目id
     * @return
     */
    List<Integer> getQualifiedKnowId();

    List<String> getBestStudent();

    List<Integer> getQuestionIdByStudent(List<String> students);

    int updateQuestion(List<Integer> result);

    @Select("select studenterId from common_student_user where deleted = 0")
    List<String> getAllStudent();

    @Select("select DISTINCT knowledgeId from common_course_knowledge_record where studenterId = #{student} and knowledgeId not in (select knowledgeId from common_student_calculate_record where studenterId = #{student} and isLast =1)")
    List<Integer> getStudyKnowledge(String student);

    @Select("select useTime from common_course_knowledge_record where studenterId = #{student} and knowledgeId = #{know} order by id desc ")
    List<Integer> getKnowledgeLength(@Param("student") String student,@Param("know") Integer know);

    @Select("select fraction from common_student_calculate_record where studenterId =#{student} and knowledgeId = #{know}  order by id desc ")
    List<Double> isAddRecord(@Param("student") String student,@Param("know") Integer know);

    @Insert("insert into common_student_calculate_record values(null,#{student},#{isLast},#{know},#{count})")
    void insertRecord(@Param("count") double count,@Param("student") String student,@Param("isLast") int i,@Param("know") int know);

    @Select("select ifNull(sum(fraction)/count(*),0) from common_student_calculate_record where isLast = 1 and studenterId = #{student}")
    double getFinalFraction(String student);

    @Select("select id from common_student_calculate where studenterId = #{student}")
    List<Integer> getStudentFraction(String student);

    @Select("select ifnull(count(*),0) as number,ifnull(sum(fraction)/count(*),0) as fraction from common_student_calculate")
    FractionRecord getAvgFraction();

    @Insert("insert into common_student_calculate values(null,#{student},#{fraction})")
    void insertFraction(@Param("student")String student,@Param("fraction")double finalFraction);

    @Update("update common_student_calculate set fraction = #{fraction} where id = #{id}")
    void updateFraction(@Param("id") Integer id,@Param("fraction") double finalFraction);
}
