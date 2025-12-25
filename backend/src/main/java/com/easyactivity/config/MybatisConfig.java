package com.easyactivity.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.easyactivity.mapper")
public class MybatisConfig {
    // MyBatis配置已通过application.yml完成
}

