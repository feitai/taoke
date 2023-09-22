package com.yc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan("com.yc.resfoods.dao")
@EnableDiscoveryClient
@EnableOpenApi
@EnableSwagger2
public class ResFoodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResFoodApplication.class,args);
    }
}
