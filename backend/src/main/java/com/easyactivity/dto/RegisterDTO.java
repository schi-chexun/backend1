package com.easyactivity.dto;

import lombok.Data;

/**
 * 用户注册DTO
 */
@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String realName;
    private String studentId;
    private String email;
    private String phone;
}

