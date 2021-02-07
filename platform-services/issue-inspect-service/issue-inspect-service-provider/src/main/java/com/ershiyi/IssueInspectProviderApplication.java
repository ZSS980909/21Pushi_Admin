package com.ershiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.ershiyi.mapper")
@EnableSwagger2
@EnableCaching
@EnableScheduling
public class IssueInspectProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueInspectProviderApplication.class, args);
    }
}
