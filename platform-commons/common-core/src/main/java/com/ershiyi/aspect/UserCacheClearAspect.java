package com.ershiyi.aspect;

import cn.hutool.core.util.StrUtil;
import com.ershiyi.aspect.annotation.UserCacheClear;
import com.ershiyi.utils.RedisUtils;
import com.ershiyi.utils.TokenUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserCacheClearAspect {


    @Before("@annotation(userCacheClear)")
    public void before(UserCacheClear userCacheClear){
        String userKey = TokenUtils.getUserKey();
        for(String cacheName : userCacheClear.cacheNames()){
            RedisUtils.del(StrUtil.format("{}::{}", cacheName, userKey));
        }
    }
}
