package com.hrms.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动入口
 * 配置入口 org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration
 *
 * @author zhengjun
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.hrms.demo.mapper")
public class ShardingJdbcServer {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcServer.class, args);
    }
}
