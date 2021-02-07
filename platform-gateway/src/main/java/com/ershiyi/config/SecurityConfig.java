package com.ershiyi.config;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("platform.security")
public class SecurityConfig {

    private List<String> skipAuthUrls = Lists.newCopyOnWriteArrayList();

    public List<String> getSkipAuthUrls() {
        return skipAuthUrls;
    }

    public void setSkipAuthUrls(List<String> skipAuthUrls) {
        this.skipAuthUrls = skipAuthUrls;
    }

    @Bean
    public PathMatcher mvcPathMatcher() {
        return new AntPathMatcher();
    }
}
