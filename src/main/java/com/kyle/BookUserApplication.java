package com.kyle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.kyle.*"})
@EnableDiscoveryClient
@EnableRedisHttpSession
public class BookUserApplication {
    public static void main(String[] args){
        SpringApplication.run(BookUserApplication.class);
    }
}
