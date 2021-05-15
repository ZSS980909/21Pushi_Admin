package com.ershiyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
