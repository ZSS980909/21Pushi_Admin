package com.ershiyi.filter;

import com.ershiyi.config.SecurityConfig;
import com.ershiyi.exception.AuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private PathMatcher pathMatcher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        //跳过不需要验证的路径
        for(String skipAuthUrl : securityConfig.getSkipAuthUrls()){
            if (pathMatcher.match(skipAuthUrl, url)) {
                return chain.filter(exchange);
            }
        }

        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
       // System.out.println(token);
        if(StringUtils.isEmpty(token)){
            throw new AuthException("请求无效");
        }
        //TokenUtils.verificationToken(token)
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return -100;
    }
}
