package com.ershiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.ershiyi.mapper")
@EnableSwagger2
@EnableCaching
public class PayServiceProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayServiceProviderApplication.class, args);
	}

}
