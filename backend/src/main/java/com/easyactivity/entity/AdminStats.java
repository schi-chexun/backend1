package com.easyactivity.entity;

import lombok.Data;
import java.util.List;

/**
 * 管理员统计数据实体
 * 用于封装管理员Dashboard页面展示的统计信息
 */
@Data
public class AdminStats {

    // ========== 基础统计数据 ==========

    /**
     * 总用户数
     */
    private Long totalUsers;

    /**
     * 总活动数
     */
    private Long totalActivities;

    /**
     * 总报名数
     */
    private Long totalParticipations;

    /**
     * 待审核活动数
     */
    private Long pendingAudits;

    /**
     * 今日新增用户数
     */
    private Long todayNewUsers;

    /**
     * 今日新增活动数
     */
    private Long todayNewActivities;

    // ========== 扩展统计数据 ==========

    /**
     * 本周新增用户数
     */
    private Long weekNewUsers;

    /**
     * 本月新增用户数
     */
    private Long monthNewUsers;

    /**
     * 进行中的活动数
     */
    private Long ongoingActivities;

    /**
     * 已结束的活动数
     */
    private Long finishedActivities;

    /**
     * 平均每个活动的报名人数
     */
    private Double avgParticipantsPerActivity;

    /**
     * 活动报名率（已报名人数/总容量）
     */
    private Double activityFillRate;

    // ========== 关联数据 ==========

    /**
     * 热门活动列表（按报名人数排序）
     */
    private List<Activity> popularActivities;

    /**
     * 最近创建的活动列表
     */
    private List<Activity> recentActivities;

    /**
     * 最活跃的用户列表（按参与活动数排序）
     */
    private List<User> activeUsers;
}