package com.easyactivity.interceptor;

import com.easyactivity.entity.User;
import com.easyactivity. utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet. http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理员拦截器
 * 拦截需要管理员权限才能访问的接口
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理 OPTIONS 请求（跨域预检）
        if ("OPTIONS". equals(request.getMethod())) {
            return true;
        }

        // 从 ThreadLocal 获取当前登录用户（由 LoginInterceptor 设置）
        User currentUser = UserContext.getCurrentUser();

        // 检查用户是否存在
        if (currentUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"请先登录\",\"data\": null}");
            return false;
        }

        // 检查用户角色是否为管理员（role = 1）
        if (currentUser.getRole() == null || currentUser.getRole() != 1) {
            response. setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":403,\"message\":\"权限不足，需要管理员权限\",\"data\":null}");
            return false;
        }

        // 验证通过，允许访问
        return true;
    }
}