package com.ershiyi.aspect;

import cn.hutool.core.codec.Base64;
import com.ershiyi.config.JwtConfig;
import com.ershiyi.dto.AccountAuthDTO;
import com.ershiyi.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountAspect {

    @Autowired
    private JwtConfig jwtConfig;

    @Pointcut("@annotation(com.ershiyi.aspect.annotation.BasicAuth)")

    public void pointcut() {
    }

    /**
     * 方法执行前
     */
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint) {
        /**
         * 登录验证
         */
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof AccountAuthDTO) {
                String basic = jwtConfig.getHeader().getBasic();
                String authorizationInfo = WebUtils.getHttpServletRequestAuthorization();
                if (StringUtils.startsWith(authorizationInfo, basic)) {
                    authorizationInfo = StringUtils.replace(authorizationInfo, basic, "").trim();
                    authorizationInfo = Base64.decodeStr(authorizationInfo);
                    String[] authorizationInfos = StringUtils.split(authorizationInfo, ":");
                    if (authorizationInfos.length == 2) {
                        AccountAuthDTO accountAuthDTO = (AccountAuthDTO) o;
                        String loginName = authorizationInfos[0];
                        String password = authorizationInfos[1];
                        accountAuthDTO.setLoginId(loginName);
                        accountAuthDTO.setPassWord(password);
                    }
                }
            }
        }
    }
    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        /**
         * 记录登录日志
         */
        System.out.println("记录日志成功");
        for (Object o : joinPoint.getArgs()) {
            if (o instanceof AccountAuthDTO) {


                 System.out.println(o);
            }
        }
    }
}
