package com.ershiyi.aspect;

import com.ershiyi.domain.AbstractBaseDomain;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class BaseServiceAspect {

    @Pointcut("@annotation(com.ershiyi.aspect.annotation.AutowiredData)")
    public void pointcut(){}

    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
        for(Object o : joinPoint.getArgs()){
            if(o instanceof AbstractBaseDomain){
                ((AbstractBaseDomain) o).init();
            }
        }
    }

}
