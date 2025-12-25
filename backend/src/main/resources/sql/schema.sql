CREATE DATABASE IF NOT EXISTS easy_activity DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE easy_activity;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `real_name` VARCHAR(50) COMMENT '真实姓名',
    `student_id` VARCHAR(20) UNIQUE COMMENT '学号',
    `email` VARCHAR(100) COMMENT '邮箱',
    `phone` VARCHAR(20) COMMENT '手机号',
    `role` INT DEFAULT 0 COMMENT '用户角色：0-普通用户，1-管理员',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    INDEX `idx_student_id` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 活动表
CREATE TABLE IF NOT EXISTS `activity` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '活动ID',
    `title` VARCHAR(200) NOT NULL COMMENT '活动标题',
    `description` TEXT COMMENT '活动描述',
    `location` VARCHAR(200) COMMENT '活动地点',
    `start_time` DATETIME NOT NULL COMMENT '活动开始时间',
    `end_time` DATETIME NOT NULL COMMENT '活动结束时间',
    `status` INT DEFAULT 0 COMMENT '活动状态：0-未开始，1-进行中，2-已结束，3-已取消',
    `max_participants` INT COMMENT '最大参与人数',
    `current_participants` INT DEFAULT 0 COMMENT '当前参与人数',
    `creator_id` BIGINT COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_creator_id` (`creator_id`),
    INDEX `idx_start_time` (`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动表';

-- 活动参与者表
CREATE TABLE IF NOT EXISTS `activity_participant` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `activity_id` BIGINT NOT NULL COMMENT '活动ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `register_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
    `check_in_status` INT DEFAULT 0 COMMENT '签到状态：0-未签到，1-已签到',
    `check_in_time` DATETIME COMMENT '签到时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_activity_user` (`activity_id`, `user_id`),
    INDEX `idx_activity_id` (`activity_id`),
    INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='活动参与者表';

-- 插入测试数据
-- 插入管理员用户（密码：admin123）
INSERT INTO `user` (`username`, `password`, `real_name`, `student_id`, `email`, `phone`, `role`) 
VALUES ('admin', 'admin123', '管理员', '20240001', 'admin@example.com', '13800138000', 1);

-- 插入普通用户（密码：user123）
INSERT INTO `user` (`username`, `password`, `real_name`, `student_id`, `email`, `phone`, `role`) 
VALUES ('user1', 'user123', '张三', '20240002', 'user1@example.com', '13800138001', 0);

INSERT INTO `user` (`username`, `password`, `real_name`, `student_id`, `email`, `phone`, `role`) 
VALUES ('user2', 'user123', '李四', '20240003', 'user2@example.com', '13800138002', 0);

-- 插入测试活动
INSERT INTO `activity` (`title`, `description`, `location`, `start_time`, `end_time`, `status`, `max_participants`, `creator_id`) 
VALUES ('校园篮球赛', '欢迎所有篮球爱好者参加', '体育馆', '2024-12-20 14:00:00', '2024-12-20 17:00:00', 0, 20, 1);

INSERT INTO `activity` (`title`, `description`, `location`, `start_time`, `end_time`, `status`, `max_participants`, `creator_id`) 
VALUES ('编程竞赛', 'ACM编程竞赛，欢迎报名', '计算机实验室', '2024-12-25 09:00:00', '2024-12-25 12:00:00', 0, 50, 1);

