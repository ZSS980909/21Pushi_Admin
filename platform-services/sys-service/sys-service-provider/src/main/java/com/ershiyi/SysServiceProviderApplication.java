package com.ershiyi;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.ershiyi.mapper")
@EnableSwagger2
@EnableCaching
public class SysServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysServiceProviderApplication.class, args);
	}

}
