package com.ershiyi.service;

import com.ershiyi.utils.TokenUtils;
import com.ershiyi.vo.TokenVO;

public interface LoginService {

    default TokenVO createToken(String userKey, String system, String device,String schoolId) {
        String token = TokenUtils.createToken(userKey, system, device,schoolId);
        TokenUtils.syncResponseToken(token);
        TokenUtils.syncRedis(token);
        return TokenVO.builder()
                .userKey(userKey)
                .token(token)
                .sys(system)
                .device(device)
                .schoolId(schoolId)
                .exp(TokenUtils.getDate(token, TokenUtils.EXP_KEY).getTime())
                .refresh(TokenUtils.getDate(token, TokenUtils.REFRESH_KEY).getTime())
                .build();
    }
}
