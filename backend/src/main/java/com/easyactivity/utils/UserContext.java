package com.easyactivity. utils;

import com.easyactivity.entity.User;

/**
 * 用户上下文工具类
 * 使用 ThreadLocal 存储当前登录用户信息
 */
public class UserContext {

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前登录用户
     */
    public static void setCurrentUser(User user) {
        userThreadLocal.set(user);
    }

    /**
     * 获取当前登录用户
     */
    public static User getCurrentUser() {
        return userThreadLocal. get();
    }

    /**
     * 获取当前用户ID
     */
    public static Long getCurrentUserId() {
        User user = userThreadLocal.get();
        return user != null ? user.getId() : null;
    }

    /**
     * 清除当前用户信息（防止内存泄漏）
     */
    public static void clear() {
        userThreadLocal.remove();
    }
}