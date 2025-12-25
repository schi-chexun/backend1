package com.easyactivity.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户视图对象（不包含密码）
 */
@Data
public class UserVO {
    private Long id;
    private String username;
    private String realName;
    private String studentId;
    private String email;
    private String phone;
    private Integer role;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

