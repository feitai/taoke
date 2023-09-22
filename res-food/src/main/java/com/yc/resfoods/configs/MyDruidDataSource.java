package com.yc.resfoods.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@Slf4j
@RefreshScope
public class MyDruidDataSource {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    @Primary
    @RefreshScope
    public DataSource druid() {
        log.info("使用编程式数据源创建.");
        DruidDataSource ds = new DruidDataSource();
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName); // 注意修正此处的大小写错误
        ds.setUrl(url); // 注意修正此处的大小写错误
        return ds;
    }
}
