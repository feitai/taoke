package com.yc.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义的记录资源的时间的过滤器
 * 有一个参数  ：  显示的时间的单位是：  分   ：秒
 */
@Component
@Slf4j
public class MyTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<MyTimeGatewayFilterFactory.MyTimeUnitConfig> {

    public MyTimeGatewayFilterFactory(){
        super(MyTimeUnitConfig.class);
    }


    public static class MyTimeUnitConfig {

        private String name;
        public String getName() {return name;}
        public void setName(String name){this.name = name;}

    }

    @Override
    public GatewayFilter apply(MyTimeUnitConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                log.info("请求来了，参数为"+ config.getName()  + "资源为"+exchange.getRequest().getPath());
                System.out.println("请求来了，参数为"+ config.getName()  + "资源为"+exchange.getRequest().getPath());
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return chain.filter(exchange).then(
                        //回调处理结果
                        Mono.fromRunnable( () ->{
                            long end = System.currentTimeMillis();
                            long diff =  end-start;
                            System.out.println(diff);
                             if("分".equalsIgnoreCase(config.getName())){
                                 log.info(  "执行时间为  ： "+ (diff/60/1000) + "分" );
                                 System.out.println("执行时间为  ： "+ (double)(diff/60/1000) + "分");
                             }else if("秒".equalsIgnoreCase(config.getName())){
                             log.info(  "执行时间为  ： "+ (diff/1000) + "秒" );
                                 System.out.println("执行时间为  ： "+ (double)(diff/1000) + "秒");
                             }
                        })
                );
            }
        };

    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }
}
