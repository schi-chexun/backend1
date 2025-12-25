package com.easyactivity. controller;

import com.easyactivity.common.Result;
import com.easyactivity. dto.LoginDTO;
import com. easyactivity.dto.RegisterDTO;
import com.easyactivity.entity.User;
import com.easyactivity. service.UserService;
import com.easyactivity.vo.UserVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation. Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户
     */
    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.error("用户不存在");
        }
        return Result.success(convertToVO(user));
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<UserVO> register(@RequestBody RegisterDTO dto) {
        try {
            User user = new User();
            BeanUtils. copyProperties(dto, user);
            User registered = userService.register(user);
            return Result.success("注册成功", convertToVO(registered));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody LoginDTO dto, HttpSession session) {
        try {
            // 使用 Service 层的 login 方法
            User user = userService. login(dto.getUsername(), dto.getPassword());

            // 登录成功，将用户信息存入 Session
            session.setAttribute("currentUser", user);

            return Result. success("登录成功", convertToVO(user));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 用户登出
     */
    @PostMapping("/logout")
    public Result<? > logout(HttpSession session) {
        session.invalidate();  // 销毁 Session
        return Result.success("退出登录成功");
    }

    /**
     * 获取当前登录用户信息
     */
    @GetMapping("/current")
    public Result<UserVO> getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            return Result.error("未登录");
        }
        // 重新从数据库查询最新信息
        User currentUser = userService.getById(user.getId());
        return Result. success(convertToVO(currentUser));
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/{id}")
    public Result<UserVO> update(@PathVariable Long id, @RequestBody RegisterDTO dto) {
        try {
            User user = new User();
            BeanUtils.copyProperties(dto, user);
            user.setId(id);
            User updated = userService.update(user);
            return Result.success("用户信息更新成功", convertToVO(updated));
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Result<? > delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success("用户删除成功");
    }

    /**
     * 转换为VO（不包含密码）
     */
    private UserVO convertToVO(User user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }
}