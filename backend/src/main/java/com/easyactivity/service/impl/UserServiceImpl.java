package com.easyactivity.service.impl;

import com.easyactivity.entity.User;
import com.easyactivity.mapper.UserMapper;
import com.easyactivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    @Transactional
    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.selectByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查学号是否已存在
        if (user.getStudentId() != null) {
            existingUser = userMapper.selectByStudentId(user.getStudentId());
            if (existingUser != null) {
                throw new RuntimeException("学号已存在");
            }
        }

        user.setRole(0); // 默认普通用户
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return userMapper.selectById(user.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 用户登录（新增）
     */
    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.selectByUsername(username);

        // 用户不存在或密码错误
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 登录成功，返回用户信息（注意：实际返回前应在Controller层清除密码字段）
        return user;
    }
}