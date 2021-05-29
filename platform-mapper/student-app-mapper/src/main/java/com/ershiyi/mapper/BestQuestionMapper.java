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

    /**
     * 获取符合条件的学生编号
     * @return
     */
    List<String> getBestStudent();

    /**
     * 根据学生编号获取题目id
     * @param students
     * @return
     */
    List<Integer> getQuestionIdByStudent(List<String> students);

    /**
     * 更新最优题目
     * @param result
     * @return
     */
    int updateQuestion(List<Integer> result);

    /**
     * 查询当前的学生列表
     * @return
     */
    @Select("select studenterId from common_student_user where deleted = 0")
    List<String> getAllStudent();

    /**
     * 获取当前学生的所有学习过的知识点列表
     * @param student 学生编号
     * @return
     */
    @Select("select DISTINCT knowledgeId from common_course_knowledge_record where studenterId = #{student} and knowledgeId not in (select knowledgeId from common_student_calculate_record where studenterId = #{student} and isLast =1)")
    List<Integer> getStudyKnowledge(String student);

    /**
     * 获取知识点的学习时长
     * @param student 学生编号
     * @param know 知识点ID
     * @return
     */
    @Select("select useTime from common_course_knowledge_record where studenterId = #{student} and knowledgeId = #{know} order by id desc ")
    List<Integer> getKnowledgeLength(@Param("student") String student,@Param("know") Integer know);

    /**
     * 获取当前知识点添加的得分
     * @param student 学生编号
     * @param know 知识点ID
     * @return
     */
    @Select("select fraction from common_student_calculate_record where studenterId =#{student} and knowledgeId = #{know}  order by id desc ")
    List<Double> isAddRecord(@Param("student") String student,@Param("know") Integer know);

    /**
     *
     * @param count 次数
     * @param student 学生编号
     * @param i 是否最后一次
     * @param know 知识点ID
     */
    @Insert("insert into common_student_calculate_record values(null,#{student},#{isLast},#{know},#{count})")
    void insertRecord(@Param("count") double count,@Param("student") String student,@Param("isLast") int i,@Param("know") int know);

    /**
     * 获取学生的最终得分
     * @param student 学生编号
     * @return
     */
    @Select("select ifNull(sum(fraction)/count(*),0) from common_student_calculate_record where isLast = 1 and studenterId = #{student}")
    double getFinalFraction(String student);

    /**
     * 获取当前学生的成绩
     * @param student
     * @return
     */
    @Select("select id from common_student_calculate where studenterId = #{student}")
    List<Integer> getStudentFraction(String student);

    /**
     * 获取团队平均成绩
     * @return
     */
    @Select("select ifnull(count(*),0) as number,ifnull(sum(fraction)/count(*),0) as fraction from common_student_calculate")
    FractionRecord getAvgFraction();

    /**
     * 插入学生的最终成绩
     * @param student
     * @param finalFraction
     */
    @Insert("insert into common_student_calculate values(null,#{student},#{fraction})")
    void insertFraction(@Param("student")String student,@Param("fraction")double finalFraction);

    /**
     * 更新学生的成绩
     * @param id
     * @param finalFraction
     */
    @Update("update common_student_calculate set fraction = #{fraction} where id = #{id}")
    void updateFraction(@Param("id") Integer id,@Param("fraction") double finalFraction);

    /**
     * 获取当前所有的成绩
     * @return
     */
    @Select("select fraction from common_student_calculate")
    List<Double> getAvgLastFraction();

    /**
     * 插入团队标准差
     * @param avg
     * @param std
     */
    @Insert("insert into common_team_avg(avgTeam,standardDeviation) values(#{avg} ,#{std})")
    void insertTeamAvg(@Param("avg") double avg,@Param("std") double std);
}
