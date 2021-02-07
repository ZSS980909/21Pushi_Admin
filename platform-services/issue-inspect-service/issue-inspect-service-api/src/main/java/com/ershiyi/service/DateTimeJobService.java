package com.ershiyi.service;

import com.ershiyi.dto.QuestionAndKnowledge;

import java.util.List;
import java.util.Map;

public interface DateTimeJobService extends BaseService<QuestionAndKnowledge> {

    List<Map<String, Object>> SelectQuestionAndKnowledge() throws Exception;
}
