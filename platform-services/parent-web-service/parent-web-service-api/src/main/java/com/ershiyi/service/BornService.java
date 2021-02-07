package com.ershiyi.service;

import com.ershiyi.dto.LocationRequestDTO;
import com.ershiyi.dto.questionSituationDTO;

import java.util.List;
import java.util.Map;

public interface BornService {

    questionSituationDTO questionSituation(LocationRequestDTO localtionrequest);

    Map <String,List<questionSituationDTO>> knowledgeNumber(LocationRequestDTO localtionrequest);

    Map<String,List<questionSituationDTO>> studyDuration(LocationRequestDTO localtionrequest);

    List<questionSituationDTO> studyTime(LocationRequestDTO localtionrequest);

    Object syntheticalScore(LocationRequestDTO localtionrequest);
}
