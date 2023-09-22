package com.yc.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration

public class RestTemplateConfig {
//    @LoadBalanced  //全局注解，所有调用restTemplate的请求都采用轮询方式
//@Bean
//public RestTemplate restTemplate() {
//    return new RestTemplate();
//}
}
