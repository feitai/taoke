package com.yc.config;

import com.yc.loadbalancer.MyOnlyOneBalancer;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MyOnlyOneConfig {
    // 配置成随机访问
    @Bean
    public ReactorServiceInstanceLoadBalancer MyOnlyOneReactorServiceInstanceLoadBalancer(
            Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        // 随机RandomLoadBalancer；轮询RoundRobinLoadBalancer
        return new MyOnlyOneBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class),name);
    }
}
