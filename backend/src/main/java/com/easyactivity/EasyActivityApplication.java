package com.easyactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.easyactivity.mapper")
@ComponentScan(basePackages = "com.easyactivity")
public class EasyActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyActivityApplication.class,args);
    }
}