package com.yc.config;

import com.yc.loadbalancer.MyOnlyOneBalancer;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@LoadBalancerClients(
        value = {
                @LoadBalancerClient(value = "res-food",configuration = RandomConfig.class),
                @LoadBalancerClient(value = "res-security",configuration = RandomConfig.class)

        },defaultConfiguration =  LoadBalancerClientConfiguration.class
)
@Configuration
public class LoadBalancerConfig {
        @LoadBalanced
        @Bean
        public RestTemplate restTemplate() {
                return new RestTemplate();
        }


        @Component
        public class CustomerRequestInterceptor implements RequestInterceptor {

                @Override
                public void apply(RequestTemplate requestTemplate) {
                        requestTemplate.header("source","order-source");
                }
        }
}
