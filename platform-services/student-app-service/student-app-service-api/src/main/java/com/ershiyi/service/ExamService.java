package com.ershiyi.service;

import com.ershiyi.domain.Common_Choice;
import com.ershiyi.domain.Common_StudyrateBy;
import com.ershiyi.dto.ExamDTO;

import java.util.List;
import java.util.Map;

public interface ExamService  extends BaseService<ExamDTO> {

    List randomExam(List<ExamDTO> examdto);

    Integer submitExam(Map<String, List<Common_Choice>> list);
}
