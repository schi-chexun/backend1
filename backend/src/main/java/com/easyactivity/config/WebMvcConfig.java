package com.easyactivity.config;

import com.easyactivity.interceptor.AdminInterceptor;
import com.easyactivity.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet. config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    /**
     * 配置跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录拦截器：拦截需要登录的接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")  // 拦截所有 /api 开头的接口
                .excludePathPatterns(
                        "/api/users/login",       // 登录接口放行
                        "/api/users/register",    // 注册接口放行
                        "/api/activities",        // 查询所有活动放行（游客可见）
                        "/api/activities/{id}",   // 查询单个活动放行
                        "/api/activities/search", // 搜索活动放行
                        "/api/activities/location", // 按地点查询放行
                        "/api/activities/recommended" // 推荐活动放行
                );

        // 管理员拦截器：拦截需要管理员权限的接口
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");  // 所有 /api/admin 开头的接口需要管理员权限
    }
}