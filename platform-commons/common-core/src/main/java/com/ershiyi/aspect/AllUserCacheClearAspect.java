package com.ershiyi.aspect;

import com.ershiyi.aspect.annotation.AllUserCacheClear;
import com.ershiyi.utils.RedisUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AllUserCacheClearAspect {


    @Before("@annotation(allUserCacheClear)")
    public void before(AllUserCacheClear allUserCacheClear){
        RedisUtils.delkeys(allUserCacheClear.cacheNames());
    }
}
