package com.ershiyi.service;

import com.ershiyi.domain.entity.A_KnowContent;
import com.ershiyi.domain.entity.Correct;
import com.ershiyi.domain.entity.ResultQuestion;
import com.ershiyi.dto.JpushPojo;
import com.ershiyi.dto.QuestionAndKnowledge;
import java.util.List;

public interface JpushService extends BaseService<JpushPojo>  {
    //String Sendtype, String RegistrationId, String Msg
    JpushPojo sendJpush(JpushPojo jpush);

    List<ResultQuestion> questionjpush(QuestionAndKnowledge question);
}
