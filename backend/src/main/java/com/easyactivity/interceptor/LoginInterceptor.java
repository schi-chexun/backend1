package com.easyactivity.interceptor;

import com.easyactivity.entity.User;
import com.easyactivity.utils.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype. Component;
import org.springframework. web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 * 拦截需要登录才能访问的接口
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理 OPTIONS 请求（跨域预检）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 从 Session 中获取用户信息
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                // 将用户信息存入 ThreadLocal，方便后续使用
                UserContext.setCurrentUser(user);
                return true;
            }
        }

        // 未登录，返回 401 错误
        response. setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\": 401,\"message\":\"请先登录\",\"data\": null}");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求完成后清理 ThreadLocal，防止内存泄漏
        UserContext.clear();
    }
}