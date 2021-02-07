package com.ershiyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.ersiyi.mapper")
public class AutoServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoServiceProviderApplication.class, args);
	}

}
