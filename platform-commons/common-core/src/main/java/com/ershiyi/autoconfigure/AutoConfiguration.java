package com.ershiyi.autoconfigure;

import com.ershiyi.config.JwtConfig;
import com.ershiyi.utils.SpringContextHolder;
import com.ershiyi.utils.TokenUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 默认配置类
 *
 * @author liy
 * @since 2020/06/06
 */
@Configuration
public class AutoConfiguration {

    /**
     * Spring上下文工具配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SpringContextHolder.class)
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Bean
    public TokenUtils tokenUtils(JwtConfig jwtConfig){
        return new TokenUtils(jwtConfig);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
        return new BCryptPasswordEncoder();
    }
}
