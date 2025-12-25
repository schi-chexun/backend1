package com.easyactivity.service;

import com.easyactivity.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据ID查询用户
     */
    User getById(Long id);
    
    /**
     * 根据用户名查询用户
     */
    User getByUsername(String username);
    
    /**
     * 注册用户
     */
    User register(User user);
    
    /**
     * 更新用户信息
     */
    User update(User user);
    
    /**
     * 删除用户
     */
    void delete(Long id);

    /**
     * 用户登录（新增）
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回用户信息（不含密码），失败返回null或抛异常
     */
    User login(String username, String password);
}
